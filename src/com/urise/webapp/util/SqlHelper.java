package com.urise.webapp.util;

import com.urise.webapp.exception.StorageException;
import com.urise.webapp.sql.ConnectionFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class SqlHelper {
    private final ConnectionFactory connectionFactory;

    public SqlHelper(ConnectionFactory connectionFactory) {
        this.connectionFactory = connectionFactory;
    }

    public PreparedStatement getPreparedStatement(String sql) {
        try (Connection conn = connectionFactory.getConnection()) {
            return conn.prepareStatement(sql);
        } catch (SQLException e) {
            throw new StorageException(e);
        }
    }
}
