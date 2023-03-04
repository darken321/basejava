package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    SqlHelper sqlHelper = new SqlHelper();

    public SqlStorage() {
    }

    @Override
    public void clear() {
        sqlHelper.sqlExecute("DELETE FROM resume", statement -> {
            return statement.execute();
        });
    }

    @Override
    public void update(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("UPDATE resume SET full_name = ? WHERE uuid = ?")) {
                        ps.setString(1, r.getFullName());
                        ps.setString(2, r.getUuid());
                        if (ps.executeUpdate() == 0) {
                            throw new NotExistStorageException(r.getUuid());
                        }
                    }
                    //удаление контактов
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid= ?;")) {
                        ps.setString(1, r.getUuid());
                        ps.execute();
                    }
                    //TODO дублирование кода с save
                    //добавление новых контактов
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                        for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                            ps.setString(1, r.getUuid());
                            ps.setString(2, e.getKey().name());
                            ps.setString(3, e.getValue());
                            ps.addBatch();
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
    }

    @Override
    public void save(Resume r) {
        sqlHelper.transactionalExecute(conn -> {
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO resume (uuid, full_name) VALUES (?,?)")) {
                        ps.setString(1, r.getUuid());
                        ps.setString(2, r.getFullName());
                        ps.execute();
                    }
                    //TODO дублирование кода с update
                    try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
                        for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                            ps.setString(1, r.getUuid());
                            ps.setString(2, e.getKey().name());
                            ps.setString(3, e.getValue());
                            ps.addBatch();
                        }
                        ps.executeBatch();
                    }
                    return null;
                }
        );
    }

    @Override
    public Resume get(String uuid) {
        return sqlHelper.sqlExecute("""
                        SELECT * FROM resume r\s
                            LEFT JOIN contact c\s
                            ON r.uuid = c.resume_uuid\s
                         WHERE r.uuid = ?""",
                statement -> {
                    statement.setString(1, uuid);
                    ResultSet rs = statement.executeQuery();
                    if (!rs.next()) {
                        throw new NotExistStorageException(uuid);
                    }
                    Resume r = new Resume(uuid, rs.getString("full_name"));
                    do {
                        String value = rs.getString("value");
                        ContactType type = ContactType.valueOf(rs.getString("type"));
                        r.setContacts(type, value);
                    } while (rs.next());
                    return r;
                });
    }

    @Override
    public void delete(String uuid) {
        sqlHelper.sqlExecute("DELETE FROM resume r WHERE r.uuid = ?", statement -> {
            statement.setString(1, uuid);
            if (statement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        List<Resume> resumes;
        //собрали все uuid и full_name
        resumes = sqlHelper.sqlExecute("SELECT * FROM resume ORDER BY full_name, uuid", statement -> {
            List<Resume> r = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                r.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return r;
        });
        //собрать контакты
         sqlHelper.sqlExecute("SELECT * FROM contact ORDER BY resume_uuid", statement -> {
            ResultSet rs = statement.executeQuery();
            //работа с результатами таблицы:
            ContactType c;

            while (rs.next()) { // цикл по строкам таблицы
                String uuid = rs.getString("resume_uuid");
                System.out.print(rs.getString("resume_uuid") + "  ");

                String typeString = rs.getString("type");
                c = ContactType.valueOf(typeString);
                System.out.print(rs.getString("type") + "  ");

                String value = rs.getString("value");
                System.out.print(rs.getString("value") + "\n");
                for (Resume r : resumes) {
                    if (r.getUuid().equals(uuid)) {
                        r.setContacts(c,value);
                    }
                }
            }
            return resumes;
        });
        return resumes;
    }

    @Override
    public int size() {
        return sqlHelper.sqlExecute("SELECT count(*) FROM resume", statement -> {
            ResultSet rs = statement.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}