
package com.overunderleague.integration.nbaclient2.api;

import javax.annotation.processing.Generated;

@Generated("jsonschema2pojo")
public class User {

    private String countryCode;
    private String countryName;
    private String locale;
    private String timeZone;
    private String timeZoneCity;

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }

    public String getTimeZone() {
        return timeZone;
    }

    public void setTimeZone(String timeZone) {
        this.timeZone = timeZone;
    }

    public String getTimeZoneCity() {
        return timeZoneCity;
    }

    public void setTimeZoneCity(String timeZoneCity) {
        this.timeZoneCity = timeZoneCity;
    }

}
