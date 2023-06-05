package model;

/**
 * This class is for users in database.
 */
public class User {
    /**
     * the id of user
     */
    private int userId;
    /**
     * the name of user
     */
    private String userName;
    /**
     * the password of user
     */
    private String password;

    /**
     * Creates a new object of User class.
     * @param userId the id of user
     * @param userName the name of user
     * @param password the password of user
     */
    public User(int userId, String userName, String password) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the id of user
     */
    public int getUserId() {
        return userId;
    }

    /**
     * @param userId the id of user
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }

    /**
     * @return the name of user
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName the name of user
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return the password of user
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password of user
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the string of user id and user name
     */
    @Override
    public String toString() {
        return "(" + userId + ") " + userName;
    }
}
