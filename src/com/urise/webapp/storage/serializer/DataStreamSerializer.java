package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class DataStreamSerializer implements StreamSerializer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

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

    private interface ListReader<T> {
        T read() throws IOException;
    }

    private <T> List<T> readCollection(DataInputStream dis, ListReader<T> listReader) throws IOException {
        int size = dis.readInt();
        List list = new ArrayList(size);
        for (int i = 0; i < size; i++) {
            list.add(listReader.read());
        }
        return list;
    }

    private interface EntryReader<K, V> {
        Map.Entry<K, V> read() throws IOException;
    }

    private <K, V> void readMap(DataInputStream dis, Map<K, V> map, EntryReader<K, V> reader) throws IOException {
        int size = dis.readInt();
        for (int i = 0; i < size; i++) {
            Map.Entry<K, V> entry = reader.read();
            map.put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {

        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            // Contacts
            readMap(dis, resume.getContacts(), () -> new AbstractMap.SimpleEntry<>(ContactType.valueOf(dis.readUTF()), dis.readUTF()));

            // Sections
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        resume.addSection(sectionType, new TextListSection(readCollection(dis, dis::readUTF)));
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        resume.addSection(sectionType,
                                new OrganizationSection(readCollection(dis, () ->
                                        new Organization(dis.readUTF(), defaultIfEmpty(dis.readUTF(), null), readCollection(dis, () -> {
                                            String description = defaultIfEmpty(dis.readUTF(), null);
                                            String title = dis.readUTF();
                                            LocalDate from = LocalDate.parse(dis.readUTF(), DATE_FORMATTER);
                                            LocalDate till = LocalDate.parse(dis.readUTF(), DATE_FORMATTER);
                                            return new Position(from, till, title, description);
                                        })))));
                        break;
                }
            }

            return resume;
        }
    }

    private static String defaultIfEmpty(final String s, final String ifEmpty) {
        if ("".equals(s))
            return ifEmpty;
        return s;
    }
}