package com.urise.webapp;

import com.urise.webapp.model.*;

import java.util.*;

public class ResumeTestData {

    public static void main(String[] args) {
        Resume resume = ResumeTestData.fillData("uuid1", "Григорий Кислин");

        System.out.println("\n" + resume.getUuid() + " " + resume.getFullName());
        for (Map.Entry<ContactType, String> c : resume.getContacts().entrySet()) {
            System.out.print(c.getKey().getTitle() + " ");
            System.out.println(c.getValue());
        }

        for (SectionType st : SectionType.values()) {
            System.out.println("\n    " + st.getTitle());
            resume.getSection(st).printAll();
        }
    }

    public static Resume fillData(String uuid, String fullName) {

        Resume resume = new Resume(uuid, fullName);

        Map<ContactType, String> contact = new EnumMap<>(ContactType.class);
        contact.put(ContactType.PHONE, "+7(921) 855-0482");
        contact.put(ContactType.SKYPE, "skype:grigory.kislin");
        contact.put(ContactType.MAIL, "gkislin@yandex.ru");
        contact.put(ContactType.LINKEDIN, "https://www.linkedin.com/in/gkislin");
        contact.put(ContactType.GITHUB, "https://github.com/gkislin");
        contact.put(ContactType.STACKOVERFLOW, "https://stackoverflow.com/users/548473");
        contact.put(ContactType.HOMEPAGE, "http://gkislin.ru/");
        resume.setContacts(contact);

        resume.setSection(SectionType.OBJECTIVE,
                new TextSection("Ведущий стажировок и корпоративного обучения по Java Web и Enterprise технологиям"));

        resume.setSection(SectionType.PERSONAL,
                new TextSection("Аналитический склад ума, сильная логика, креативность, инициативность. Пурист кода и архитектуры."));

        List<String> stringsArray1 = new ArrayList<>();
        stringsArray1.add("Организация команды и успешная реализация Java проектов для сторонних заказчиков: приложения автопарк на стеке Spring Cloud/микросервисы, система мониторинга показателей спортсменов на Spring Boot, участие в проекте МЭШ на Play-2, многомодульный Spring Boot + Vaadin проект для комплексных DIY смет");
        stringsArray1.add("С 2013 года: разработка проектов \"Разработка Web приложения\",\"Java Enterprise\", \"Многомодульный maven. Многопоточность. XML (JAXB/StAX). Веб сервисы (JAX-RS/SOAP). Удаленное взаимодействие (JMS/AKKA)\". Организация онлайн стажировок и ведение проектов. Более 3500 выпускников.");
        stringsArray1.add("Реализация двухфакторной аутентификации для онлайн платформы управления проектами Wrike. Интеграция с Twilio, DuoSecurity, Google Authenticator, Jira, Zendesk.");
        stringsArray1.add("Налаживание процесса разработки и непрерывной интеграции ERP системы River BPM. Интеграция с 1С, Bonita BPM, CMIS, LDAP. Разработка приложения управления окружением на стеке: Scala/Play/Anorm/JQuery. Разработка SSO аутентификации и авторизации различных ERP модулей, интеграция CIFS/SMB java сервера.");
        stringsArray1.add("Реализация c нуля Rich Internet Application приложения на стеке технологий JPA, Spring, Spring-MVC, GWT, ExtGWT (GXT), Commet, HTML5, Highstock для алгоритмического трейдинга.");
        stringsArray1.add("Создание JavaEE фреймворка для отказоустойчивого взаимодействия слабо-связанных сервисов (SOA-base архитектура, JAX-WS, JMS, AS Glassfish). Сбор статистики сервисов и информации о состоянии через систему мониторинга Nagios. Реализация онлайн клиента для администрирования и мониторинга системы по JMX (Jython/ Django).");
        stringsArray1.add("Реализация протоколов по приему платежей всех основных платежных системы России (Cyberplat, Eport, Chronopay, Сбербанк), Белоруcсии(Erip, Osmp) и Никарагуа.");
        resume.setSection(SectionType.ACHIEVEMENT, new ListTextSection(stringsArray1));

        List<String> stringsArray2 = new ArrayList<>();
        stringsArray2.add("JEE AS: GlassFish (v2.1, v3), OC4J, JBoss, Tomcat, Jetty, WebLogic, WSO2");
        stringsArray2.add("Version control: Subversion, Git, Mercury, ClearCase, Perforce");
        stringsArray2.add("DB: PostgreSQL(наследование, pgplsql, PL/Python), Redis (Jedis), H2, Oracle, MySQL, SQLite, MS SQL, HSQLDB");
        stringsArray2.add("Languages: Java, Scala, Python/Jython/PL-Python, JavaScript, Groovy");
        stringsArray2.add("XML/XSD/XSLT, SQL, C/C++, Unix shell scripts");
        stringsArray2.add("Java Frameworks: Java 8 (Time API, Streams), Guava, Java Executor, MyBatis, Spring (MVC, Security, Data, Clouds, Boot), JPA (Hibernate, EclipseLink), Guice, GWT(SmartGWT, ExtGWT/GXT), Vaadin, Jasperreports, Apache Commons, Eclipse SWT, JUnit, Selenium (htmlelements).");
        stringsArray2.add("Python: Django.");
        stringsArray2.add("JavaScript: jQuery, ExtJS, Bootstrap.js, underscore.js");
        stringsArray2.add("Scala: SBT, Play2, Specs2, Anorm, Spray, Akka");
        stringsArray2.add("Технологии: Servlet, JSP/JSTL, JAX-WS, REST, EJB, RMI, JMS, JavaMail, JAXB, StAX, SAX, DOM, XSLT, MDB, JMX, JDBC, JPA, JNDI, JAAS, SOAP, AJAX, Commet, HTML5, ESB, CMIS, BPMN2, LDAP, OAuth1, OAuth2, JWT.");
        stringsArray2.add("Инструменты: Maven + plugin development, Gradle, настройка Ngnix");
        stringsArray2.add("администрирование Hudson/Jenkins, Ant + custom task, SoapUI, JPublisher, Flyway, Nagios, iReport, OpenCmis, Bonita, pgBouncer");
        stringsArray2.add("Отличное знание и опыт применения концепций ООП, SOA, шаблонов проектрирования, архитектурных шаблонов, UML, функционального программирования");
        stringsArray2.add("Родной русский, английский \"upper intermediate\"");
        resume.setSection(SectionType.QUALIFICATIONS, new ListTextSection(stringsArray2));

        List<Organization> experience = new ArrayList<>();
        List<Period> period1 = new ArrayList<>();
        period1.add(new Period("10/2013", "Сейчас", "Автор проекта.", "Создание, организация и проведение Java онлайн проектов и стажировок."));
        experience.add(new Organization("Java Online Projects", "http://javaops.ru/", period1));
        List<Period> period2 = new ArrayList<>();
        period2.add(new Period("10/2014", "01/2016", "Старший разработчик (backend)", "Проектирование и разработка онлайн платформы управления проектами Wrike (Java 8 API, Maven, Spring, MyBatis, Guava, Vaadin, PostgreSQL, Redis). Двухфакторная аутентификация, авторизация по OAuth1, OAuth2, JWT SSO."));
        experience.add(new Organization("Wrike", "https://www.wrike.com/", period2));
        resume.setSection(SectionType.EXPERIENCE, new OrganizationSection(experience));

        List<Organization> education = new ArrayList<>();
        List<Period> period3 = new ArrayList<>();
        period3.add(new Period("03/2013", "05/2013", "'Functional Programming Principles in Scala' by Martin Odersky", ""));
        education.add(new Organization("Coursera", "https://www.coursera.org/course/progfun", period3));
        List<Period> period4 = new ArrayList<>();
        period4.add(new Period("03/2011", "04/2011", "Курс 'Объектно-ориентированный анализ ИС. Концептуальное моделирование на UML.'", ""));
        education.add(new Organization("Luxoft", "http://www.luxoft-training.ru/training/catalog/course.html?ID=22366", period4));
        List<Period> period5 = new ArrayList<>();
        period5.add(new Period("09/1993", "07/1996", "Аспирантура (программист С, С++)", ""));
        period5.add(new Period("09/1987", "07/1993", "Инженер (программист Fortran, C)", ""));
        education.add(new Organization("Санкт-Петербургский национальный исследовательский университет информационных технологий, механики и оптики", "http://www.ifmo.ru/", period5));
        resume.setSection(SectionType.EDUCATION, new OrganizationSection(education));

        return resume;
    }
}