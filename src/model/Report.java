package model;

/**
 * This class is for reports.
 */
public class Report {
    /**
     * the month of appointment
     */
    private String month;
    /**
     * the type of appointment
     */
    private String type;
    /**
     * the number of appointment by month and type
     */
    private int monthTypeCount;
    /**
     * the country of customer who makes appointment
     */
    private String country;
    /**
     * the number of appointment by country
     */
    private int countryCount;

    /**
     * Creates a new object of Report class by month and type.
     * @param month
     * @param type
     * @param monthTypeCount
     */
    public Report(String month, String type, int monthTypeCount) {
        this.month = month;
        this.type = type;
        this.monthTypeCount = monthTypeCount;
    }

    /**
     * Creates a new object of Report class by country.
     * @param country
     * @param countryCount
     */
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
