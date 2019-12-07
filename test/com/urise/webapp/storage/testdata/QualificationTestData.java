package com.urise.webapp.storage.testdata;

import com.urise.webapp.model.Resume;
import com.urise.webapp.model.SectionType;
import com.urise.webapp.model.TextListSection;

public class QualificationTestData {

    void addData(Resume resume) {
        TextListSection data = new TextListSection();
        data.addItem("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        data.addItem("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        data.addItem("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle,");
        data.addItem("MySQL, SQLite, MS SQL, HSQLDB");
        data.addItem("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy,");
        data.addItem("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts,");
        data.addItem("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        data.addItem("Python: Django.");
        data.addItem("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        data.addItem("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        data.addItem("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        data.addItem("Инструменты: Maven + plugin development, Gradle, настройка Ngnix,");
        data.addItem("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer.");
        data.addItem("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        data.addItem("Родной русский, английский \"upper intermediate\"");
        resume.addData(SectionType.ACHIEVEMENT, data);
    }
}
