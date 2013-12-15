package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.Cosmetics;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SolariumDAO {

    private static final String GET_ONE_MINUTE_PRICE_BY_ID = "{call getOneMinutePriceById(?,?)}";
    private static final String GET_CODE_BY_SYMBOL = "{call getCodeBySymbol(?,?)}";
    private static final String VERTICAL_SOLARIUM = "vertical_sun_data";
    private static final String GORIZONTAL_BLUE_SOLARIUM = "gorizontal_blue_sun_data";
    private static final String GORIZONTAL_GREEN_SOLARIUM = "gorizontal_green_sun_data";

    private Connection dbConnection = SqlServer.getConnection();
    private CallableStatement callableStatement = null;

    public Long getOneMinutePriceById(Long solariumId) throws SQLException {
        callableStatement = dbConnection.prepareCall(GET_ONE_MINUTE_PRICE_BY_ID);
        callableStatement.setInt(1, solariumId.intValue());
        callableStatement.registerOutParameter(2, Types.INTEGER);

        callableStatement.executeUpdate();

        return callableStatement.getLong(2);
    }

    public List<BaseSolariumData> getSolariumData(Long solariumId, Date startDate) throws SQLException {
        String solarium = null;
        List<BaseSolariumData> solariumDataList = new ArrayList<BaseSolariumData>();

        if (solariumId == 1L)
            solarium = VERTICAL_SOLARIUM;
        else if (solariumId == 2L)
            solarium = GORIZONTAL_BLUE_SOLARIUM;
        else if (solariumId == 3L)
            solarium = GORIZONTAL_GREEN_SOLARIUM;

        PreparedStatement preStatement = dbConnection.prepareStatement(
                "select * from " + solarium + " where start_date=?"
        );
        preStatement.setDate(1, startDate);

        ResultSet rs = preStatement.executeQuery();

        while (rs.next()) {
            Long id = rs.getLong("data_id");
            Date startDateData = rs.getDate("start_date");
            Long minutes = rs.getLong("minutes");
            Long totalPrice = rs.getLong("total_price");
            String abonementCode = rs.getString("abonement_number");

            BaseSolariumData baseSolariumData = new BaseSolariumData(
                    id, startDateData, minutes, totalPrice, abonementCode);
            solariumDataList.add(baseSolariumData);
        }
        return solariumDataList;
    }

    public List<Cosmetics> getAllCosmetics() throws SQLException {
        List<Cosmetics> cosmeticsList = new ArrayList<Cosmetics>();

        PreparedStatement preStatement = dbConnection.prepareStatement("select * from cosmetics");
        ResultSet rs = preStatement.executeQuery();
        while (rs.next()) {
            Long id = rs.getLong("cosmetics_id");
            String name = rs.getString("name");
            Long price = rs.getLong("price");
            Long cosmeticsCount = rs.getLong("cosmetics_count");
            Cosmetics cosmetics = new Cosmetics(id, name, price, cosmeticsCount);
            cosmeticsList.add(cosmetics);
        }

        return cosmeticsList;
    }

    public Long getCodeBySymbol(String symbol) throws SQLException {
        callableStatement = dbConnection.prepareCall(GET_CODE_BY_SYMBOL);
        callableStatement.setString(1, symbol);
        callableStatement.registerOutParameter(2, Types.INTEGER);

        callableStatement.executeUpdate();

        return callableStatement.getLong(2);
    }

    public void saveSolariumData(List<BaseSolariumData> baseSolariumDataList, Long solariumId) throws SQLException {
        String solarium = null;

        if (solariumId == 1L)
            solarium = VERTICAL_SOLARIUM;
        else if (solariumId == 2L)
            solarium = GORIZONTAL_BLUE_SOLARIUM;
        else if (solariumId == 3L)
            solarium = GORIZONTAL_GREEN_SOLARIUM;
        for (BaseSolariumData baseData : baseSolariumDataList) {
            PreparedStatement ps = dbConnection.prepareStatement("insert into " + solarium + " (start_date, minutes, total_price, abonement_number) values(?,?,?,?)");
            ps.setDate(1, (Date) baseData.getStartDate());
            ps.setLong(2, baseData.getMinutes());
            ps.setLong(3, baseData.getTotalPrice());
            ps.setString(4, baseData.getAbonementNumber());
            ps.executeUpdate();
        }
    }
}
