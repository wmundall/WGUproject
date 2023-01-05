package model;

import java.time.LocalDate;
import java.time.LocalDateTime;
/**This class is used to create Countries objects. Countries object fields model attributes found in countries table
 * in client_schedule database */
public class Countries {
    private int countryId;
    private String countryName;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    /**This Countries constructor uses all fields provided. Every Countries field is available as a parameter.*/

    public Countries(int countryId, String countryName, LocalDateTime createDate, String createdBy, LocalDateTime lastUpdate, String lastUpdatedBy) {
        this.countryId = countryId;
        this.countryName = countryName;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return the countryId that uniquely identifies different countries in database.
     */
    public int getCountryId() {
        return countryId;
    }

    /**
     * @param countryId the int ID that uniquely identifies different countries in database.
     */
    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    /**
     * @return  this countryName. The countryName string given to this Countries.
     */
    public String getCountryName() {
        return countryName;
    }

    /**
     * @param countryName a String that will set the countryName field of this Countries.
     */
    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    /**
     * @return this createDate. The createDate LocalDateTime given to this Countries.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate a LocalDateTime that will set the createDate field of this Countries.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return this createdBy. The createdBy String given to this Countries.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy a String that will set the createdBy field of this Countries.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return this lastUpdate. The lastUpdate LocalDateTime given to this Countries.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate a LocalDateTime that will set the lastUpdate field of this Countries.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return this lastUpdatedBy. The lastUpdatedBy String given to this Countries.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy a String that will set the lastUpdateBy field of this Countries.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /** This method overrides the toString method and returns a String that consists of the Countries object's countryName.
     * @return
     */
    public String toString() {
        return countryName;
    }
}
