package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class EducationTestData {

    public static final String TEST_URL = "https://javawebinar.github.io/";

    void addData(Resume resume) {

        List<Organization> organizations = new ArrayList<>();

        Position position = new Position(
                LocalDate.of(2013, 3, 1),
                LocalDate.of(2013, 5, 1),
                "\"Functional Programming Principles in Scala\" by Martin Odersky",
                null);
        List<Position> list = new ArrayList<>();
        list.add(position);
        Organization organization = new Organization("Coursera", TEST_URL, list);
        organizations.add(organization);

        position = new Position(
                LocalDate.of(2011, 3, 1),
                LocalDate.of(2011, 4, 1),
                "Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"",
                null);
        list = new ArrayList<>();
        list.add(position);
        organization = new Organization("Luxoft", TEST_URL, list);
        organizations.add(organization);

        position = new Position(
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2005, 4, 1),
                "3 месяца обучения мобильным IN сетям (Берлин)",
                null);
        list = new ArrayList<>();
        list.add(position);
        organization = new Organization("Siemens AG", TEST_URL, list);
        organizations.add(organization);

        position = new Position(
                LocalDate.of(1997, 9, 1),
                LocalDate.of(1998, 3, 1),
                "6 месяцев обучения цифровым телефонным сетям (Москва)",
                null);
        list = new ArrayList<>();
        list.add(position);
        organization = new Organization("Alcatel", TEST_URL, list);
        organizations.add(organization);

        position = new Position(
                LocalDate.of(1993, 9, 1),
                LocalDate.of(1996, 7, 1),
                "Аспирантура (программист С, С++)",
                null);
        list = new ArrayList<>();
        list.add(position);

        position = new Position(
                LocalDate.of(1987, 9, 1),
                LocalDate.of(1993, 7, 1),
                "Инженер (программист Fortran, C)",
                null);
        list.add(position);

        organization = new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", TEST_URL, list);
        organizations.add(organization);

        position = new Position(
                LocalDate.of(1984, 9, 1),
                LocalDate.of(1987, 6, 1),
                "Закончил с отличием",
                null);
        list = new ArrayList<>();
        list.add(position);

        organization = new Organization("Заочная физико-техническая школа при МФТИ", TEST_URL, list);
        organizations.add(organization);

        OrganizationSection data = new OrganizationSection(organizations);
        resume.addSection(SectionType.EDUCATION, data);
    }
}
