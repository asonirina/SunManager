package com.sun.manager.service;

import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dao.UsersDAO;
import com.sun.manager.dto.AbonementsData;
import com.sun.manager.dto.Users;

import java.sql.SQLException;
import java.util.List;

/**
 * User: iason
 * Date: 10.02.14
 */
public class UsersService {

    private SolariumDAO dao = new SolariumDAO();
    UsersDAO userDao = new UsersDAO();

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

    public List<Users> getUsersByRole(String role) {
        try {
            return dao.getUsersByRole(role);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void addUser(Users user) {
        try {
            dao.addUser(user);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void deleteUser(Users user) {
        try {
            dao.deleteUser(user.getLogin());
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<AbonementsData> getPhoneBaseForAllCustomers(int page) {
        try {
           return userDao.getPhoneBaseForAllCustomers(page);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public int getPhoneBaseSize() {
        try {
            return userDao.getPhoneBaseSize();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<AbonementsData> getAvailableAbonementsByPhoneNumber(String phoneNumber, int pageNumber ) {
        try {
            return userDao.getAvailableAbonementsByPhoneNumber(phoneNumber, pageNumber);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public int getAbonsByPhoneSize(String phone) {
        try {
            return userDao.getAbonsByPhoneSize(phone);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
}
