package com.ly.fn.admin.modules.order.domain;


import com.ly.fn.admin.common.domain.BaseDomain;

public class Airport extends BaseDomain {
    /**机场编号*/
    private String airportCode;
    /**机场名称*/
    private String airportName;
    /**首字母*/
    private String firstWord;

    public String getAirportCode() {
        return airportCode;
    }

    public void setAirportCode(String airportCode) {
        this.airportCode = airportCode;
    }

    public String getAirportName() {
        return airportName;
    }

    public void setAirportName(String airportName) {
        this.airportName = airportName;
    }

    public String getFirstWord() {
        return firstWord;
    }

    public void setFirstWord(String firstWord) {
        this.firstWord = firstWord;
    }
}
