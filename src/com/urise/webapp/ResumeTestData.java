package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.Map;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = new Resume("uuid1", "Григорий Кислин");
        EnumMap<ContactType, String> contact = new EnumMap<>(ContactType.class);
        contact.put(ContactType.PHONE, "+7(921) 855-0482");
        contact.put(ContactType.SKYPE, "skype:grigory.kislin");
        contact.put(ContactType.MAIL, "gkislin@yandex.ru");
        contact.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contact.put(ContactType.GITHUB, "https://github.com/gkislin");
        contact.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contact.put(ContactType.HOMEPAGE, "http://gkislin.ru/");
        resume.setContacts(contact);

        System.out.println("\n" + resume.getUuid() + " " + resume.getFullName());
        for (Map.Entry<ContactType, String> c : resume.getContacts().entrySet()) {
            System.out.print(c.getKey().getTitle() + " ");
            System.out.println(c.getValue());
        }

        EnumMap<SectionType, AbstractSection> position = new EnumMap<>(SectionType.class);

        TextSection objective = new TextSection();
        objective.setTextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям");
        position.put(SectionType.OBJECTIVE, objective);

        TextSection personal = new TextSection();
        personal.setTextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры.");
        position.put(SectionType.PERSONAL, personal);

        ListTextSection achievement = new ListTextSection();
        ArrayList<String> stringsArray3 = new ArrayList<>();
        stringsArray3.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        stringsArray3.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        stringsArray3.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        stringsArray3.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        stringsArray3.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        stringsArray3.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        stringsArray3.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        achievement.setArraySection(stringsArray3);
        position.put(SectionType.ACHIEVEMENT, achievement);

        ListTextSection qualifications = new ListTextSection();
        ArrayList<String> stringsArray4 = new ArrayList<>();
        stringsArray4.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        stringsArray4.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        stringsArray4.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        stringsArray4.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        stringsArray4.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        stringsArray4.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        stringsArray4.add("Python: Django.");
        stringsArray4.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        stringsArray4.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        stringsArray4.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        stringsArray4.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        stringsArray4.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        stringsArray4.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        stringsArray4.add("Родной русский, английский \"upper intermediate\"");
        qualifications.setArraySection(stringsArray4);
        position.put(SectionType.QUALIFICATIONS, qualifications);

        OrganizationSection org1 = new OrganizationSection();
        Period p5 = new Period("10/2013", "Сейчас", "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок.");
        ArrayList<CompanySection> experience = new ArrayList<>();
        experience.add(new CompanySection("Java Online Projects","http://javaops.ru/", p5));
        Period p6 = new Period("10/2014", "01/2016", "Старший разработчик (backend)" ,"Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO.");
        experience.add(new CompanySection("Wrike", "https://www.wrike.com/", p6));
        org1.setClassSection(experience);
        position.put(SectionType.EXPERIENCE, org1);

        OrganizationSection org2 = new OrganizationSection();
        Period p7 = new Period("03/2013", "05/2013", "'Functional Programming Principles in Scala' by Martin Odersky", "");
        ArrayList<CompanySection> education = new ArrayList<>();
        education.add(new CompanySection("Coursera","https://www.coursera.org/course/progfun", p7));
        Period p8 = new Period("03/2011", "04/2011", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'" ,"");
        education.add(new CompanySection("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", p8));
        org2.setClassSection(education);
        position.put(SectionType.EDUCATION, org2);

        resume.setSections(position);
        for (Map.Entry<SectionType, AbstractSection> c : resume.getSections().entrySet()) {
            System.out.println("\n    " + c.getKey().getTitle());
            c.getValue().printAll();
        }
    }
}