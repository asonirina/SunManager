package com.sun.manager.constants;

/**
 * User: iason
 * Date: 29.01.14
 */
public enum DataColumnEnum {

    Number(0),
    VerticalSolarium(1),
    GreenSolarium(2),
    BlueSolarium(3),
    Cosmetics(4),
    Abonements(5);

    private long solariumNo;

    DataColumnEnum(long solariumNo) {
        this.solariumNo = solariumNo;
    }

    public long getSolariumNo() {
        return solariumNo;
    }
}
