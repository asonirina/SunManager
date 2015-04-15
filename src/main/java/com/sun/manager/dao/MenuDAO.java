package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.menu.ActivityInfo;
import com.sun.manager.dto.menu.StandartMenu;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MenuDAO {

    private static final String GET_MENU_ITEM_DEPTH = "{call menu_hier(?,?)}";

    private Connection dbConnection = SqlServer.getConnection();
    private CallableStatement callableStatement = null;

    public void saveActionInfo(String menuId, String userRole, Date actionDate) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("insert into action_info (menu_id, user_role, action_date) values(?,?,?)");
        ps.setString(1, menuId);
        ps.setString(2, userRole);
        ps.setDate(3, actionDate);
        ps.executeUpdate();
    }

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

    public List<ActivityInfo> findUserActivity(String role) throws SQLException {
        String selectQuery = "select DISTINCT sm.menu_id, sm.parent_menu_id, sm.description from action_info as ai, standart_menu as sm where ai.menu_id = sm.menu_id and ai.user_role = ?";
        List<ActivityInfo> menuList = new ArrayList<ActivityInfo>();
        PreparedStatement ps = dbConnection.prepareStatement(selectQuery);
        ps.setString(1, role);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String menuID = rs.getString("menu_id");
            String parentMenuID = rs.getString("parent_menu_id");
            String description = rs.getString("description");

            Integer depth = findMenuItemDepth(role, menuID);
            Integer usageCount = null;

            PreparedStatement ps2 = dbConnection.prepareStatement("select count(*) from action_info where menu_id = ? and user_role = ?");
            ps2.setString(1, menuID);
            ps2.setString(2, role);
            ResultSet countRS = ps2.executeQuery();
            if (countRS.next()) {
                usageCount = countRS.getInt(1);
            }

            Double adaptCoefficient = calculateAdaptCoefficient(depth, usageCount);

            ActivityInfo activityInfo = new ActivityInfo(menuID, parentMenuID, description, depth, usageCount, adaptCoefficient);
            menuList.add(activityInfo);
        }

        return menuList;
    }

    public List<String> findParentNodesInStandartMenu(String role) throws SQLException {
        String selectQuery = "select DISTINCT menu_id from standart_menu where user_role = ? and parent_menu_id is NULL";
        List<String> menuIDs = new ArrayList<String>();
        PreparedStatement ps = dbConnection.prepareStatement(selectQuery);
        ps.setString(1, role);
        ResultSet rs = ps.executeQuery();
        while (rs.next()) {
            String menuID = rs.getString("menu_id");
            menuIDs.add(menuID);

        }

        return menuIDs;
    }

    public Integer getMenuItemDepth(String parentID, String nodeID, String role) throws SQLException {
        callableStatement = dbConnection.prepareCall(GET_MENU_ITEM_DEPTH);
        callableStatement.setString(1, parentID);
        callableStatement.setString(2, role);

        ResultSet rs = callableStatement.executeQuery();

        while (rs.next()) {
            String menuID = rs.getString("menu_id");
            Integer itemDepth = rs.getInt("depth");

            if (menuID.equals(nodeID)) {
                return itemDepth;
            }
        }

        return null;
    }

    private Integer findMenuItemDepth(String role, String menuID) throws SQLException {
        Integer depth = null;
        List<String> rootNodes = findParentNodesInStandartMenu(role);

        for (String rootNodeID : rootNodes) {
            if (depth == null) {
                depth = getMenuItemDepth(rootNodeID, menuID, role);
            } else {
                break;
            }
        }

        return depth;
    }

    private Double calculateAdaptCoefficient(Integer depth, Integer countUsage) {
        return Math.floor(countUsage/depth);
    }
}
