package com.urise.webapp.storage.strategy;

import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;

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
            return resume;
            //TODO implements sections
        }
    }
}