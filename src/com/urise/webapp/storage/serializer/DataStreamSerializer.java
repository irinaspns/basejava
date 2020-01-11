package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            // Contacts
            writeCollection(dos, r.getContacts().entrySet(), contactEntry -> {
                dos.writeUTF(contactEntry.getKey().name());
                dos.writeUTF(contactEntry.getValue());
            });

            // Sections
            writeCollection(dos, r.getSections().entrySet(), sectionEntry -> {
                SectionType sectionType = sectionEntry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        dos.writeUTF(((TextSection) sectionEntry.getValue()).getText());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeCollection(dos, ((TextListSection) sectionEntry.getValue()).getList(), dos::writeUTF);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        ElementWriter<Organization> organizationElementWriter = organization -> {
                            Link link = organization.getLink();
                            dos.writeUTF(link.getName());
                            dos.writeUTF(link.getUrl() == null ? "" : link.getUrl());

                            writeCollection(dos, organization.getPositions(), position -> {
                                dos.writeUTF(position.getDescription() == null ? "" : position.getDescription());
                                dos.writeUTF(position.getTitle());
                                dos.writeUTF(position.getFrom().format(DATE_FORMATTER));
                                dos.writeUTF(position.getTill().format(DATE_FORMATTER));
                            });
                        };
                        writeCollection(dos, ((OrganizationSection) sectionEntry.getValue()).getOrganizations(), organizationElementWriter);
                        break;
                }
            });
        }
    }

    private interface ElementWriter<T> {
        void write(T t) throws IOException;
    }

    private <T> void writeCollection(DataOutputStream dos, Collection<T> collection, ElementWriter<T> writer) throws IOException {
        dos.writeInt(collection.size());
        for (T element : collection) {
            writer.write(element);
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {

        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            // Contacts
            readMap(dis, () -> resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            // Sections
            readMap(dis, () -> {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                Section section = null;
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        section = new TextSection(dis.readUTF());
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        section = new TextListSection(readList(dis, dis::readUTF));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        section = new OrganizationSection(readList(dis, () ->
                                new Organization(dis.readUTF(), nullIfEmpty(dis.readUTF()), readList(dis, () -> {
                                    String description = nullIfEmpty(dis.readUTF());
                                    String title = dis.readUTF();
                                    LocalDate from = LocalDate.parse(dis.readUTF(), DATE_FORMATTER);
                                    LocalDate till = LocalDate.parse(dis.readUTF(), DATE_FORMATTER);
                                    return new Position(from, till, title, description);
                                }))));
                        break;
                }
                resume.addSection(sectionType, section);
            });
            return resume;
        }
    }

    private interface Reader {
        void read() throws IOException;
    }

    private void readMap(DataInputStream dis, Reader reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            reader.read();
        }
    }

    private interface ListReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readList(DataInputStream dis, ListReader<T> listReader) throws IOException {
        int size = dis.readInt();
        List<T> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(listReader.read());
        }
        return list;
    }


    private static String nullIfEmpty(final String s) {
        return "".equals(s) ? null : s;
    }
}