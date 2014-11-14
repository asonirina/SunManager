package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.AbonementsData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsersDAO {

    private static final String CHECK_USER = "{call checkUser(?,?)}";

    private Connection dbConnection = SqlServer.getConnection();;
    private CallableStatement callableStatement = null;

    public Boolean checkUser(String login) throws SQLException {
        callableStatement = dbConnection.prepareCall(CHECK_USER);
        callableStatement.setString(1, login);
        callableStatement.registerOutParameter(2, Types.BOOLEAN);

        callableStatement.executeUpdate();

        return callableStatement.getBoolean(2);
    }

    //просмотр действующих абонементов по номеру телeфона
    public List<AbonementsData> getAvailableAbonementsByPhoneNumber(String phoneNumber, int pageNumber) throws SQLException {
        List<AbonementsData> aDataList = new ArrayList<AbonementsData>();
        String getDataFromDB = "select letter, code, buyDate, clientName, minutes from abonements_data where phone =? LIMIT ?, 30";
        PreparedStatement ps = dbConnection.prepareStatement(getDataFromDB);
        ps.setString(1, phoneNumber);
        ps.setInt(2, pageNumber*30);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String letter = rs.getString("letter");
            Long code = rs.getLong("code");
            Date buyDate = rs.getDate("buyDate");
            String clientName = rs.getString("clientName");
            Long minutes = rs.getLong("minutes");
            AbonementsData aData = new AbonementsData(clientName, letter, code, phoneNumber, minutes, buyDate);

            aDataList.add(aData);
        }

        return aDataList;
    }

    //телефонная база клиентов
    public List<AbonementsData> getPhoneBaseForAllCustomers(int pageNumber) throws SQLException {
        List<AbonementsData> aDataList = new ArrayList<AbonementsData>();
        String getDataFromDB = "select client_name, phone from abonements_data group by(phone) LIMIT ?, 30";
        PreparedStatement ps = dbConnection.prepareStatement(getDataFromDB);
        ps.setInt(1, pageNumber*30);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String clientName = rs.getString("client_name");
            String phone = rs.getString("phone");
            AbonementsData aData = new AbonementsData(clientName, phone);

            aDataList.add(aData);
        }

        return aDataList;
    }
}
