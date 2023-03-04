package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.ContactType;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.SqlHelper;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SqlStorage implements Storage {
    SqlHelper sqlHelper = new SqlHelper();

    public SqlStorage() {
    }

    @Override
    public void clear() {
        sqlHelper.sqlExecute("DELETE FROM resume", PreparedStatement::execute);
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
                    try (PreparedStatement ps = conn.prepareStatement("DELETE FROM contact WHERE resume_uuid= ?;")) {
                        ps.setString(1, r.getUuid());
                        ps.execute();
                    }
                    addContacts(r, conn);
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
                    addContacts(r, conn);
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
                        saveContacts(rs, r);
                    } while (rs.next());
                    return r;
                });
    }

    private static void saveContacts(ResultSet rs, Resume r) throws SQLException {
        String value = rs.getString("value");
        ContactType type = ContactType.valueOf(rs.getString("type"));
        r.setContacts(type, value);
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
        resumes = sqlHelper.sqlExecute("SELECT * FROM resume ORDER BY full_name, uuid", statement -> {
            List<Resume> r = new ArrayList<>();
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                r.add(new Resume(rs.getString("uuid"), rs.getString("full_name")));
            }
            return r;
        });
        sqlHelper.sqlExecute("SELECT * FROM contact ORDER BY resume_uuid", statement -> {
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                String uuid = rs.getString("resume_uuid");
                for (Resume r : resumes) {
                    if (r.getUuid().equals(uuid)) {
                        saveContacts(rs, r);
                        break;
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

    private void addContacts(Resume r, Connection conn) throws SQLException {
        try (PreparedStatement ps = conn.prepareStatement("INSERT INTO contact (resume_uuid, type, value) VALUES (?,?,?)")) {
            for (Map.Entry<ContactType, String> e : r.getContacts().entrySet()) {
                ps.setString(1, r.getUuid());
                ps.setString(2, e.getKey().name());
                ps.setString(3, e.getValue());
                ps.addBatch();
            }
            ps.executeBatch();
        }
    }
}
