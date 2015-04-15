package com.sun.manager.service;

import com.sun.manager.dao.MenuDAO;
import com.sun.manager.dto.MenuData;
import com.sun.manager.dto.menu.ActivityInfo;
import com.sun.manager.dto.menu.StandartMenu;
import com.sun.manager.dto.menu.UserActivityMenu;
import com.sun.manager.testData.MenuTestData;

import java.sql.Date;
import java.sql.SQLException;
import java.util.*;

public class MenuService {
    private MenuDAO dao = new MenuDAO();

    public List<MenuData> getDefaultMenuByRole(String role) throws SQLException {
        List<StandartMenu> standartUserMenu = dao.findStandartMenuByRole(role);
        List<MenuData> menuDataList = new ArrayList<MenuData>();

        menuDataList = buildDefaultMenu(standartUserMenu, menuDataList);

        return menuDataList;
    }

    public List<MenuData> getAdaptMenuByRole(String role) throws SQLException {
        List<ActivityInfo> activityInfoList = dao.findUserActivity(role);

        List<StandartMenu> standartUserMenu = dao.findStandartMenuByRole(role);
        List<UserActivityMenu> userActivityMenuList = new ArrayList<UserActivityMenu>();

        userActivityMenuList = buildUserActivityMenu(standartUserMenu, userActivityMenuList);

        for (UserActivityMenu userActivityMenu : userActivityMenuList) {
            setAdaptCoefTree(activityInfoList, userActivityMenu);
        }

        return MenuTestData.getAdaptMenuByRole(role);
    }

    public void saveActionInfo(String actionId, String userRole, Date currentDate) throws SQLException {
        dao.saveActionInfo(actionId, userRole, currentDate);
    }

    private List<MenuData> buildDefaultMenu(List<StandartMenu> standartUserMenu, List<MenuData> menuDataList) {
        Map<String, MenuData> idToNodeMap = new HashMap<String, MenuData>();
        for (StandartMenu standartMenu : standartUserMenu) {
            String menuID = standartMenu.getMenuID();
            String parentID = standartMenu.getParentMenuID();

            MenuData previousNode = new MenuData(menuID, standartMenu.getDescription());
            idToNodeMap.put(menuID, previousNode);

            if (parentID == null) {
                menuDataList.add(previousNode);
            } else {
                MenuData parentNode = idToNodeMap.get(parentID);
                if (parentNode == null) {
                    for (MenuData child : idToNodeMap.values()) {
                        parentNode = getMenuDataParent(child, parentID);
                        if (parentNode != null) {
                            break;
                        }
                    }
                }

                if (parentNode != null)
                    parentNode.addMenu(previousNode);
            }
        }

        return menuDataList;
    }

    private List<UserActivityMenu> buildUserActivityMenu(List<StandartMenu> standartUserMenu, List<UserActivityMenu> menuDataList) {
        Map<String, UserActivityMenu> idToNodeMap = new HashMap<String, UserActivityMenu>();
        for (StandartMenu standartMenu : standartUserMenu) {
            String menuID = standartMenu.getMenuID();
            String parentID = standartMenu.getParentMenuID();

            UserActivityMenu previousNode = new UserActivityMenu(menuID, standartMenu.getDescription(), 0D);
            idToNodeMap.put(menuID, previousNode);

            if (parentID == null) {
                menuDataList.add(previousNode);
            } else {
                UserActivityMenu parentNode = idToNodeMap.get(parentID);
                if (parentNode == null) {
                    for (UserActivityMenu child : idToNodeMap.values()) {
                        parentNode = getUserActivityMenuParent(child, parentID);
                        if (parentNode != null) {
                            break;
                        }
                    }
                }

                if (parentNode != null)
                    parentNode.addMenu(previousNode);
            }
        }

        return menuDataList;
    }

    private void setAdaptCoefTree(List<ActivityInfo> activityInfoList, UserActivityMenu userActivityMenuList) {
        if (userActivityMenuList.getChildrenMenuList().isEmpty()) {
            String menuID = userActivityMenuList.getMenuId();
            for (ActivityInfo activityInfo : activityInfoList) {
                String activityMenuID = activityInfo.getMenuID();
                if (menuID.equals(activityMenuID)) {
                    userActivityMenuList.setAdaptCoefficient(activityInfo.getAdaptCoefficient());
                }
            }
        } else {
            for (UserActivityMenu child : userActivityMenuList.getChildrenMenuList()) {
                setAdaptCoefTree(activityInfoList, child);
            }
        }
    }

    // Get parent of node (recursive)
    private MenuData getMenuDataParent(MenuData rootNode, String rootID) {
        if (rootNode.getMenuId().equals(rootID))
            return rootNode;

        for (MenuData child : rootNode.getChildrenMenuList()) {
            if (child.getMenuId().equals(rootID)) return child;

            MenuData childResult = null;
            if (child.getChildrenMenuList().size() > 0) {
                childResult = getMenuDataParent(child, rootID);
            }

            if (childResult != null) return childResult;
        }
        return null;
    }

    // Get parent of node (recursive)
    private UserActivityMenu getUserActivityMenuParent(UserActivityMenu rootNode, String rootID) {
        if (rootNode.getMenuId().equals(rootID))
            return rootNode;

        for (UserActivityMenu child : rootNode.getChildrenMenuList()) {
            if (child.getMenuId().equals(rootID)) return child;

            UserActivityMenu childResult = null;
            if (child.getChildrenMenuList().size() > 0) {
                childResult = getUserActivityMenuParent(child, rootID);
            }

            if (childResult != null) return childResult;
        }
        return null;
    }
}
