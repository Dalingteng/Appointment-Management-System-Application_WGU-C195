package model;

/**
 * This is Country model class.
 * This class is for countries in database.
 *
 * @author Sochandaling Teng
 */
public class Country {
    /**
     * the id of country
     */
    private int countryId;
    /**
     * the name of country
     */
    private String countryName;

    /**
     * Creates a new object of Country class.
     * @param countryId the id of country
     * @param countryName the name of country
     */
    public Country(int countryId, String countryName) {
        this.countryId = countryId;
        this.countryName = countryName;
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
     * @return the name of country
     */
    @Override
    public String toString() {
        return countryName;
    }
}
