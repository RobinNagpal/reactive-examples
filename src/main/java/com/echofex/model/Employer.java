package com.echofex.model;

/**
 * Created by robin on 3/6/16.
 */
public class Employer {
    private long id;
    private String name;
    private String countryCode;

    private Employer() {
        //no instance
    }

    public Employer(long id, String name, String countryCode) {
        this.id = id;
        this.name = name;
        this.countryCode = countryCode;
    }



    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public long getId() {
        return id;
    }
}
