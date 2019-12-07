package com.urise.webapp.storage;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Grigorij Kislin");

        makeContacten(resume);

        resume.addData(SectionType.OBJECTIVE, new TextSection(SectionType.OBJECTIVE));
        resume.getData().get(SectionType.OBJECTIVE).setText("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");

//    private TextSection personal  = new TextSection(SectionType.PERSONAL);
//    private TextSection achievements = new TextSection(SectionType.ACHIEVEMENT);
//    private TextSection qualification = new TextSection(SectionType.QUALIFICATIONS);
//    private ChapterSection experience = new ChapterSection(SectionType.EXPERIENCE);
//    private ChapterSection education = new ChapterSection(SectionType.EDUCATION);

    }

    private static void makeContacten(Resume resume) {
        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKED_IN, "Profil LinkedIn");
        resume.addContact(ContactType.GIT_HUB, "Profil GitHub");
        resume.addContact(ContactType.STACK_OVERFLOW, "Profil Stackoverflow");
        resume.addContact(ContactType.HOME_PAGE, "Home page");
    }
}
