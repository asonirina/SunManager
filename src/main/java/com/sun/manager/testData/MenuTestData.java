package com.sun.manager.testData;

import com.sun.manager.dto.MenuData;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuTestData {

    public static List<MenuData> getDefaultMenuByRole(String role) {
        Map<String, List<MenuData>> defaultMap = buildDefaultMenu();

        return defaultMap.get(role);
    }

    public static List<MenuData> getAdaptMenuByRole(String role) {
        Map<String, List<MenuData>> defaultMap = buildAdaptMenu();

        return defaultMap.get(role);
    }

    private static Map<String, List<MenuData>> buildDefaultMenu() {
        Map<String,  List<MenuData>> menusByRole = new HashMap<String,  List<MenuData>>();
        List<MenuData> adminMenu = new ArrayList<MenuData>();
        List<MenuData> directorMenu = new ArrayList<MenuData>();
        // ADMIN
        // Абнементы
        MenuData m1 = new MenuData("Абнементы");

        MenuData m11 = new MenuData("Доступные абонементы");
        MenuData m12 = new MenuData("Остаток минут");
        MenuData m13 = new MenuData("Поиск абонементов");

        m1.addMenu(m11);
        m1.addMenu(m12);
        m1.addMenu(m13);

        adminMenu.add(m1);

        // Комментарии
        MenuData m2 = new MenuData("Комментарии");

        MenuData m21 = new MenuData("Добавить комментарии");

        m2.addMenu(m21);
        adminMenu.add(m2);

        // Дополнительно
        MenuData m3 = new MenuData("Дополнительно");

        MenuData m31 = new MenuData("Дополнительная информация");
        MenuData m32 = new MenuData("Внести кассу");

        m3.addMenu(m31);
        m3.addMenu(m32);

        adminMenu.add(m3);

        //DIRECTOR
        MenuData md1 = new MenuData("Абнементы");

        MenuData md11 = new MenuData("Доступные абонементы");
        MenuData md12 = new MenuData("Остаток минут");
        MenuData md13 = new MenuData("Поиск абонементов");

        md1.addMenu(md11);
        md1.addMenu(md12);
        md1.addMenu(md13);

        directorMenu.add(md1);

        //Косметика
        MenuData md3 = new MenuData("Косметика");

        MenuData md31 = new MenuData("Добавить косметику");

        md3.addMenu(md31);
        directorMenu.add(md3);

        //Пользователи
        MenuData md4 = new MenuData("Пользователи");

        MenuData md41 = new MenuData("Администраторы");
        MenuData md42 = new MenuData("Клиенты");

        md4.addMenu(md41);
        md4.addMenu(md42);

        directorMenu.add(md4);

        // Комментарии
        MenuData md5 = new MenuData("Комментарии");

        MenuData md51 = new MenuData("Показать комментарии");

        md5.addMenu(md51);

        directorMenu.add(md5);

        // Дополнительно
        MenuData md6 = new MenuData("Дополнительно");

        MenuData md61 = new MenuData("Дополнительная информация");
        MenuData md62 = new MenuData("Статистика");
        MenuData md63 = new MenuData("Данные за период");

        md6.addMenu(md61);
        md6.addMenu(md62);
        md6.addMenu(md63);

        directorMenu.add(md6);

        menusByRole.put("admin", adminMenu);
        menusByRole.put("derictor", directorMenu);

        return menusByRole;
    }

    private static Map<String, List<MenuData>> buildAdaptMenu() {
        Map<String,  List<MenuData>> menusByRole = new HashMap<String,  List<MenuData>>();
        List<MenuData> adminMenu = new ArrayList<MenuData>();
        List<MenuData> directorMenu = new ArrayList<MenuData>();
        // ADMIN
        // Абнементы
        MenuData m1 = new MenuData("Абнементы");

        MenuData m11 = new MenuData("Доступные абонементы");
        MenuData m12 = new MenuData("Остаток минут по абонементу");
        MenuData m13 = new MenuData("Поиск абонементов");

        m1.addMenu(m11);
        m1.addMenu(m13);

        adminMenu.add(m1);
        adminMenu.add(m12);
        // Комментарии
        MenuData m2 = new MenuData("Комментарии");

        MenuData m21 = new MenuData("Добавить комментарии");

        m2.addMenu(m21);
        adminMenu.add(m2);

        // Дополнительно
        MenuData m3 = new MenuData("Дополнительно");

        MenuData m31 = new MenuData("Дополнительная информация");
        MenuData m32 = new MenuData("Внести кассу");

        m3.addMenu(m31);

        adminMenu.add(m3);
        adminMenu.add(m32);
        //DIRECTOR
        MenuData md1 = new MenuData("Абнементы");

        MenuData md11 = new MenuData("Доступные абонементы");
        MenuData md12 = new MenuData("Остаток минут");
        MenuData md13 = new MenuData("Поиск абонементов");

        md1.addMenu(md12);
        md1.addMenu(md13);

        directorMenu.add(md1);
        directorMenu.add(md11);

        //Косметика
        MenuData md3 = new MenuData("Косметика");

        MenuData md31 = new MenuData("Добавить косметику");

        md3.addMenu(md31);
        directorMenu.add(md3);

        //Пользователи
        MenuData md4 = new MenuData("Пользователи");

        MenuData md41 = new MenuData("Администраторы");
        MenuData md42 = new MenuData("Клиенты");

        md4.addMenu(md41);
        md4.addMenu(md42);

        directorMenu.add(md4);

        // Комментарии
        MenuData md5 = new MenuData("Комментарии");

        MenuData md51 = new MenuData("Показать комментарии");

        md5.addMenu(md51);

        directorMenu.add(md5);

        // Дополнительно
        MenuData md6 = new MenuData("Дополнительно");

        MenuData md61 = new MenuData("Дополнительная информация");
        MenuData md62 = new MenuData("Статистика");
        MenuData md63 = new MenuData("Данные за период");

        md6.addMenu(md61);

        directorMenu.add(md6);
        directorMenu.add(md62);
        directorMenu.add(md63);

        menusByRole.put("admin", adminMenu);
        menusByRole.put("derictor", directorMenu);

        return menusByRole;
    }
}
