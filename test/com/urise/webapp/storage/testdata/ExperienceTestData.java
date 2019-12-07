package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.*;

import java.time.LocalDate;

public class ExperienceTestData {

    public static final String TEST_URL = "https://javawebinar.github.io/";

    void addData(Resume resume) {
        ChapterSection data = new ChapterSection();

        Chapter chapter = new Chapter();
        chapter.setTitel("Java Online Projects");
        chapter.setUrl(TEST_URL);
        TimeBlock block = new TimeBlock();
        block.setFrom(LocalDate.of(2013, 10, 1));
        block.setTill(null);
        block.setSubTitel("Автор проекта.");
        block.addDescription("Создание, организация и проведение Java онлайн проектов и стажировок.");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Wrike");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2014, 10, 1));
        block.setTill(LocalDate.of(2016, 1, 1));
        block.setSubTitel("Старший разработчик (backend)");
        block.addDescription("Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("RIT Center");
        chapter.setUrl(null);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2012, 4, 1));
        block.setTill(LocalDate.of(2014, 10, 1));
        block.setSubTitel("Java архитектор");
        block.addDescription("Организация процесса разработки системы ERP для разных окружений: релизная политика, версионирование, ведение CI (Jenkins), миграция базы (кастомизация Flyway), конфигурирование системы (pgBoucer, Nginx), AAA via SSO. Архитектура БД и серверной части системы. Разработка интергационных сервисов: CMIS, BPMN2, 1C (WebServices), сервисов общего назначения (почта, экспорт в pdf, doc, html). Интеграция Alfresco JLAN для online редактирование из браузера документов MS Office. Maven + plugin development, Ant, Apache Commons, Spring security, Spring MVC, Tomcat,WSO2, xcmis, OpenCmis, Bonita, Python scripting, Unix shell remote scripting via ssh tunnels, PL/Python");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Luxoft (Deutsche Bank)");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2010, 12, 1));
        block.setTill(LocalDate.of(2012, 4, 1));
        block.setSubTitel("Ведущий программист");
        block.addDescription("Участие в проекте Deutsche Bank CRM (WebLogic, Hibernate, Spring, Spring MVC, SmartGWT, GWT, Jasper, Oracle). Реализация клиентской и серверной части CRM. Реализация RIA-приложения для администрирования, мониторинга и анализа результатов в области алгоритмического трейдинга. JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Highstock, Commet, HTML5.");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Yota");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2008, 6, 1));
        block.setTill(LocalDate.of(2010, 12, 1));
        block.setSubTitel("Ведущий специалист");
        block.addDescription("Дизайн и имплементация Java EE фреймворка для отдела \"Платежные Системы\" (GlassFish v2.1, v3, OC4J, EJB3, JAX-WS RI 2.1, Servlet 2.4, JSP, JMX, JMS, Maven2). Реализация администрирования, статистики и мониторинга фреймворка. Разработка online JMX клиента (Python/ Jython, Django, ExtJS)");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Enkata");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2007, 3, 1));
        block.setTill(LocalDate.of(2008, 6, 1));
        block.setSubTitel("Разработчик ПО");
        block.addDescription("Реализация клиентской (Eclipse RCP) и серверной (JBoss 4.2, Hibernate 3.0, Tomcat, JMS) частей кластерного J2EE приложения (OLAP, Data mining).");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Siemens AG");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2005, 1, 1));
        block.setTill(LocalDate.of(2007, 2, 1));
        block.setSubTitel("Разработчик ПО");
        block.addDescription("Разработка информационной модели, проектирование интерфейсов, реализация и отладка ПО на мобильной IN платформе Siemens @vantage (Java, Unix).");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Alcatel");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2005, 1, 1));
        block.setTill(LocalDate.of(2007, 2, 1));
        block.setSubTitel("Инженер по аппаратному и программному тестированию");
        block.addDescription("Тестирование, отладка, внедрение ПО цифровой телефонной станции Alcatel 1000 S12 (CHILL, ASM).");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        resume.addData(SectionType.EXPERIENCE, data);
    }
}
