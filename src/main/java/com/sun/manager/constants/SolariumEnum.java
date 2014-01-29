package com.sun.manager.constants;

/**
 * User: iason
 * Date: 29.01.14
 */
public enum SolariumEnum {
    Vertical(1),
    Green(2),
    Blue(3);

    private long solariumNo;
    SolariumEnum( long solariumNo) {
        this.solariumNo = solariumNo;
    }

    public long getNo() {
        return solariumNo;
    }
}
