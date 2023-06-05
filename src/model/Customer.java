package model;

/**
 * This class is for customers in database.
 */
public class Customer {
    /**
     * the id of customer
     */
    private int customerId;
    /**
     * the name of customer
     */
    private String customerName;
    /**
     * the address of customer
     */
    private String address;
    /**
     * the postal code of customer
     */
    private String postalCode;
    /**
     * the phone number of customer
     */
    private String phoneNumber;
    /**
     * the id of division of customer
     */
    private int divisionId;
    /**
     * the name of division of customer
     */
    private String divisionName;
    /**
     * the id of country of customer
     */
    private int countryId;
    /**
     * the name of country of customer
     */
    private String countryName;
    /**
     * the auto-generated customer id
     */
    private static int autoCustomerId = 1;

    /**
     * Creates a new object of Customer class.
     * @param customerId the id of customer
     * @param customerName the name of customer
     * @param address the address of customer
     * @param postalCode the postal code of customer
     * @param phoneNumber the phone number of customer
     * @param divisionId the id of division of customer
     * @param countryId the id of country of customer
     * @param divisionName the name of division of customer
     * @param countryName the name of country of customer
     */
    public Customer(int customerId, String customerName, String address, String postalCode, String phoneNumber, int divisionId, int countryId, String divisionName, String countryName) {
        this.customerId = customerId;
        this.customerName = customerName;
        this.address = address;
        this.postalCode = postalCode;
        this.phoneNumber = phoneNumber;
        this.divisionId = divisionId;
        this.countryId = countryId;
        this.divisionName = divisionName;
        this.countryName = countryName;
    }

    /**
     * @return the id of customer
     */
    public int getCustomerId() {
        return customerId;
    }

    /**
     * @param customerId the id of customer
     */
    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    /**
     * @return the name of customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName the name of customer
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return the address of customer
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address of customer
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the postal code of customer
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode the postal code of customer
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return the phone number of customer
     */
    public String getPhoneNumber() {
        return phoneNumber;
    }

    /**
     * @param phoneNumber the phone number of customer
     */
    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    /**
     * @return the id of division of customer
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the id of division of customer
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return the name of division of customer
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName the name of division of customer
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return the id of country of customer
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the id of country of customer
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return the name of country of customer
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName the name of country of customer
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return the auto-generated customer id
     */
    public static int getAutoCustomerId(){
        return autoCustomerId++;
    }

    /**
     * @return the string of customer id and customer name
     */
    @Override
    public String toString() {
        return "(" + customerId + ") " + customerName;
    }
}
