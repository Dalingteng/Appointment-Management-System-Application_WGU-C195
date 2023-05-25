package model;

public class Report {
    private String month;
    private String type;
    private int monthTypeCount;
    private String country;
    private int countryCount;

    public Report(String month, String type, int monthTypeCount) {
        this.month = month;
        this.type = type;
        this.monthTypeCount = monthTypeCount;
    }

    public Report(String country, int countryCount) {
        this.country = country;
        this.countryCount = countryCount;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getMonthTypeCount() {
        return monthTypeCount;
    }

    public void setMonthTypeCount(int monthTypeCount) {
        this.monthTypeCount = monthTypeCount;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public int getCountryCount() {
        return countryCount;
    }

    public void setCountryCount(int countryCount) {
        this.countryCount = countryCount;
    }
}
