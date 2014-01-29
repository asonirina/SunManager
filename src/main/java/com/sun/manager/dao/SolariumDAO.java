package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.Cosmetics;

import java.sql.*;
import java.sql.Date;
import java.util.*;

public class SolariumDAO {

    private static final String GET_ONE_MINUTE_PRICE_BY_ID = "{call getOneMinutePriceById(?,?)}";
    private static final String GET_CODE_BY_SYMBOL = "{call getCodeBySymbol(?,?)}";
    private static final String VERTICAL_SOLARIUM = "vertical_sun_data";
    private static final String GORIZONTAL_BLUE_SOLARIUM = "gorizontal_blue_sun_data";
    private static final String GORIZONTAL_GREEN_SOLARIUM = "gorizontal_green_sun_data";
    private static final String VERTICAL_SOLARIUM_SUN = "vertical_sun";
    private static final String GORIZONTAL_BLUE_SOLARIUM_SUN = "gorizontal_blue_sun";
    private static final String GORIZONTAL_GREEN_SOLARIUM_SUN = "gorizontal_green_sun";

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

    public Map<String, Long> saveSolariumData(List<BaseSolariumData> baseSolariumDataList, Long solariumId) throws SQLException {
        String solarium = null;
        String solarium_sun = null;
        Map<String, Long> totals = new HashMap<String, Long>();
        Long totalSum = 0L;
        Long totalMinutes = 0L;
        Long l2 = 0L;

        if (solariumId == 1L) {
            solarium = VERTICAL_SOLARIUM;
            solarium_sun = VERTICAL_SOLARIUM_SUN;
        } else if (solariumId == 2L) {
            solarium = GORIZONTAL_BLUE_SOLARIUM;
            solarium_sun = GORIZONTAL_BLUE_SOLARIUM_SUN;
        } else if (solariumId == 3L) {
            solarium = GORIZONTAL_GREEN_SOLARIUM;
            solarium_sun = GORIZONTAL_GREEN_SOLARIUM_SUN;
        }
        for (BaseSolariumData baseData : baseSolariumDataList) {
            //Update minutes by abonements
            if (baseData.getTotalPrice() == 0) {
                PreparedStatement ps1 = dbConnection.prepareStatement("update abonements set minutes = ? where abonement_code = ?");
                PreparedStatement ps2 = dbConnection.prepareStatement("select  minutes from abonements where abonement_code = ?");
                ps2.setString(1, baseData.getAbonementNumber());
                Long minutes = ps2.executeQuery().getLong("minutes");
                ps1.setLong(1, minutes - baseData.getMinutes());
                ps2.setString(2, baseData.getAbonementNumber());
                ps2.executeUpdate();

                totalMinutes += baseData.getMinutes();
            } else {
                totalSum += baseData.getTotalPrice();
            }
            //Insert abonementsData
            PreparedStatement ps = dbConnection.prepareStatement("insert into " + solarium + " (start_date, minutes, total_price, abonement_number) values(?,?,?,?)");
            ps.setDate(1, (Date) baseData.getStartDate());
            ps.setLong(2, baseData.getMinutes());
            ps.setLong(3, baseData.getTotalPrice());
            ps.setString(4, baseData.getAbonementNumber());
            ps.executeUpdate();
        }
        PreparedStatement ps3 = dbConnection.prepareStatement("select l2 from " + solarium_sun + " where where start_date = (select MAX(start_date) from " + solarium_sun + ");");
        l2 = ps3.executeQuery().getLong("l2");
        totals.put("Итоговая сумма", totalSum);
        totals.put("Итого минут", totalMinutes);
        totals.put("L2", l2 + totalMinutes);
        return totals;
    }

    public Map<String, Long> saveCosmeticsData(HashMap<Cosmetics, Long> cosmetics) throws SQLException {
        Map<String, Long> resultData = new HashMap<String, Long>();

        for (Map.Entry<Cosmetics, Long> entry : cosmetics.entrySet()) {
            PreparedStatement ps1 = dbConnection.prepareStatement("update cosmetics set cosmetics_count = ? where cosmetics_id = ?");
            PreparedStatement ps2 = dbConnection.prepareStatement("select cosmetics_count from cosmetics where cosmetics_id = ?");

            Cosmetics key = entry.getKey();
            Long value = entry.getValue();

            ps2.setLong(1, key.getId());
            Long cosmeticsCount = ps2.executeQuery().getLong("cosmetics_count");
            if (cosmeticsCount < value) {
                resultData.put(key.getName(), value - cosmeticsCount);
            }
            ps1.setLong(1, cosmeticsCount - value);
            ps2.setLong(2, key.getId());
            ps2.executeUpdate();
        }

        return resultData;
    }
}
