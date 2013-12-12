package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class UsersDAO {

    private static final String CHECK_USER = "{call checkUser(?,?,?)}";

    private Connection dbConnection = null;
    private CallableStatement callableStatement = null;

    public Boolean checkUser(String login, String pswd) throws SQLException {
        dbConnection = SqlServer.getConnection();
        callableStatement = dbConnection.prepareCall(CHECK_USER);
        callableStatement.setString(1, login);
        callableStatement.setString(2, pswd);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);

        callableStatement.executeUpdate();

        return callableStatement.getBoolean(3);
    }
}
