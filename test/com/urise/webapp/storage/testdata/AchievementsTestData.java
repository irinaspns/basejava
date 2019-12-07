package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextListSection;

public class AchievementsTestData {

    void addData(Resume resume) {
        TextListSection data = new TextListSection();
        data.addItem("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        resume.addData(SectionType.ACHIEVEMENT, data);
    }
}
