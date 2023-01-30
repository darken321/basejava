package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.*;

import java.io.*;
import java.util.EnumMap;
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
            //считал все (2) секции в EnumMap
            Map<SectionType, Section> section = r.getSections();
            //записал две секции в файл

            Section sectionObjective = section.get(SectionType.OBJECTIVE);
            dos.writeUTF(sectionObjective.getStringSection());

            Section sectionPersonal = section.get(SectionType.PERSONAL);
            dos.writeUTF(sectionPersonal.getStringSection());
//            dos.writeUTF(section.get(SectionType.OBJECTIVE).getStringSection());
//            dos.writeUTF(section.get(SectionType.PERSONAL).getStringSection());

            //TODO implements sections
        }
    }

    @Override
    public Resume doRead(InputStream is) throws IOException {
        try (DataInputStream dis = new DataInputStream(is)) {
            String uuid = dis.readUTF();
            String fullName = dis.readUTF();
            int size = dis.readInt();
            Resume resume = new Resume(uuid, fullName);
            EnumMap<ContactType, String> contacts = new EnumMap<>(ContactType.class);
            for (int i = 0; i < size; i++) {
                contacts.put(ContactType.valueOf(dis.readUTF()), dis.readUTF());
            }
            resume.setContacts(contacts);
            //завел новую мапу для секций
            EnumMap<SectionType, Section> section = new EnumMap<>(SectionType.class);

            //прочитал две секции и записал их в файл
            section.put(SectionType.OBJECTIVE, new TextSection(dis.readUTF()));
            section.put(SectionType.PERSONAL, new TextSection(dis.readUTF()));

            //добавил все секции в резюме
            resume.setSections(section);

            return resume;
            //TODO implements sections
        }
    }
}