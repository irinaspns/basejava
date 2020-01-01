package com.urise.webapp.storage.serializer;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class DataStreamSerializer implements StreamSerializer {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            // TODO implements sections
            Map<SectionType, Section> sections = r.getSections();

            writeTextSection(SectionType.OBJECTIVE, sections, dos);
            writeTextSection(SectionType.PERSONAL, sections, dos);

            writeTextListSection(SectionType.ACHIEVEMENT, sections, dos);
            writeTextListSection(SectionType.QUALIFICATIONS, sections, dos);
        }
    }
    private void writeTextSection(SectionType sectionType,
                                      Map<SectionType, Section> sections,
                                      DataOutputStream dos) throws IOException {
        Section section = sections.get(sectionType);
        dos.writeInt(section == null ? 0 : 1);
        if (section != null) {
            dos.writeUTF(sectionType.name());
            dos.writeUTF(((TextSection) sections.get(sectionType)).getText());
        }
    }

    private void writeTextListSection(SectionType sectionType,
                                      Map<SectionType, Section> sections,
                                      DataOutputStream dos) throws IOException {
        Section section = sections.get(sectionType);
        dos.writeInt(section == null ? 0 : 1);
        dos.writeUTF(sectionType.name());
        if (section != null) {
            TextListSection textListSection = (TextListSection) sections.get(sectionType);
            List<String> list = textListSection.getList();
            dos.writeInt(list.size());
            for (String s : list) {
                dos.writeUTF(s);
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            Resume resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            for (int i = 0; i < size; i++) {
                resume.addContact(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            // TODO implements sections
            if (dis.readInt() > 0) { // OBJECTIVE
                resume.addSection(SectionType.valueOf(dis.readUTF()), new TextSection(dis.readUTF()));
            }
            if (dis.readInt() > 0) { // PERSONAL
                resume.addSection(SectionType.valueOf(dis.readUTF()), new TextSection(dis.readUTF()));
            }

            if (dis.readInt() > 0) { // ACHIEVEMENT
                readTextListSection(dis, resume);
            }
            if (dis.readInt() > 0) { // QUALIFICATIONS
                readTextListSection(dis, resume);
            }

            return resume;
        }
    }

    private void readTextListSection(DataInputStream dis, Resume resume) throws IOException {
        SectionType sectionType = SectionType.valueOf(dis.readUTF());
        int size = dis.readInt();
        List<String> list = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            list.add(dis.readUTF());
        }
        resume.addSection(sectionType, new TextListSection(list));
    }
}
