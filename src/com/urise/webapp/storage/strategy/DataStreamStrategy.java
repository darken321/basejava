package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements StreamStrategy {

    @Override
    public void doWrite(Resume r, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(r.getUuid());
            dos.writeUTF(r.getFullName());
            Map<ContactType, String> contacts = r.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            //считал все (4) секции в EnumMap
            Map<SectionType, Section> sections = r.getSections();

            //записал две секции в файл
            Section objective = sections.get(SectionType.OBJECTIVE);
            dos.writeUTF(((TextSection) objective).getTextSection());
            Section personal = sections.get(SectionType.PERSONAL);
            dos.writeUTF(((TextSection) personal).getTextSection());

            {
                //беру секцию достижения
                Section achievement = sections.get(SectionType.ACHIEVEMENT);
                //собираю лист стрингов в массив
                List<String> achievementArray = ((ListTextSection) achievement).getList();

                //длина массива, пишу ее в файл
                dos.writeInt(achievementArray.size());

                for (String array : achievementArray) {
                    dos.writeUTF(array);
                }
            }
            {
                //беру секцию квалификация
                Section qualifications = sections.get(SectionType.QUALIFICATIONS);
                //собираю лист стрингов в массив
                List<String> qualificationsArray = ((ListTextSection) qualifications).getList();

                //длина массива, пишу ее в файл
                dos.writeInt(qualificationsArray.size());

                for (String array : qualificationsArray) {
                    dos.writeUTF(array);
                }
            }
            {
                //беру секцию опыт
                Section experience = sections.get(SectionType.EXPERIENCE);
                //собираю лист организаций в массив
                List<Organization> organizationsArray = ((OrganizationSection) experience).getOrganizations();

                //пишу в файл число организаций
                dos.writeInt(organizationsArray.size());
                for (Organization organization : organizationsArray) {
                    dos.writeUTF(organization.getName());
                    dos.writeUTF(organization.getWebsite());
                    dos.writeInt(organization.getPeriod().size());
                    for (Period period : organization.getPeriod()) {
                        dos.writeUTF(period.getStartDate());
                        dos.writeUTF(period.getEndDate());
                        dos.writeUTF(period.getTitle());
                        dos.writeUTF(period.getDescription());
                    }
                }
            }
            {
                //беру секцию обучение
                Section education = sections.get(SectionType.EDUCATION);
                //собираю лист организаций в массив
                List<Organization> educationArray = ((OrganizationSection) education).getOrganizations();

                //пишу ее в файл число организаций
                dos.writeInt(educationArray.size());
                for (Organization organization : educationArray) {
                    dos.writeUTF(organization.getName());
                    dos.writeUTF(organization.getWebsite());
                    dos.writeInt(organization.getPeriod().size());
                    for (Period period : organization.getPeriod()) {
                        dos.writeUTF(period.getStartDate());
                        dos.writeUTF(period.getEndDate());
                        dos.writeUTF(period.getTitle());
                        dos.writeUTF(period.getDescription());
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            Resume resume;
            {
                String uuid = dis.readUTF();
                String fullName = dis.readUTF();
                int size = dis.readInt();
                resume = new Resume(uuid, fullName);
                EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
                for (int i = 0; i < size; i++) {
                    contacts.put(ContactType.valueOf(dis.readUTF()), dis.readUTF());
                }
                resume.setContacts(contacts);
            }
            EnumMap<SectionType, Section> section = new EnumMap<>(SectionType.class);
            section.put(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
            section.put(SectionType.PERSONAL, new TextSection(dis.readUTF()));
            {
                List<String> stringsArray = new ArrayList<>();
                int size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    stringsArray.add(dis.readUTF());
                }
                section.put(SectionType.ACHIEVEMENT, new ListTextSection(stringsArray));
            }
            {
                List<String> stringsArray = new ArrayList<>();
                int size = dis.readInt();
                for (int i = 0; i < size; i++) {
                    stringsArray.add(dis.readUTF());
                }
                section.put(SectionType.QUALIFICATIONS, new ListTextSection(stringsArray));
            }
            {
                //беру секцию опыт
                List<Organization> organizations = new ArrayList<>();
                //собираю лист организаций в массив
                int size = dis.readInt();
                //цикл по организациям - опыт
                for (int i = 0; i < size; i++) {
                    String name = dis.readUTF();
                    String website = dis.readUTF();
                    int periodSize = dis.readInt();
                    List<Period> periods = new ArrayList<>();
                    for (int j = 0; j < periodSize; j++) {
                        periods.add(new Period(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()));
                    }
                    organizations.add(new Organization(name, website, periods));
                }
                section.put(SectionType.EXPERIENCE, new OrganizationSection(organizations));
            }
            {
                //беру секцию обучение
                List<Organization> organizations = new ArrayList<>();
                //собираю лист организаций в массив
                int size = dis.readInt();
                //цикл по организациям - обучение
                for (int i = 0; i < size; i++) {
                    String name = dis.readUTF();
                    String website = dis.readUTF();
                    int periodSize = dis.readInt();
                    List<Period> periods = new ArrayList<>();
                    for (int j = 0; j < periodSize; j++) {
                        periods.add(new Period(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()));
                    }
                    organizations.add(new Organization(name, website, periods));
                }
                section.put(SectionType.EDUCATION, new OrganizationSection(organizations));
            }
            resume.setSections(section);
            return resume;
        }
    }
}