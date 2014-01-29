package com.sun.manager.service;

import com.sun.manager.constants.SolariumEnum;
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

    public List<BaseSolariumData> getSunData(Date date, SolariumEnum e) {
        try {
            return dao.getSolariumData(e.getNo(), date);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

}
