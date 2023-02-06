package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

public class DataStreamStrategy implements StreamStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();
            dos.writeInt(contacts.size());
            for (Map.Entry<ContactType, String> entry : contacts.entrySet()) {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            }
            dos.writeInt(resume.getSectionsSet().size());
            for (SectionType section : resume.getSectionsSet()) {
                switch (section) {

                    case OBJECTIVE, PERSONAL -> {
                        dos.writeUTF(section.name());
                        dos.writeUTF(resume.getSection(section).getSections().toString());
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        dos.writeUTF(section.name());
                        List<String> sections = (List<String>) (resume.getSection(section)).getSections();
                        dos.writeInt(sections.size());
                        for (String s : sections) {
                            dos.writeUTF(s);
                        }
                    }
                    case EXPERIENCE, EDUCATION -> {
                        dos.writeUTF(section.name());
                        List<Organization> sections = (List<Organization>) (resume.getSection(section)).getSections();
                        dos.writeInt(sections.size());
                        for (Organization org : sections) {
                            dos.writeUTF(org.getName());
                            dos.writeUTF(org.getWebsite());
                            dos.writeInt(org.getPeriods().size());
                            for (Period period : org.getPeriods()) {
                                dos.writeUTF(period.getStartDate());
                                dos.writeUTF(period.getEndDate());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            }
                        }
                    }
                }
            }
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            {
                String uuid = dis.readUTF();
                String fullName = dis.readUTF();
                resume = new Resume(uuid, fullName);
                int size = dis.readInt();
                EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
                for (int i = 0; i < size; i++) {
                    contacts.put(ContactType.valueOf(dis.readUTF()), dis.readUTF());
                }
                resume.setContacts(contacts);
            }

            String sectionName;
            int sectionsCount = dis.readInt();
            while (sectionsCount > 0) {
                sectionName = dis.readUTF();
                switch (sectionName) {

                    case "OBJECTIVE", "PERSONAL" -> {
                        resume.setSection(SectionType.valueOf(sectionName), new TextSection(dis.readUTF()));
                    }
                    case "ACHIEVEMENT", "QUALIFICATIONS" -> {
                        int size = dis.readInt();
                        List<String> sections = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            sections.add(dis.readUTF());
                        }
                        resume.setSection(SectionType.valueOf(sectionName), new ListTextSection(sections));
                    }
                    case "EXPERIENCE", "EDUCATION" -> {
                        int size = dis.readInt();
                        List<Organization> organizations = new ArrayList<>(size);
                        for (int i = 0; i < size; i++) {
                            String name = dis.readUTF();
                            String website = dis.readUTF();
                            int periodsNum = dis.readInt();
                            List<Period> periods = new ArrayList<>(periodsNum);
                            for (int j = 0; j < periodsNum; j++) {
                                periods.add(new Period(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()));
                            }
                            organizations.add(new Organization(name, website, periods));
                        }
                        resume.setSection(SectionType.valueOf(sectionName), new OrganizationSection(organizations));
                    }
                }
                sectionsCount--;
            }
        }
        return resume;
    }
}