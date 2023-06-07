package model;

/**
 * This is Division model class.
 * This class is for divisions in database.
 *
 * @author Sochandaling Teng
 */
public class Division {
    /**
     * the id of division
     */
    private int divisionId;
    /**
     * the name of division
     */
    private String divisionName;
    /**
     * the id of country
     */
    private int countryId;
    /**
     * the name of country
     */
    private String countryName;

    /**
     * Creates a new object of Division class.
     * @param divisionId the id of division
     * @param divisionName the name of division
     * @param countryId the id of country
     * @param countryName the name of country
     */
    public Division(int divisionId, String divisionName, int countryId, String countryName) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.countryId = countryId;
        this.countryName = countryName;
    }

    /**
     * @return the id of division
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the id of division
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return the name of division
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName the name of division
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return the id of country
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the id of country
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the name of country
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the name of country
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the name of division
     */
    @Override
    public String toString() {
        return divisionName;
    }
}
