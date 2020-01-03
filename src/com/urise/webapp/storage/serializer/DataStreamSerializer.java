package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd-MMM-yyyy");

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());

            // Contacten
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }

            // Sections
            Map<SectionType, Section> sections = r.getSections();
            dos.writeInt(sections.size());
            for (Map.Entry<SectionType, Section> entry : sections.entrySet()) {
                SectionType sectionType = entry.getKey();
                dos.writeUTF(sectionType.name());
                switch (sectionType) {
                    case OBJECTIVE:
                    case PERSONAL:
                        writeTextSection(sectionType, sections, dos);
                        break;
                    case ACHIEVEMENT:
                    case QUALIFICATIONS:
                        writeTextListSection(sectionType, sections, dos);
                        break;
                    case EDUCATION:
                    case EXPERIENCE:
                        writeOrganizationSection(sectionType, sections, dos);
                        break;
                }
            }
        }
    }

    private void writeTextSection(SectionType sectionType,
                                  Map<SectionType, Section> sections,
                                  DataOutputStream dos) throws IOException {

        dos.writeUTF(((TextSection) sections.get(sectionType)).getText());
    }

    private void writeTextListSection(SectionType sectionType,
                                      Map<SectionType, Section> sections,
                                      DataOutputStream dos) throws IOException {

        List<String> list = ((TextListSection) sections.get(sectionType)).getList();
        dos.writeInt(list.size());
        for (String s : list) {
            dos.writeUTF(s);
        }
    }

    private void writeOrganizationSection(SectionType sectionType,
                                          Map<SectionType, Section> sections,
                                          DataOutputStream dos) throws IOException {

        OrganizationSection organizationSection = (OrganizationSection) sections.get(sectionType);

        List<Organization> organizations = organizationSection.getOrganizations();
        dos.writeInt(organizations.size());
        for (Organization organization : organizations) {

            Link link = organization.getLink();
            dos.writeUTF(link.getName());
            dos.writeUTF(link.getUrl() == null ? "" : link.getUrl());

            List<Position> positions = organization.getPositions();
            dos.writeInt(positions.size());
            for (Position position : positions) {
                dos.writeUTF(position.getDescription() == null ? "" : position.getDescription());
                dos.writeUTF(position.getTitle());
                dos.writeUTF(position.getFrom().format(DATE_FORMATTER));
                dos.writeUTF(position.getTill().format(DATE_FORMATTER));
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {

        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);

            // Contacten
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
                        readTextSection(sectionType, dis, resume);
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

    private void readTextSection(SectionType sectionType, DataInputStream dis, Resume resume) throws IOException {

        resume.addSection(sectionType, new TextSection(dis.readUTF()));
    }

    private void readTextListSection(SectionType sectionType, DataInputStream dis, Resume resume) throws IOException {

        int size = dis.readInt();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        resume.addSection(sectionType, new TextListSection(list));
    }

    private void readOrganizationSection(SectionType sectionType, DataInputStream dis, Resume resume) throws IOException {

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
