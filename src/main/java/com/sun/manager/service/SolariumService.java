package com.sun.manager.service;

import com.sun.manager.constants.SolariumEnum;
import com.sun.manager.dao.SolariumDAO;
import com.sun.manager.dto.*;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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

    public Long getL2ById(Long id) {
        try {
            return dao.getL2ById(id);
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException(ex.getMessage());
        }
    }

    public void saveSolariumData(List<BaseSolariumData> list, Long id, Double l2) {
        try {
            dao.saveSolariumData(list, id, l2);
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
}
