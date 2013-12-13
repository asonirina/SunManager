package com.sun.manager.dao;

import com.sun.manager.connection.SqlServer;
import org.junit.Test;

/**
 * User: Администратор
 */
public class SolarisDaoTest {
    @Test
    public void solarisTest()throws Exception{
        SqlServer.init();
        SolariumDAO dao  =   new SolariumDAO();
        System.out.println(dao.getOneMinutePriceById(1L));
    }
}
