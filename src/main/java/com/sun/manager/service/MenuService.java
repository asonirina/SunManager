package com.sun.manager.service;

import com.sun.manager.dto.Menu;
import com.sun.manager.testData.MenuData;

import java.util.List;

public class MenuService {

    public List<Menu> getDefaultMenuByRole(String role) {
        return MenuData.getDefaultMenuByRole(role);
    }

    public List<Menu> getAdaptMenuByRole(String role) {
        return MenuData.getAdaptMenuByRole(role);
    }
}
