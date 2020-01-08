package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

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

    @Override
    public Resume doRead(InputStream is) throws IOException {

        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            // Contacts
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }

            // Sections
            size = dis.readInt();
            for (int i = 0; i < size; i++) {
                SectionType sectionType = SectionType.valueOf(dis.readUTF());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        resume.addSection(sectionType, new TextSection(dis.readUTF()));
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        readTextListSection(sectionType, dis, resume);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        readOrganizationSection(sectionType, dis, resume);
                        break;
                }
            }

            return resume;
        }
    }

    private void readTextListSection(SectionType sectionType, DataInputStream dis, Resume resume) throws
            IOException {

        int size = dis.readInt();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        resume.addSection(sectionType, new TextListSection(list));
    }

    private void readOrganizationSection(SectionType sectionType, DataInputStream dis, Resume resume) throws
            IOException {

        int organizationsSize = dis.readInt();
        if (organizationsSize > 0) {
            List<Organization> organizations = new ArrayList<>(organizationsSize);
            for (int i = 0; i < organizationsSize; i++) {
                String name = dis.readUTF();
                String url = dis.readUTF();
                if ("".equalsIgnoreCase(url)) {
                    url = null;
                }
                int positionsSize = dis.readInt();
                if (positionsSize > 0) {
                    List<Position> positions = new ArrayList<>();
                    for (int j = 0; j < positionsSize; j++) {
                        String description = dis.readUTF();
                        if ("".equalsIgnoreCase(description)) {
                            description = null;
                        }
                        String title = dis.readUTF();
                        LocalDate from = LocalDate.parse(dis.readUTF(), DATE_FORMATTER);
                        LocalDate till = LocalDate.parse(dis.readUTF(), DATE_FORMATTER);
                        positions.add(new Position(from, till, title, description));
                    }
                    Organization organization = new Organization(name, url, positions);
                    organizations.add(organization);
                }
            }
            resume.addSection(sectionType, new OrganizationSection(organizations));
        }
    }
}