package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.*;

import java.time.LocalDate;

public class EducationTestData {

    public static final String TEST_URL = "https://javawebinar.github.io/";

    void addData(Resume resume) {

        ChapterSection data = new ChapterSection();

        Chapter chapter = new Chapter();
        chapter.setTitel("Coursera");
        chapter.setUrl(TEST_URL);
        TimeBlock block = new TimeBlock();
        block.setFrom(LocalDate.of(2013, 3, 1));
        block.setTill(LocalDate.of(2013, 5, 1));
        block.setSubTitel("\"Functional Programming Principles in Scala\" by Martin Odersky");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Luxoft");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2011, 3, 1));
        block.setTill(LocalDate.of(2011, 4, 1));
        block.setSubTitel("Курс \"Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.\"");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Siemens AG");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(2005, 1, 1));
        block.setTill(LocalDate.of(2005, 4, 1));
        block.setSubTitel("3 месяца обучения мобильным IN сетям (Берлин)");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Alcatel");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(1997, 9, 1));
        block.setTill(LocalDate.of(1998, 3, 1));
        block.setSubTitel("6 месяцев обучения цифровым телефонным сетям (Москва)");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики");
        chapter.setUrl(TEST_URL);

        block = new TimeBlock();
        block.setFrom(LocalDate.of(1993, 9, 1));
        block.setTill(LocalDate.of(1996, 7, 1));
        block.setSubTitel("Аспирантура (программист С, С++)");
        chapter.addSubChapters(block);

        block = new TimeBlock();
        block.setFrom(LocalDate.of(1987, 9, 1));
        block.setTill(LocalDate.of(1993, 7, 1));
        block.setSubTitel("Инженер (программист Fortran, C)");
        chapter.addSubChapters(block);

        data.addChapter(chapter);

        chapter = new Chapter();
        chapter.setTitel("Заочная физико-техническая школа при МФТИ");
        chapter.setUrl(TEST_URL);
        block = new TimeBlock();
        block.setFrom(LocalDate.of(1984, 9, 1));
        block.setTill(LocalDate.of(1987, 6, 1));
        block.setSubTitel("Закончил с отличием");
        chapter.addSubChapters(block);
        data.addChapter(chapter);

        resume.addData(SectionType.EDUCATION, data);
    }
}
