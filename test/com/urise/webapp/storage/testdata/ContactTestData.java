package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.Contact;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

public class ContactTestData {

    void addData(Resume resume) {
        resume.addContact(ContactType.PHONE_NUMBER, new Contact("+7(921) 855-0482"));
        resume.addContact(ContactType.SKYPE, new Contact("grigory.kislin"));
        resume.addContact(ContactType.MAIL, new Contact("gkislin@yandex.ru"));
        resume.addContact(ContactType.LINKED_IN, new Contact("Профиль LinkedIn"));
        resume.addContact(ContactType.GIT_HUB, new Contact("Профиль GitHub"));
        resume.addContact(ContactType.STACK_OVERFLOW, new Contact("Профиль Stackoverflow"));
        resume.addContact(ContactType.HOME_PAGE, new Contact("Домашняя страница"));
    }
}
