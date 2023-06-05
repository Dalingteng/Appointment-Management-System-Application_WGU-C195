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
     * the country of customer of appointment
     */
    private String country;
    /**
     * the number of appointment by country
     */
    private int countryCount;

    /**
     * Creates a new object of Report class by month and type.
     * @param month the month of appointment
     * @param type the type of appointment
     * @param monthTypeCount the number of appointment by month and type
     */
    public Report(String month, String type, int monthTypeCount) {
        this.month = month;
        this.type = type;
        this.monthTypeCount = monthTypeCount;
    }

    /**
     * Creates a new object of Report class by country.
     * @param country the country of customer of appointment
     * @param countryCount the number of appointment by country
     */
    public Report(String country, int countryCount) {
        this.country = country;
        this.countryCount = countryCount;
    }

    /**
     * @return the month of appointment
     */
    public String getMonth() {
        return month;
    }

    /**
     * @param month the month of appointment
     */
    public void setMonth(String month) {
        this.month = month;
    }

    /**
     * @return the type of appointment
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type of appointment
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the number of appointment by month and type
     */
    public int getMonthTypeCount() {
        return monthTypeCount;
    }

    /**
     * @param monthTypeCount the number of appointment by month and type
     */
    public void setMonthTypeCount(int monthTypeCount) {
        this.monthTypeCount = monthTypeCount;
    }

    /**
     * @return the country of customer of appointment
     */
    public String getCountry() {
        return country;
    }

    /**
     * @param country the country of customer of appointment
     */
    public void setCountry(String country) {
        this.country = country;
    }

    /**
     * @return the number of appointment by country
     */
    public int getCountryCount() {
        return countryCount;
    }

    /**
     * @param countryCount the number of appointment by country
     */
    public void setCountryCount(int countryCount) {
        this.countryCount = countryCount;
    }
}
