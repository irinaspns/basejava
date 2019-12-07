package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

public class ObjectiveTestData {

    void addData(Resume resume) {
        TextSection data = new TextSection();
        data.setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        resume.addData(SectionType.OBJECTIVE, data);
    }
}
