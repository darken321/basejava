package com.urise.webapp.sql;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public interface IHelper <T>{
    T psOperations(PreparedStatement ps) throws SQLException;
}