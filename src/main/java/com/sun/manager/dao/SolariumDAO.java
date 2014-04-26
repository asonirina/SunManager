package com.sun.manager.dao;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.sun.manager.connection.SqlServer;
import com.sun.manager.dto.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SolariumDAO {

    private static final String GET_ONE_MINUTE_PRICE_BY_ID = "{call getOneMinutePriceById(?,?)}";
    private static final String GET_L2_BY_ID = "{call getL2ById(?,?)}";
    private static final String GET_CODE_BY_SYMBOL = "{call getCodeBySymbol(?,?)}";
    private static final String CHECK_USER = "{call checkUser(?,?,?)}";
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

    public Long checkUser(String login, String pswd) throws SQLException {
        callableStatement = dbConnection.prepareCall(CHECK_USER);
        callableStatement.setString(1, login);
        callableStatement.setString(2, pswd);
        callableStatement.registerOutParameter(3, Types.BOOLEAN);

        callableStatement.executeUpdate();

        return callableStatement.getLong(3);
    }

    public Long getL2ById(Long solariumId) throws SQLException {
        callableStatement = dbConnection.prepareCall(GET_L2_BY_ID);
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

        PreparedStatement preStatement = dbConnection.prepareStatement("select * from cosmetics where name != ?");
        preStatement.setString(1, "stikini");
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

    public Cosmetics getStikini() throws SQLException {
        PreparedStatement preStatement = dbConnection.prepareStatement("select * from cosmetics where name = ?");
        preStatement.setString(1, "stikini");
        ResultSet rs = preStatement.executeQuery();
        Cosmetics cosmetics = null;
        while (rs.next()) {
            Long id = rs.getLong("cosmetics_id");
            String name = rs.getString("name");
            Long price = rs.getLong("price");
            Long cosmeticsCount = rs.getLong("cosmetics_count");
            cosmetics = new Cosmetics(id, name, price, cosmeticsCount);
        }

        return cosmetics;
    }

    public Map<String, Long> getCodeBySymbol(String symbol) throws SQLException {
        Map<String, Long> abonementData = new HashMap<String, Long>();

        callableStatement = dbConnection.prepareCall(GET_CODE_BY_SYMBOL);
        callableStatement.setString(1, symbol);
        callableStatement.registerOutParameter(2, Types.INTEGER);

        callableStatement.executeUpdate();
        Long code = callableStatement.getLong(2);
        abonementData.put("code", code);

        PreparedStatement preStatement = dbConnection.prepareStatement("select price from abonements where abonement_code = ?");
        preStatement.setString(1, symbol + code);
        ResultSet rs = preStatement.executeQuery();
        while (rs.next()) {
            abonementData.put("price", rs.getLong("price"));
        }

        return abonementData;
    }

    public void saveSolariumData(List<BaseSolariumData> baseSolariumDataList, Long solariumId, Double solariumL2) throws SQLException {
        String solarium = null;
        String solarium_sun = null;
        Map<String, Long> totals = new HashMap<String, Long>();
        Long totalSum = 0L;
        Long totalMinutes = 0L;
        Double l2 = 0D;
        Long oneMinutePrice = 0L;

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
            if (baseData.getTotalPrice() == null) {
                PreparedStatement ps1 = dbConnection.prepareStatement("update abonements set minutes = ? where abonement_code = ?");
                PreparedStatement ps2 = dbConnection.prepareStatement("select  minutes from abonements where abonement_code = ?");
                ps2.setString(1, baseData.getAbonementNumber());

                ResultSet rs = ps2.executeQuery();

                while (rs.next()) {
                    Long minutes = rs.getLong("minutes");
                    ps1.setLong(1, minutes - baseData.getMinutes());
                    ps1.setString(2, baseData.getAbonementNumber());
                    ps1.executeUpdate();

                    totalMinutes += baseData.getMinutes();
                }
            } else {
                totalSum += baseData.getTotalPrice();
            }

            //Update l2
            PreparedStatement ps5 = dbConnection.prepareStatement("delete from " + solarium_sun + " where start_date = ?");
            PreparedStatement ps3 = dbConnection.prepareStatement("insert into " + solarium_sun + " (start_date, total_minute, l2, one_minute_price) values(?,?,?,?)");
            PreparedStatement ps4 = dbConnection.prepareStatement("select l2,one_minute_price from " + solarium_sun + " where start_date = (select MAX(start_date) from "+ solarium_sun +")");
            ResultSet rs = ps4.executeQuery();

            while (rs.next()) {
                l2 = rs.getDouble("l2");
                oneMinutePrice = rs.getLong("one_minute_price");
            }

            if(l2 + solariumL2 >= 999.59) {
               l2 += (l2 + solariumL2 - 999.59);
            } else {
                l2 += solariumL2;
           }

            ps5.setDate(1, (Date) baseData.getStartDate());
            ps5.executeUpdate();

            ps3.setDate(1, (Date) baseData.getStartDate());
            ps3.setLong(2, totalMinutes);
            ps3.setDouble(3, l2);
            ps3.setLong(4, oneMinutePrice);
            ps3.executeUpdate();

            //Insert abonementsData
            PreparedStatement ps = dbConnection.prepareStatement("insert into " + solarium + " (start_date, minutes, total_price, abonement_number) values(?,?,?,?)");
            ps.setDate(1, (Date) baseData.getStartDate());
            ps.setLong(2, baseData.getMinutes());
            if (baseData.getTotalPrice() == null) {
                ps.setLong(3, 0);
            } else {
                ps.setLong(3, baseData.getTotalPrice());
            }
            ps.setString(4, baseData.getAbonementNumber());
            ps.executeUpdate();
        }
    }

    public List<CosmeticsRequest> saveCosmeticsData(HashMap<Cosmetics, Long> cosmetics, boolean isMinus) throws SQLException {
        List<CosmeticsRequest> resultData = new ArrayList<CosmeticsRequest>();

        for (Map.Entry<Cosmetics, Long> entry : cosmetics.entrySet()) {
            PreparedStatement ps1 = dbConnection.prepareStatement("update cosmetics set cosmetics_count = ? where cosmetics_id = ?");
            PreparedStatement ps2 = dbConnection.prepareStatement("select cosmetics_count from cosmetics where cosmetics_id = ?");

            Cosmetics key = entry.getKey();
            Long value = entry.getValue();

            ps2.setLong(1, key.getId());
            ResultSet rs = ps2.executeQuery();

            while (rs.next()) {
                Long cosmeticsCount = rs.getLong("cosmetics_count");
                if (isMinus) {
                    if (cosmeticsCount < value || cosmeticsCount == 0) {
                        resultData.add(new CosmeticsRequest(value - cosmeticsCount, entry.getKey()));
                    } else {
                        ps1.setLong(1, cosmeticsCount - value);
                        ps1.setLong(2, key.getId());
                        ps1.executeUpdate();
                    }
                } else {
                    ps1.setLong(1, cosmeticsCount + value);
                    ps1.setLong(2, key.getId());
                    ps1.executeUpdate();
                }
            }
        }

        return resultData;
    }

    public Users getUserByLogin(String login) throws SQLException {
        Users user = new Users();
        PreparedStatement ps2 = dbConnection.prepareStatement("select name, password, role from users where login = ?");
        ps2.setString(1, login);
        ResultSet rs = ps2.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            String password = rs.getString("password");
            String role = rs.getString("role");
            user.setName(name);
            user.setLogin(login);
            user.setPassword(password);
            user.setRole(role);
        }
        return user;
    }

    public List<CosmeticsRequest> getCosmByDate(Date startDate) throws SQLException {
        List<CosmeticsRequest> cosmeticsRequestList = new ArrayList<CosmeticsRequest>();
        PreparedStatement ps2 = dbConnection.prepareStatement("select cosm_count, cosm_name, price from cosmetics_data where start_date = ?");
        ps2.setDate(1, startDate);
        ResultSet rs = ps2.executeQuery();

        while (rs.next()) {
            Long cosmCount = rs.getLong("cosm_count");
            String cosmName = rs.getString("cosm_name");
            Long price = rs.getLong("price");
            Cosmetics cosmetics = new Cosmetics();
            cosmetics.setName(cosmName);
            cosmetics.setPrice(price);
            CosmeticsRequest cosmeticsRequest = new CosmeticsRequest(cosmCount, cosmetics, startDate);
            cosmeticsRequestList.add(cosmeticsRequest);
        }

        return cosmeticsRequestList;
    }

    public List<AbonementsRequest> getAbonByDate(Date startDate) throws SQLException {
        List<AbonementsRequest> abonementsRequestList = new ArrayList<AbonementsRequest>();
        PreparedStatement ps2 = dbConnection.prepareStatement("select ad.code, ad.letter, ad.client_name, ad.phone, a.price from abonements_data ad JOIN abonements a on a.abonement_code = concat(ad.letter,'',ad.code) where start_date = ?");
        ps2.setDate(1, startDate);
        ResultSet rs = ps2.executeQuery();

        while (rs.next()) {
            Long code = rs.getLong("ad.code");
            String letter = rs.getString("ad.letter");
            String clientName = rs.getString("ad.client_name");
            String phone = rs.getString("ad.phone");
            Long price = (long) rs.getInt("a.price");

            AbonementsRequest abonementsRequest = new AbonementsRequest(letter, code, clientName, phone, price, startDate);
            abonementsRequestList.add(abonementsRequest);
        }

        return abonementsRequestList;
    }


    public void saveCosmetics(List<CosmeticsRequest> cosmeticsRequestList) throws SQLException {
        for (CosmeticsRequest cosmeticsRequest : cosmeticsRequestList) {
            PreparedStatement ps = dbConnection.prepareStatement("insert into cosmetics_data (start_date, cosm_count, cosm_name, price) values(?,?,?,?)");
            ps.setDate(1, (Date) cosmeticsRequest.getStartDate());
            ps.setLong(2, cosmeticsRequest.getCount());
            ps.setString(3, cosmeticsRequest.getCosmetics().getName());
            ps.setLong(4, cosmeticsRequest.getCosmetics().getPrice());
            ps.executeUpdate();
        }
    }

    public void saveAbonement(List<AbonementsRequest> abonementsRequestList) throws SQLException {
        for (AbonementsRequest abonementsRequest : abonementsRequestList) {
            PreparedStatement ps2 = dbConnection.prepareStatement("insert into abonements_data (start_date, code, letter, client_name, phone) values(?,?,?,?,?)");
            PreparedStatement ps1 = dbConnection.prepareStatement("update abonements set is_free = ? where abonement_code = ?");
            ps1.setBoolean(1, Boolean.FALSE);
            ps1.setString(2, abonementsRequest.getLetter() + abonementsRequest.getCode());
            ps1.executeUpdate();

            ps2.setDate(1, (Date) abonementsRequest.getStartDate());
            ps2.setLong(2, abonementsRequest.getCode());
            ps2.setString(3, abonementsRequest.getLetter());
            ps2.setString(4, abonementsRequest.getName());
            ps2.setString(5, abonementsRequest.getPhone());

            ps2.executeUpdate();
        }
    }

    public String createAbonement(String letter, String code, int minutes, int duration, int price) throws SQLException {
        String abonementCode = letter + code;
        PreparedStatement ps = dbConnection.prepareStatement("insert into abonements (abonement_code, price, minutes, duration, is_free) values(?,?,?,?,?)");
        ps.setString(1, abonementCode);
        ps.setInt(2, price);
        ps.setInt(3, minutes);
        ps.setInt(4, duration);
        ps.setBoolean(5, Boolean.TRUE);

        try {
            ps.executeUpdate();
        } catch (MySQLIntegrityConstraintViolationException ex) {
            return abonementCode + " уже существует!";
        }
        return null;

    }

    public void createCosmetic(String name, int price, int cosmeticsCount) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("insert into cosmetics (name, price, cosmetics_count) values(?,?,?)");
        ps.setString(1, name);
        ps.setInt(2, price);
        ps.setInt(3, cosmeticsCount);
        ps.executeUpdate();
    }

    public void updateCosmetic(Cosmetics cosmtic) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("update cosmetics set name = ?, price = ?, cosmetics_count = ? where cosmetics_id = ?");
        ps.setString(1, cosmtic.getName());
        ps.setLong(2, cosmtic.getPrice());
        ps.setLong(3, cosmtic.getCount());
        ps.setLong(4, cosmtic.getId());
        ps.executeUpdate();
    }

    public void deleteCosmetic(Long id) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("delete from cosmetics where cosmetics_id = ?");
        ps.setLong(1, id);
        ps.executeUpdate();
    }

    public List<Users> getUsersByRole(String role) throws SQLException {
        List<Users> usersList = new ArrayList<Users>();
        PreparedStatement ps2 = dbConnection.prepareStatement("select login, name, password from users where role = ?");
        ps2.setString(1, role);
        ResultSet rs = ps2.executeQuery();

        while (rs.next()) {
            Users user = new Users();
            String name = rs.getString("name");
            String password = rs.getString("password");
            String login = rs.getString("login");
            user.setName(name);
            user.setPassword(password);
            user.setLogin(login);

            usersList.add(user);
        }
        return usersList;
    }


    public void addUser(Users user) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("insert into users (login, name, password, role) values(?,?,?,?)");
        ps.setString(1, user.getLogin());
        ps.setString(2, user.getName());
        ps.setString(3, user.getPassword());
        ps.setString(4, user.getRole());
        ps.executeUpdate();
    }


    public void deleteUser(String login) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("delete from users where login = ?");
        ps.setString(1, login);
        ps.executeUpdate();
    }

    public void addComment(Comment comment) throws SQLException {
        PreparedStatement ps = dbConnection.prepareStatement("insert into comments_data (start_date, comment) values(?,?)");
        ps.setDate(1, new java.sql.Date((comment.getDate()).getTime()));
        ps.setString(2, comment.getComment());
        ps.executeUpdate();
    }

    public List<CustomerStatistic> getBuyersStatistic() throws SQLException {
        List<CustomerStatistic> customerStatisticList = new ArrayList<CustomerStatistic>();
        PreparedStatement ps = dbConnection.prepareStatement("select c.name, count(a.customer_id) as count from abonements as a JOIN customers as c on a.customer_id = c.customer_id group by a.customer_id");
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String name = rs.getString("name");
            Long count = rs.getLong("count");
            CustomerStatistic customerStatistic = new CustomerStatistic(name, count);
            customerStatisticList.add(customerStatistic);
        }
        return customerStatisticList;
    }

    public List<Comment> getCommentsByDate(Date date) throws SQLException {
        List<Comment> comments = new ArrayList<Comment>();
        PreparedStatement ps = dbConnection.prepareStatement("select comment  from comments_data where start_date = ?");
        ps.setDate(1, date);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            String comment = rs.getString("comment");

            Comment commentData = new Comment(date, comment);
            comments.add(commentData);
        }
        return comments;
    }


    public AvailableAbonements getPriceAndMinutesByLetter(String letter) throws SQLException {
        AvailableAbonements availableAbonements = null;
        PreparedStatement ps = dbConnection.prepareStatement("select price, minutes from available_abonements where letter = ?");
        ps.setString(1, letter);
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            Integer price = rs.getInt("price");
            Integer minutes = rs.getInt("minutes");

            availableAbonements = new AvailableAbonements(letter, price, minutes);
        }
        return availableAbonements;
    }

    public void updatePriceAndMinutes(AvailableAbonements availableAbonements) throws SQLException {
        PreparedStatement update = dbConnection.prepareStatement("update available_abonements set price = ?, minutes = ? where letter = ?");
        update.setInt(1, availableAbonements.getPrice());
        update.setInt(2, availableAbonements.getMinutes());
        update.setString(3, availableAbonements.getLetter());
        update.executeUpdate();
    }
}
