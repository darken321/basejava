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
                dos.writeUTF(section.name());
                switch (section) {
                    case OBJECTIVE, PERSONAL -> {
                        writeWithException(dos, List.of(resume.getSection(section).getSections().toString()), str -> dos.writeUTF(str));
                    }
                    case ACHIEVEMENT, QUALIFICATIONS -> {
                        writeWithException(dos, (List<String>) (resume.getSection(section)).getSections(), dos::writeUTF);
                    }
                    case EXPERIENCE, EDUCATION -> {
                        writeWithException(dos, (List<Organization>) (resume.getSection(section)).getSections(), org -> {
                            dos.writeUTF(org.getName());
                            dos.writeUTF(org.getWebsite());
                            writeWithException(dos, org.getPeriods(), period -> {
                                dos.writeUTF(period.getStartDate());
                                dos.writeUTF(period.getEndDate());
                                dos.writeUTF(period.getTitle());
                                dos.writeUTF(period.getDescription());
                            });
                        });
                    }
                }
            }
        }
    }

    private <T> void writeWithException(DataOutputStream dos, Collection<T> collection, MyWriter<? super T> writer) throws IOException {
        Objects.requireNonNull(writer);
        dos.writeInt(collection.size());
        for (T item : collection) {
            writer.accept(item);
        }
    }

    private interface MyWriter<T> {
        void accept(T t) throws IOException;
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        Resume resume;
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            resume = new Resume(uuid, fullName);

            //переделать вот это
            Map<ContactType, String> contacts = readContactsWithException(dis, dis::readUTF);
            resume.setContacts(contacts);

            String sectionName;
            int sectionsCount = dis.readInt();
            for (int i = 0; i < sectionsCount; i++) {
                sectionName = dis.readUTF();
                switch (sectionName) {

                    case "OBJECTIVE", "PERSONAL" -> {
                        resume.setSection(SectionType.valueOf(sectionName),
                                new TextSection((readListWithException(dis, dis::readUTF)).get(0)));
                    }
                    case "ACHIEVEMENT", "QUALIFICATIONS" -> {
                        resume.setSection(SectionType.valueOf(sectionName),
                                new ListTextSection(readListWithException(dis, dis::readUTF)));

                    }
                    case "EXPERIENCE", "EDUCATION" -> {
                        List<Organization> organizations = readListWithException(dis, () ->
                                new Organization(dis.readUTF(), dis.readUTF(),
                                        readListWithException(dis, () -> new Period(dis.readUTF(), dis.readUTF(), dis.readUTF(), dis.readUTF()))));
                        resume.setSection(SectionType.valueOf(sectionName), new OrganizationSection(organizations));
                    }
                }
            }
        }
        return resume;
    }

    private <T> List<T> readListWithException(DataInputStream dis, MyReader<? super T> reader) throws IOException {
        Objects.requireNonNull(reader);
        int size = dis.readInt();
        List<T> collection = new ArrayList<>(size);
        for (int i = 0; i < size; i++) {
            collection.add((T) reader.accept());
        }
        return collection;
    }

    private <T> Map<ContactType, String> readContactsWithException(DataInputStream dis, MyReader<T> reader) throws IOException {
        Objects.requireNonNull(reader);
        int size = dis.readInt();
        Map<ContactType, String> map = new EnumMap<>(ContactType.class);
        for (int i = 0; i < size; i++) {
            map.put(ContactType.valueOf((String) reader.accept()), (String) reader.accept());
        }
        return map;
    }

    private interface MyReader<T> {
        T accept() throws IOException;
    }
}