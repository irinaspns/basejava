package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("Григорий Кислин");

        new ContactTestData().addData(resume);
        new ObjectiveTestData().addData(resume);
        new PersonalTestData().addData(resume);
        resume.addSection(SectionType.ACHIEVEMENT, new AchievementsTestData().getData());
        new QualificationTestData().addData(resume);
        new ExperienceTestData().addData(resume);
        new EducationTestData().addData(resume);
    }
}
