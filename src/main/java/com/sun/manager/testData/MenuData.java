package com.sun.manager.testData;

import com.sun.manager.dto.Menu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MenuData {

    public static List<Menu> getDefaultMenuByRole(String role) {
        Map<String, List<Menu>> defaultMap = buildDefaultMenu();

        return defaultMap.get(role);
    }

    public static List<Menu> getAdaptMenuByRole(String role) {
        Map<String, List<Menu>> defaultMap = buildAdaptMenu();

        return defaultMap.get(role);
    }

    private static Map<String, List<Menu>> buildDefaultMenu() {
        Map<String,  List<Menu>> menusByRole = new HashMap<String,  List<Menu>>();
        List<Menu> adminMenu = new ArrayList<Menu>();
        List<Menu> directorMenu = new ArrayList<Menu>();
        // ADMIN
        // Абнементы
        Menu m1 = new Menu("Абнементы");

        Menu m11 = new Menu("Доступные абонементы");
        Menu m12 = new Menu("Остаток минут");
        Menu m13 = new Menu("Поиск абонементов");

        m1.addMenu(m11);
        m1.addMenu(m12);
        m1.addMenu(m13);

        adminMenu.add(m1);

        // Комментарии
        Menu m2 = new Menu("Комментарии");

        Menu m21 = new Menu("Добавить комментарии");

        m2.addMenu(m21);
        adminMenu.add(m2);

        // Дополнительно
        Menu m3 = new Menu("Дополнительно");

        Menu m31 = new Menu("Дополнительная информация");
        Menu m32 = new Menu("Внести кассу");

        m3.addMenu(m31);
        m3.addMenu(m32);

        adminMenu.add(m3);

        //DIRECTOR
        Menu md1 = new Menu("Абнементы");

        Menu md11 = new Menu("Доступные абонементы");
        Menu md12 = new Menu("Остаток минут");
        Menu md13 = new Menu("Поиск абонементов");

        md1.addMenu(md11);
        md1.addMenu(md12);
        md1.addMenu(md13);

        directorMenu.add(md1);

        //Косметика
        Menu md3 = new Menu("Косметика");

        Menu md31 = new Menu("Добавить косметику");

        md3.addMenu(md31);
        directorMenu.add(md3);

        //Пользователи
        Menu md4 = new Menu("Пользователи");

        Menu md41 = new Menu("Администраторы");
        Menu md42 = new Menu("Клиенты");

        md4.addMenu(md41);
        md4.addMenu(md42);

        directorMenu.add(md4);

        // Комментарии
        Menu md5 = new Menu("Комментарии");

        Menu md51 = new Menu("Показать комментарии");

        md5.addMenu(md51);

        directorMenu.add(md5);

        // Дополнительно
        Menu md6 = new Menu("Дополнительно");

        Menu md61 = new Menu("Дополнительная информация");
        Menu md62 = new Menu("Статистика");
        Menu md63 = new Menu("Данные за период");

        md6.addMenu(md61);
        md6.addMenu(md62);
        md6.addMenu(md63);

        directorMenu.add(md6);

        menusByRole.put("admin", adminMenu);
        menusByRole.put("director", directorMenu);

        return menusByRole;
    }

    private static Map<String, List<Menu>> buildAdaptMenu() {
        Map<String,  List<Menu>> menusByRole = new HashMap<String,  List<Menu>>();
        List<Menu> adminMenu = new ArrayList<Menu>();
        List<Menu> directorMenu = new ArrayList<Menu>();
        // ADMIN
        // Абнементы
        Menu m1 = new Menu("Абнементы");

        Menu m11 = new Menu("Доступные абонементы");
        Menu m12 = new Menu("Остаток минут по абонементу");
        Menu m13 = new Menu("Поиск абонементов");

        m1.addMenu(m11);
        m1.addMenu(m13);

        adminMenu.add(m1);
        adminMenu.add(m12);
        // Комментарии
        Menu m2 = new Menu("Комментарии");

        Menu m21 = new Menu("Добавить комментарии");

        m2.addMenu(m21);
        adminMenu.add(m2);

        // Дополнительно
        Menu m3 = new Menu("Дополнительно");

        Menu m31 = new Menu("Дополнительная информация");
        Menu m32 = new Menu("Внести кассу");

        m3.addMenu(m31);

        adminMenu.add(m3);
        adminMenu.add(m32);
        //DIRECTOR
        Menu md1 = new Menu("Абнементы");

        Menu md11 = new Menu("Доступные абонементы");
        Menu md12 = new Menu("Остаток минут");
        Menu md13 = new Menu("Поиск абонементов");

        md1.addMenu(md12);
        md1.addMenu(md13);

        directorMenu.add(md1);
        directorMenu.add(md11);

        //Косметика
        Menu md3 = new Menu("Косметика");

        Menu md31 = new Menu("Добавить косметику");

        md3.addMenu(md31);
        directorMenu.add(md3);

        //Пользователи
        Menu md4 = new Menu("Пользователи");

        Menu md41 = new Menu("Администраторы");
        Menu md42 = new Menu("Клиенты");

        md4.addMenu(md41);
        md4.addMenu(md42);

        directorMenu.add(md4);

        // Комментарии
        Menu md5 = new Menu("Комментарии");

        Menu md51 = new Menu("Показать комментарии");

        md5.addMenu(md51);

        directorMenu.add(md5);

        // Дополнительно
        Menu md6 = new Menu("Дополнительно");

        Menu md61 = new Menu("Дополнительная информация");
        Menu md62 = new Menu("Статистика");
        Menu md63 = new Menu("Данные за период");

        md6.addMenu(md61);

        directorMenu.add(md6);
        directorMenu.add(md62);
        directorMenu.add(md63);

        menusByRole.put("admin", adminMenu);
        menusByRole.put("director", directorMenu);

        return menusByRole;
    }
}
