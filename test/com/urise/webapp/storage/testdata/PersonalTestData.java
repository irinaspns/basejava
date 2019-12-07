package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextSection;

public class PersonalTestData {

    void addData(Resume resume) {
        TextSection data = new TextSection();
        data.setText("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        resume.addData(SectionType.PERSONAL, data);
    }
}
