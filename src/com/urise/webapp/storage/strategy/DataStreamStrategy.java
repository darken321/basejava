package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.*;

public class DataStreamStrategy implements StreamStrategy {

    @Override
    public void doWrite(Resume resume, OutputStream os) throws IOException {
        try (DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeUTF(resume.getUuid());
            dos.writeUTF(resume.getFullName());
            Map<ContactType, String> contacts = resume.getContacts();

            writeWithException(dos, contacts.entrySet(), entry -> {
                dos.writeUTF(entry.getKey().name());
                dos.writeUTF(entry.getValue());
            });

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
    //должен получиться некий метод writeWithException (...) throws IOException (который заменит стандартный forEach),
    // который как параметры принимает коллекцию (в буквальном смысле Collection), DataOutputStream и твой функциональный интерфейс.
// <T> writeCollection - типизированный метод,
// на вход подается коллекция collection
// с типом Т
// в конце кусок кода, который будет принимать элемент коллекции.
// в конце передается интерфейс writer типа Consumer, который работает с типом данных Т


    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, MyConsumer<? super T> writer) throws IOException {
        Objects.requireNonNull(writer);
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.accept(item);
        }
    }
    private interface MyConsumer <T> {
        void accept(T t) throws IOException;
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {

            String uuid = dis.readUTF();
            String fullName = dis.readUTF();

            resume = new Resume(uuid, fullName);
            int size = dis.readInt();
            Map<ContactType, String> contacts = new EnumMap<>(ContactType.class);
            for (int i = 0; i < size; i++) {
                contacts.put(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            resume.setContacts(contacts);

            String sectionName;
            int sectionsCount = dis.readInt();
            while (sectionsCount > 0) {
                sectionName = dis.readUTF();
                switch (sectionName) {

                    case "OBJECTIVE", "PERSONAL" -> {
                        resume.setSection(SectionType.valueOf(sectionName), new TextSection(dis.readUTF()));
                    }
                    case "ACHIEVEMENT", "QUALIFICATIONS" -> {
                        size = dis.readInt();
                        List<String> sections = new ArrayList<>();
                        for (int i = 0; i < size; i++) {
                            sections.add(dis.readUTF());
                        }
                        resume.setSection(SectionType.valueOf(sectionName), new ListTextSection(sections));
                    }
                    case "EXPERIENCE", "EDUCATION" -> {
                        size = dis.readInt();
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