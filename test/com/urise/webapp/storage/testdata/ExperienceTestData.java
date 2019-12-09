package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExperienceTestData {

    public static final String TEST_URL = "https://javawebinar.github.io/";

    void addData(Resume resume) {
        List<Organization> organizations = new ArrayList<>();

        TimeBlock block = new TimeBlock(
                LocalDate.of(2013, 10, 1),
                LocalDate.now(),
                "Автор проекта.");
        block.setDescription("Создание, организация и проведение Java онлайн проектов и стажировок.");
        List list = new ArrayList<>();
        list.add(block);
        Organization organization = new Organization("Java Online Projects", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2014, 10, 1),
                LocalDate.of(2016, 1, 1),
                "Старший разработчик (backend)");
        block.setDescription("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Wrike", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2012, 4, 1),
                LocalDate.of(2014, 10, 1),
                "Java архитектор");
        block.setDescription("Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("RIT Center", list);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2010, 12, 1),
                LocalDate.of(2012, 4, 1),
                "Ведущий программист");
        block.setDescription("Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Luxoft (Deutsche Bank)", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2008, 6, 1),
                LocalDate.of(2010, 12, 1),
                "Ведущий специалист");
        block.setDescription("Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Yota", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2007, 3, 1),
                LocalDate.of(2008, 6, 1),
                "Разработчик ПО");
        block.setDescription("Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Enkata", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1),
                "Разработчик ПО");
        block.setDescription("Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Siemens AG", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        block = new TimeBlock(
                LocalDate.of(2005, 1, 1),
                LocalDate.of(2007, 2, 1),
                "Инженер по аппаратному и программному тестированию");
        block.setDescription("Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        list = new ArrayList<>();
        list.add(block);
        organization = new Organization("Alcatel", list);
        organization.setUrl(TEST_URL);
        organizations.add(organization);

        ChapterSection data = new ChapterSection(organizations);
        resume.addSection(SectionType.EXPERIENCE, data);
    }
}
