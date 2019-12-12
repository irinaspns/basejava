package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        addContacts(resume);
        addSections(resume);

        new ObjectiveTestData().addData(resume);
        new PersonalTestData().addData(resume);
        resume.addSection(SectionType.ACHIEVEMENT, new AchievementsTestData().getData());
        new QualificationTestData().addData(resume);
        new ExperienceTestData().addData(resume);
        new EducationTestData().addData(resume);
    }

    private static void addContacts(Resume resume) {
        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKED_IN, "Профиль LinkedIn");
        resume.addContact(ContactType.GIT_HUB, "Профиль GitHub");
        resume.addContact(ContactType.STACK_OVERFLOW, "Профиль Stackoverflow");
        resume.addContact(ContactType.HOME_PAGE, "Домашняя страница");
    }

    private static void addSections(Resume resume) {
        resume.addSection(SectionType.OBJECTIVE, addObjective());
        resume.addSection(SectionType.PERSONAL, addPersonal());
    }

    private static TextSection addObjective() {
        return new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
    }

    private static TextSection addPersonal() {
        return new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
    }
}
