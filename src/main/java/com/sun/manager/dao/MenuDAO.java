package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.Menu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    private Connection dbConnection = SqlServer.getConnection();

    /** <code>select o from Menu o</code> */
    public List<Menu> findAllMenus() throws SQLException {
       List<Menu> menuList = new ArrayList<Menu>();
        PreparedStatement ps = dbConnection.prepareStatement("select m from Menu m");
        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//        }

        return menuList;
    }

    /** <code>select o from Menu o where o.id = menuId</code> */
    //I intend it to return a list instead of just a menu.
    public List<Menu> findTargetRootMenu(Long menuId) throws SQLException {
        List<Menu> menuList = new ArrayList<Menu>();
        PreparedStatement ps = dbConnection.prepareStatement("select o from Menu o where o.id = ?");
        ps.setLong(1, menuId);
        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//        }
        return menuList;
    }

    /** <code>select o from Menu o where o.parentMenu IS NULL</code> */
    public List<Menu> findRootMenus() throws SQLException {
        List<Menu> menuList = new ArrayList<Menu>();
        PreparedStatement ps = dbConnection.prepareStatement("select o from Menu o where o.parentMenu IS NULL");
        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//        }

        return menuList;
    }
}
