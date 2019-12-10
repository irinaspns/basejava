package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

public class ContactTestData {

    void addData(Resume resume) {
        resume.addContact(ContactType.PHONE_NUMBER, "+7(921) 855-0482");
        resume.addContact(ContactType.SKYPE, "grigory.kislin");
        resume.addContact(ContactType.MAIL, "gkislin@yandex.ru");
        resume.addContact(ContactType.LINKED_IN, "Профиль LinkedIn");
        resume.addContact(ContactType.GIT_HUB, "Профиль GitHub");
        resume.addContact(ContactType.STACK_OVERFLOW, "Профиль Stackoverflow");
        resume.addContact(ContactType.HOME_PAGE, "Домашняя страница");
    }
}
