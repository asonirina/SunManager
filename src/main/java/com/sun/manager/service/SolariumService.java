package com.sun.manager.service;

import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.Cosmetics;

import java.sql.Date;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * User: iason
 */
public class SolariumService {
    private SolariumDAO dao = new SolariumDAO();

    public List<BaseSolariumData> getSunData(Date date, SolariumEnum e) {
        try {
            return dao.getSolariumData(e.getNo(), date);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Cosmetics> getAllCosmetics() {
        try {
            return dao.getAllCosmetics();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Map<String, Long> saveCosmetics(HashMap<Cosmetics, Long> map) {
        try {
            return dao.saveCosmeticsData(map);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String getCodeBySymbol(String symbol) {
        try {
            return String.valueOf(dao.getCodeBySymbol(symbol));
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Long getL2ById(Long id) {
        try {
        return dao.getL2ById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

}
