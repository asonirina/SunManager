package com.sun.manager.constants;

import com.sun.manager.dto.AbonementsRequest;
import com.sun.manager.dto.BaseSolariumData;
import com.sun.manager.dto.CosmeticsRequest;

/**
 * User: iason
 * Date: 30.01.14
 */
public class BlankItem {
    private static  BaseSolariumData solariumData = new BaseSolariumData(null, null, null, null, null);
    private static CosmeticsRequest cosmeticsRequest = new CosmeticsRequest(null, null);
    private static AbonementsRequest abonementsRequest = new AbonementsRequest();

    public static Object generateBlankItem(Long l) {
      if (l ==1L) {
          return solariumData.clone();
      } else if (l==2) {
          return  cosmeticsRequest.clone();
      }
        else {
         return  abonementsRequest.clone();
      }
    }
}
