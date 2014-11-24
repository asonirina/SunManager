package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.constants.SunConstants;
import com.sun.manager.dto.StatisticData;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatisticDAO {

    private Connection dbConnection = SqlServer.getConnection();

    public List<StatisticData> getStatisticData(Date startDate) throws SQLException {
        List<StatisticData> statisticDataList = new ArrayList<StatisticData>();
        PreparedStatement ps = dbConnection.prepareStatement("select residue, booking_per_day, bank, official_salary," +
                " quenching, accumulation from statistic_data where start_date=?");
        ps.setDate(1, startDate);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer residue = rs.getInt("residue");
            Integer bookingPerDay = rs.getInt("booking_per_day");
            Integer bank = rs.getInt("bank");
            Integer officialSalary = rs.getInt("official_salary");
            Integer quenching = rs.getInt("quenching");
            Integer accumulation = rs.getInt("accumulation");
            StatisticData statisticData = new StatisticData(residue, bank, bookingPerDay, officialSalary, quenching, accumulation);
            statisticDataList.add(statisticData);
        }
        return statisticDataList;
    }

    public void saveStatisticData(StatisticData statisticData) throws SQLException {
        String deleteRow = "delete from statistic_data where start_date = ?";
        PreparedStatement ps = dbConnection.prepareStatement(deleteRow);
        ps.setDate(1, (Date) statisticData.getStartDate());
        ps.executeUpdate();

        ps = dbConnection.prepareStatement("insert into statistic_data (start_date, residue, booking_per_day, bank, official_salary, quenching, accumulation, user_login) values(?,?,?,?,?,?,?,?)");
        ps.setDate(1, (Date) statisticData.getStartDate());
        ps.setInt(2, statisticData.getResidue());
        ps.setInt(3, statisticData.getBookingPerDay());
        ps.setInt(4, statisticData.getBank());
        ps.setInt(5, statisticData.getOfficialSalary());
        ps.setInt(6, statisticData.getQuenching());
        ps.setInt(7, statisticData.getAccumulation());
        ps.setString(8, statisticData.getUserLogin());
        ps.executeUpdate();
    }

    //Взять остаток предыдущего дня, чтобы восстановить его в форме
    public Integer getResidue(Date lastDate) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("select residue, booking_per_day, bank, official_salary from statistic_data where start_date=?");
        ps.setDate(1, new java.sql.Date(lastDate.getTime() - SunConstants.MILLIS_IN_DAY));
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            return 0;
        }

        return rs.getInt("residue");
    }


    public Map<String, Integer> getQuenchingAndAccumulation(Date date) throws SQLException {
        Map<String, Integer> res = new HashMap<String, Integer>();
        PreparedStatement ps = dbConnection.prepareStatement("select quenching, accumulation from statistic_data where start_date = ?");
        ps.setDate(1, new java.sql.Date(date.getTime() - SunConstants.MILLIS_IN_DAY));
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer quenching = rs.getInt("quenching");
            Integer accumulation = rs.getInt("accumulation");

            res.put("quenching", quenching);
            res.put("accumulation", accumulation);
        }
        return res;
    }

    public void saveBankByAdmin(Date date, Integer bankByAdmin) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("select bankByAdmin from statistic_data_admin where start_date=?");
        ps.setDate(1, new java.sql.Date(date.getTime()));
        ResultSet rs = ps.executeQuery();

        if (!rs.next()) {
            PreparedStatement ps2 = dbConnection.prepareStatement("insert into statistic_data_admin (bankByAdmin) values(?)");
            ps2.setInt(1, bankByAdmin);
            ps2.executeUpdate();
        } else {
            PreparedStatement ps2 = dbConnection.prepareStatement("update statistic_data_admin set bankByAdmin = ? where start_date = ?");
            ps2.setInt(1, bankByAdmin);
            ps.setDate(2, new java.sql.Date(date.getTime()));
            ps2.executeUpdate();
        }
    }
}
