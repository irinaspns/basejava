package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EducationTestData {

    public static final String TEST_URL = "https://javawebinar.github.io/";

    void addData(Resume resume) {

        List<Organization> organizations = new ArrayList<>();

        TimeBlock block = new TimeBlock(
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1),
                "\"Functional Programming Principles in Scala\" by Martin Odersky");
        List<TimeBlock> list = new ArrayList<>();
        list.add(block);
        Organization organization = new Organization("Coursera", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Luxoft", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1),
                "3 месяца обучения мобильным IN сетям (Берлин)");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Siemens AG", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1),
                "6 месяцев обучения цифровым телефонным сетям (Москва)");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Alcatel", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1),
                "Аспирантура (программист С, С++)");
        list = new ArrayList<>();
        list.add(block);

        block = new TimeBlock(
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1),
                "Инженер (программист Fortran, C)");
        list.add(block);

        organization = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1),
                "Закончил с отличием");
        list = new ArrayList<>();
        list.add(block);

        organization = new Organization("Заочная физико-техническая школа при МФТИ", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        ChapterSection data = new ChapterSection(organizations);
        resume.addSection(SectionType.EDUCATION, data);
    }
}
