package com.urise.webapp.sql;

import com.urise.webapp.Config;
import com.urise.webapp.exception.ExistStorageException;
import com.urise.webapp.exception.StorageException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    public ConnectionFactory connectionFactory = null;

    public SqlHelper() {
        connectionFactory = Config.get().getConnectionFactory();
    }

    public <T> T sqlExecute(String statement, IHelper<T> operation) {
        try (Connection conn = connectionFactory.getConnection();
             PreparedStatement ps = conn.prepareStatement(statement)) {
            return operation.psOperations(ps);
        } catch (SQLException e) {
            if (e.getSQLState().equals("23505")){
                throw new ExistStorageException(e.getMessage());
            } else throw new StorageException(e);
        }
    }
}
