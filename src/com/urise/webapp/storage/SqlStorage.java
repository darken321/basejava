package com.urise.webapp.storage;

import com.urise.webapp.exception.NotExistStorageException;
import com.urise.webapp.model.Resume;
import com.urise.webapp.sql.ConnectionFactory;
import com.urise.webapp.sql.SqlHelper;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class SqlStorage implements Storage {
    public ConnectionFactory connectionFactory = null;

    public SqlStorage(String dbUrl, String dbUser, String dbPassword) {
        connectionFactory = () -> DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    @Override
    public void clear() {
        SqlHelper.sqlExecute(connectionFactory,"DELETE FROM resume", statement -> {
            return statement.execute();
        });
    }

    @Override
    public void update(Resume r) {
        SqlHelper.sqlExecute(connectionFactory, "UPDATE resume SET full_name = ? WHERE uuid = ?", statement -> {
            statement.setString(1, r.getFullName());
            statement.setString(2, r.getUuid());
            if (statement.executeUpdate() == 0) {
                throw new NotExistStorageException(r.getUuid());
            }
            return null;
        });
    }

    public void save(Resume r) {
        SqlHelper.sqlExecute(connectionFactory,"INSERT INTO resume (uuid, full_name) VALUES (?, ?)", statement ->{
            statement.setString(1, r.getUuid());
            statement.setString(2, r.getFullName());
            statement.execute();
            return null;
        });
    }

    @Override
    public Resume get(String uuid) {
        String fullName = SqlHelper.sqlExecute(connectionFactory, "SELECT * FROM resume r WHERE r.uuid = ?", statement -> {
            statement.setString(1, uuid);
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                throw new NotExistStorageException(uuid);
            }
            return rs.getString("full_name");
        });
        return new Resume(uuid, fullName);
    }

    @Override
    public void delete(String uuid) {
        SqlHelper.sqlExecute(connectionFactory, "DELETE FROM resume r WHERE r.uuid = ?", statement ->{
            statement.setString(1, uuid);
            if (statement.executeUpdate() == 0) {
                throw new NotExistStorageException(uuid);
            }
            return null;
        });
    }

    @Override
    public List<Resume> getAllSorted() {
        return  SqlHelper.sqlExecute(connectionFactory,"SELECT * FROM resume ORDER BY full_name", statement ->{
            ResultSet rs = statement.executeQuery();
            List<Resume> resumes = new ArrayList<>();
            while (rs.next()) {
                resumes.add(new Resume(rs.getString("uuid").trim(), rs.getString("full_name")));
            }
            return resumes;
        });
    }

    @Override
    public int size() {
        return SqlHelper.sqlExecute(connectionFactory, "SELECT count(*) FROM resume", statement ->{
            ResultSet rs = statement.executeQuery();
            return rs.next() ? rs.getInt(1) : 0;
        });
    }
}