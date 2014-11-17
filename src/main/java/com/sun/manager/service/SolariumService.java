package com.sun.manager.service;

import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.*;

import java.sql.Date;
import java.sql.SQLException;
import java.util.Calendar;
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

    public List<CosmeticsRequest> getCosmeticsFromStock(HashMap<Cosmetics, Long> map) {
        try {
            return dao.saveCosmeticsData(map, true);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Map<String, Long> getCodeAndPriceBySymbol(String symbol) {
        try {
            return dao.getCodeBySymbol(symbol);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Double getL2ById(Long id) {
        try {
            return dao.getL2ById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveSolariumData(List<BaseSolariumData> list, Long id) {
        try {
            dao.saveSolariumData(list, id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveL2(Long id, Double l2, Date date, Long tm) {
        try {
            dao.saveL2(id, l2, date, tm);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<CosmeticsRequest> getCosmByDate(Date startDate) {
        try {
            return dao.getCosmByDate(startDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<AbonementsRequest> getAbonByDate(Date startDate) {
        try {
            return dao.getAbonByDate(startDate);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }


    public void saveCosmetics(List<CosmeticsRequest> cosmeticsRequestList) {
        try {
            dao.saveCosmetics(cosmeticsRequestList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveAbonement(List<AbonementsRequest> abonementsRequestList) {
        try {
            dao.saveAbonement(abonementsRequestList);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void createCosmetic(String name, int price, int cosmeticsCount) {
        try {
            dao.createCosmetic(name, price, cosmeticsCount);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }


    public List<CosmeticsRequest> putCosmeticsToStock(HashMap<Cosmetics, Long> map) {
        try {
            return dao.saveCosmeticsData(map, false);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public String createAbonement(String letter, String code, int minutes, int duration, int price) {
        try {
            return dao.createAbonement(letter, code, minutes, duration, price);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveComment(Comment comment) {
        try {
            dao.addComment(comment);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<Comment> getComments(Date date) {
        try {
            return dao.getCommentsByDate(date);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<CustomerStatistic> getStatistics() {
        try {
            return dao.getBuyersStatistic();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Cosmetics getStikini() {
        try {
            return dao.getStikini();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void deleteCosmetics(Cosmetics cosmetics) {
        try {
            dao.deleteCosmetic(cosmetics.getId());
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void updateCosmetics(Cosmetics cosmetics) {
        try {
            dao.updateCosmetic(cosmetics);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void deleteSolariumData(BaseSolariumData data, Long solariumId) {
        try {
            dao.deleteRowFromSolarium(data.getDataId(), solariumId);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public List<AvailableAbonements> getAvailableAbonements() {
        try {
            return dao.getAllAvailableAbonements();
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void updateAbonements(List<AvailableAbonements> list) {
        try {
             dao.updatePriceAndMinutes(list);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Integer getStikiniByDate(Date date) {
        try {
           return dao.getStikiniCountByDate(date);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveStikiniByDate(Date date, Integer count) {
        try {
            dao.saveStikiniByDate(date, count);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public Integer getResidueMinutesFromAbonement(String abonement, String phone) {
        try {
            return dao.getResidueMinutesFromAbonement(abonement, phone);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveL2ByAdministrator(Long solariumId, Double l2) {
        try {
            Long minutes = Math.round(Math.floor(l2))*60 + Math.round((l2 - Math.floor(l2))*100);
            dao.saveL2ByAdministrator(solariumId, l2,new Date(Calendar.getInstance().getTime().getTime()), minutes);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }
}
