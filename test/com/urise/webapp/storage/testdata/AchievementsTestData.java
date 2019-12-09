package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.TextListSection;

import java.util.ArrayList;

public class AchievementsTestData {

    TextListSection getData() {
        TextListSection data = new TextListSection(new ArrayList<>());
        data.addItem("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 1000 выпускников.");
        return data;
    }
}
