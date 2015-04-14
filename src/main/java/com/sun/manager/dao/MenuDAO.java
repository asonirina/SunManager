package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.Cosmetics;
import com.sun.manager.dto.MenuData;
import com.sun.manager.dto.menu.StandartMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    private Connection dbConnection = SqlServer.getConnection();

    public void saveActionInfo(String menuId, String userRole, Date actionDate) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("insert into action_info (menu_id, user_role, action_date) values(?,?,?)");
        ps.setString(1, menuId);
        ps.setString(2, userRole);
        ps.setDate(3, actionDate);
        ps.executeUpdate();
    }

    /**
     * <code>select o from Menu o</code>
     */
    public List<StandartMenu> findStandartMenuByRole(String role) throws SQLException {
        List<StandartMenu> menuList = new ArrayList<StandartMenu>();
        PreparedStatement ps = dbConnection.prepareStatement("select * from standart_menu where user_role = ?");
        ps.setString(1, role);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String menuID = rs.getString("menu_id");
            String parentMenuID = rs.getString("parent_menu_id");
            String description = rs.getString("description");
            String userRole = rs.getString("user_role");
            StandartMenu standartMenu = new StandartMenu(menuID, parentMenuID, description, userRole);
            menuList.add(standartMenu);
        }

        return menuList;
    }

    /**
     * <code>select o from Menu o where o.id = menuId</code>
     */
    //I intend it to return a list instead of just a menu.
    public List<MenuData> findTargetRootMenu(Long menuId) throws SQLException {
        List<MenuData> menuList = new ArrayList<MenuData>();
        PreparedStatement ps = dbConnection.prepareStatement("select o from Menu o where o.id = ?");
        ps.setLong(1, menuId);
        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//        }
        return menuList;
    }

    /**
     * <code>select o from Menu o where o.parentMenu IS NULL</code>
     */
    public List<MenuData> findRootMenus() throws SQLException {
        List<MenuData> menuList = new ArrayList<MenuData>();
        PreparedStatement ps = dbConnection.prepareStatement("select o from Menu o where o.parentMenu IS NULL");
        ResultSet rs = ps.executeQuery();
//        while (rs.next()) {
//
//        }

        return menuList;
    }
}
