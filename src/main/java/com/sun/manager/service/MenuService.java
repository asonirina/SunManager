package com.sun.manager.service;

import com.sun.manager.dto.MenuData;
import com.sun.manager.testData.MenuTestData;

import java.util.List;

public class MenuService {

    public List<MenuData> getDefaultMenuByRole(String role) {
        return MenuTestData.getDefaultMenuByRole(role);
    }

    public List<MenuData> getAdaptMenuByRole(String role) {
        return MenuTestData.getAdaptMenuByRole(role);
    }
}
