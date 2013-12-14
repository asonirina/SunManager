package com.sun.manager.service;

import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.BaseSolariumData;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * User: iason
 */
public class SolariumService {
    private SolariumDAO dao = new SolariumDAO();

    public List<BaseSolariumData> getVertSunData(Date date) {
        try {
            return dao.getSolariumData(1L, date);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
}
