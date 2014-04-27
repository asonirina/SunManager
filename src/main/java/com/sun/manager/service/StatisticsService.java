package com.sun.manager.service;

import com.sun.manager.dao.StatisticDAO;
import com.sun.manager.dto.StatisticData;

import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * User: iason
 * Date: 04.04.14
 */
public class StatisticsService {
    StatisticDAO dao = new StatisticDAO();

    public List<StatisticData> getStatisticData(Date startDate) {
        try {
            return dao.getStatisticData(startDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }


    public void saveStatisticData(StatisticData statisticData) {
        try {
            dao.saveStatisticData(statisticData);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Integer getResidue(Date lastDate) {
        try {
            return dao.getResidue(lastDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Map<String, Integer> getQuenchingAndAccumulation(Date date) {
        try {
             return dao.getQuenchingAndAccumulation(date);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
}
