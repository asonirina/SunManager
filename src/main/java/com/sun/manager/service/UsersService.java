package com.sun.manager.service;

import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.Users;

import java.sql.SQLException;

/**
 * User: iason
 * Date: 10.02.14
 */
public class UsersService {

    private SolariumDAO dao = new SolariumDAO();

    public boolean login(String login, String password) {
        try {
            return dao.checkUser(login, password) > 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Users getUser(String login) {
        try {
            return dao.getUserByLogin(login);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
}
