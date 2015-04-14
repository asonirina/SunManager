package com.sun.manager.service;

import com.sun.manager.dao.MenuDAO;
import com.sun.manager.dto.MenuData;
import com.sun.manager.testData.MenuTestData;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class MenuService {
    private MenuDAO dao = new MenuDAO();

    public List<MenuData> getDefaultMenuByRole(String role) {
        return MenuTestData.getDefaultMenuByRole(role);
    }

    public List<MenuData> getAdaptMenuByRole(String role) {
        return MenuTestData.getAdaptMenuByRole(role);
    }

    public void saveActionInfo(String actionId, String userRole, Date currentDate) throws SQLException {
        dao.saveActionInfo(actionId, userRole, currentDate);
    }
}
