package model;

import java.time.LocalDateTime;
/**This class is used to create FirstLevelDivision objects. FirstLevelDivision object fields model attributes found in first_level_divisions table
 * in client_schedule database */
public class FirstLevelDivision {
    private int divisionId;
    private String divisionName;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int countryID;
    /**This FirstLevelDivsion constructor uses all fields provided. Every FirstLevelDivision field is available as a parameter.*/

    public FirstLevelDivision(int divisionId, String divisionName, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy, int countryID) {
        this.divisionId = divisionId;
        this.divisionName = divisionName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.countryID = countryID;
    }

    /**
     * @return the divisionId that uniquely identifies different first level divisions in database.
     */
    public int getDivisionId() {
        return divisionId;
    }

    /**
     * @param divisionId the int divisionId that uniquely identifies different first level divisions in database.
     */
    public void setDivisionId(int divisionId) {
        this.divisionId = divisionId;
    }

    /**
     * @return this divisionName. The divisionName string given to this FirstLevelDivision.
     */
    public String getDivisionName() {
        return divisionName;
    }

    /**
     * @param divisionName a String that will set the divisionName field of this FirstLevelDivision.
     */
    public void setDivisionName(String divisionName) {
        this.divisionName = divisionName;
    }

    /**
     * @return this createDate. The createDate LocalDateTime given to this FirstLevelDivision.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate a LocalDateTime that will set the createDate field of this FirstLevelDivision.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return this createdBy. The createdBy string given to this FirstLevelDivision.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy a String that will set the createdBy field of this FirstLevelDivision.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return this lastUpdate. The lastUpdate LocalDateTime given to this FirstLevelDivision.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate a LocalDateTime that will set the lastUpdate field of this FirstLevelDivision.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return this lastUpdatedBy. The lastUpdatedBy string given to this FirstLevelDivision.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy a String that will set the lastUpdatedBy field of this FirstLevelDivision.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return this countryId. The countryId int given to this FirstLevelDivision.
     */
    public int getCountryID() {
        return countryID;
    }

    /**
     * @param countryID a int that will set the countryId field of this FirstLevelDivision.
     */
    public void setCountryID(int countryID) {
        this.countryID = countryID;
    }

    /** This method overrides the toString method and returns a String that consists of the FirstLevelDivision object's divisionName.
     * @return
     */
    public String toString() {
        return divisionName;
    }

}
