package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.StatisticData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

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
        PreparedStatement ps = dbConnection.prepareStatement("insert into statistic_data (start_date, residue, booking_per_day, bank, official_salary, quenching, accumulation, users_id) values(?,?,?,?,?,?,?,?)");
        ps.setDate(1, (Date) statisticData.getStartDate());
        ps.setInt(2, statisticData.getResidue());
        ps.setInt(3, statisticData.getBookingPerDay());
        ps.setInt(4, statisticData.getBank());
        ps.setInt(5, statisticData.getOfficialSalary());
        ps.setInt(6, statisticData.getQuenching());
        ps.setInt(7, statisticData.getAccumulation());
        ps.setInt(8, statisticData.getUserId());
        ps.executeUpdate();
    }

    //Взять остаток предыдущего дня, чтобы восстановить его в форме
    public Integer getResidue(Date lastDate) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("select residue, booking_per_day, bank, official_salary from statistic_data where start_date=?");
        ps.setDate(1, lastDate);
        ResultSet rs = ps.executeQuery();

        Integer residue = rs.getInt("residue");
        Integer bookingPerDay = rs.getInt("booking_per_day");
        Integer bank = rs.getInt("bank");
        Integer officialSalary = rs.getInt("official_salary");

        Integer residueLastDay = 0;

        if(bank != 0 && officialSalary != 0) {
            //РАЗ В МЕСЯЦ!!!!
            // Остаток предыдущего дня  + касса за тот день = остаток на текущий день - банк - офф зп
            residueLastDay = residue + bookingPerDay - bank- officialSalary;
        } else {
            //Остаток предыдущего дня  + касса за тот день = остаток на текущий день
            residueLastDay = residue + bookingPerDay;
        }
        return residueLastDay;

    }
}