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

            Map<SectionType, Section> sections = r.getSections();

            // TextSections
            writeTextSection(SectionType.OBJECTIVE, sections, dos);
            writeTextSection(SectionType.PERSONAL, sections, dos);

            // TextListSections
            writeTextListSection(SectionType.ACHIEVEMENT, sections, dos);
            writeTextListSection(SectionType.QUALIFICATIONS, sections, dos);

            // OrganizationSection
            writeOrganizationSection(SectionType.EDUCATION, sections, dos);
            writeOrganizationSection(SectionType.EXPERIENCE, sections, dos);

        }
    }

    private void writeTextSection(SectionType sectionType,
                                      Map<SectionType, Section> sections,
                                      DataOutputStream dos) throws IOException {
        Section section = sections.get(sectionType);
        dos.writeInt(section == null ? 0 : 1);
        if (section != null) {
            dos.writeUTF(sectionType.name());

            dos.writeUTF(((TextSection) section).getText());
        }
    }

    private void writeTextListSection(SectionType sectionType,
                                      Map<SectionType, Section> sections,
                                      DataOutputStream dos) throws IOException {
        Section section = sections.get(sectionType);
        dos.writeInt(section == null ? 0 : 1);
        if (section != null) {
            dos.writeUTF(sectionType.name());

            List<String> list = ((TextListSection) section).getList();
            dos.writeInt(list.size());
            for (String s : list) {
                dos.writeUTF(s);
            }
        }
    }

    private void writeOrganizationSection(SectionType sectionType,
                                  Map<SectionType, Section> sections,
                                  DataOutputStream dos) throws IOException {
        Section section = sections.get(sectionType);
        dos.writeInt(section == null ? 0 : 1);
        if (section != null) {
            dos.writeUTF(sectionType.name());

            OrganizationSection organizationSection = (OrganizationSection) section;

            List<Organization> organizations = organizationSection.getOrganizations();
            dos.writeInt(organizations.size());
            for (Organization organization: organizations) {
                // Link
                Link link = organization.getLink();
                dos.writeUTF(link.getName());
                dos.writeUTF(link.getUrl() == null ? "" : link.getUrl());

                List<Position> positions = organization.getPositions();
                dos.writeInt(positions.size());
                for (Position position: positions) {
                    dos.writeUTF(position.getDescription() == null ? "" : position.getDescription());
                    dos.writeUTF(position.getTitle());
                    dos.writeUTF(position.getFrom().format(DATE_FORMATTER));
                    dos.writeUTF(position.getTill().format(DATE_FORMATTER));
                }
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

            // TextSections
            if (dis.readInt() > 0) { // OBJECTIVE
                resume.addSection(SectionType.valueOf(dis.readUTF()), new TextSection(dis.readUTF()));
            }
            if (dis.readInt() > 0) { // PERSONAL
                resume.addSection(SectionType.valueOf(dis.readUTF()), new TextSection(dis.readUTF()));
            }

            // TextListSections
            if (dis.readInt() > 0) { // ACHIEVEMENT
                readTextListSection(dis, resume);
            }
            if (dis.readInt() > 0) { // QUALIFICATIONS
                readTextListSection(dis, resume);
            }

            // OrganizationSection
            if (dis.readInt() > 0) { // EDUCATION
                readOrganizationSection(dis, resume);
            }
            if (dis.readInt() > 0) { // EXPERIENCE
                readOrganizationSection(dis, resume);
            }

            return resume;
        }
    }

    private void readTextListSection(DataInputStream dis, Resume resume) throws IOException {
        SectionType sectionType = SectionType.valueOf(dis.readUTF());
        int size = dis.readInt();
        if (size > 0) {
            List<String> list = new ArrayList<>(size);
            for (int i = 0; i < size; i++) {
                list.add(dis.readUTF());
            }
            resume.addSection(sectionType, new TextListSection(list));
        }
    }

    private void readOrganizationSection(DataInputStream dis, Resume resume) throws IOException {
        SectionType sectionType = SectionType.valueOf(dis.readUTF());
        int organizationsSize = dis.readInt();
        if (organizationsSize > 0) {
            List<Organization> organizations = new ArrayList<>(organizationsSize);
            for (int i = 0; i < organizationsSize; i++) {
                String name = dis.readUTF();
                String url = dis.readUTF();
                int positionsSize = dis.readInt();
                if (positionsSize > 0) {
                    List<Position> positions = new ArrayList<>();
                    for (int j = 0; j < positionsSize; j++) {
                        String description = dis.readUTF();
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
