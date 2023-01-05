package model;

import java.time.LocalDateTime;
import java.util.Calendar;
/**This class is used to create User objects. User object fields model attributes found in users table
 * in client_schedule database */
public class User {
    private int userID;
    private String userName;
    private String password;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdateBy;
    /**This User constructor uses all fields provided. Every User field is available as a parameter.*/

    public User(int userID, String userName, String password, LocalDateTime createDate, String createdBy,
                LocalDateTime lastUpdate, String lastUpdateBy) {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdateBy = lastUpdateBy;
    }
    public User (int userID, String userName, String password)   {
        this.userID = userID;
        this.userName = userName;
        this.password = password;
    }

    /**
     * @return the userID that uniquely identifies different users in database.
     */
    public int getUserID() {
        return userID;
    }

    /**
     * @param userID the int userID that uniquely identifies different users in database.
     */
    public void setUserID(int userID) {
        this.userID = userID;
    }

    /**
     * @return this userName. The userName string given to this User.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * @param userName a String that will set the userName field of this User.
     */
    public void setUserName(String userName) {
        this.userName = userName;
    }

    /**
     * @return this password. The password string given to this User.
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password  a String that will set the password field of this User.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return this createdDate. The createDate string given to this User.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate  a LocalDateTime that will set the createDate field of this User.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return this createdBy. The createdBy string given to this User.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy  a String that will set the createdBy field of this User.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return this lastUpdate. The lastUpdate LocalDateTime given to this User.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate  a LocalDateTime that will set the lastUpdate field of this User.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return this lastUpdatedBy. The lastUpdatedBy string given to this User.
     */
    public String getLastUpdateBy() {
        return lastUpdateBy;
    }

    /**
     * @param lastUpdateBy  a String that will set the lastUpdatedBy field of this User.
     */
    public void setLastUpdateBy(String lastUpdateBy) {
        this.lastUpdateBy = lastUpdateBy;
    }
}
