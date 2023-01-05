package model;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.time.LocalDateTime;
/**This class is used to create Customer objects. Customer object fields model attributes found in customer table
 * in client_schedule database */
public class Customer {
    private int customerID;
    private String customerName;
    private String customerAddress;
    private String postalCode;
    private String customerPhone;
    private LocalDateTime createDate;
    private String createdBy;
    private LocalDateTime lastUpdate;
    private String lastUpdatedBy;
    private int divisionID;
    private int country;
    /**This Customer constructor uses all fields provided. Every Customer field is available as a parameter.*/
    public Customer(int customerID, String customerName, String customerAddress,
                    String postalCode, String customerPhone,LocalDateTime createDate,String createdBy,LocalDateTime lastUpdate,String lastUpdatedBy, int divisionID, int country) {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.customerPhone = customerPhone;
        this.createDate = createDate;
        this.createdBy = createdBy;
        this.lastUpdate = lastUpdate;
        this.lastUpdatedBy = lastUpdatedBy;
        this.divisionID = divisionID;
        this.country = country;
    }
    //country was in the next constructor but I don't think I need it
    public Customer(int customerID, String customerName, String customerAddress,
                    String postalCode, String customerPhone, int divisionID,int countryID )  {
        this.customerID = customerID;
        this.customerName = customerName;
        this.customerAddress = customerAddress;
        this.postalCode = postalCode;
        this.customerPhone = customerPhone;
        this.createDate = null;
        this.createdBy = null;
        this.lastUpdate = null;
        this.lastUpdatedBy = null;
        this.divisionID = divisionID;
        this.country = countryID;
    }
    public Customer(){};

    /**
     * @return this country. The country string given to this Customer.
     */
    public int getCountry() {
        return country;
    }

    /**
     * @param country an int that will set the country field of this Customer.
     */
    public void setCountry(int country) {
        this.country = country;
    }

    /**
     * @return the customerID that uniquely identifies different customers in database.
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * @param customerID the customerID that uniquely identifies different customers in database.
     */
    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    /**
     * @return this customerName. The customerName string given to this Customer.
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * @param customerName a String that will set the customerName field of this Customer.
     */
    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    /**
     * @return this customerAddress. The customerAddress string given to this Customer.
     */
    public String getCustomerAddress() {
        return customerAddress;
    }

    /**
     * @param customerAddress a String that will set the customerAddress field of this Customer.
     */
    public void setCustomerAddress(String customerAddress) {
        this.customerAddress = customerAddress;
    }

    /**
     * @return this postalCode. The postalCode string given to this Customer.
     */
    public String getPostalCode() {
        return postalCode;
    }

    /**
     * @param postalCode a String that will set the postalCode field of this Customer.
     */
    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    /**
     * @return this customerPhone. The customerPhone string given to this Customer.
     */
    public String getCustomerPhone() {
        return customerPhone;
    }

    /**
     * @param customerPhone a String that will set the customerPhone field of this Customer.
     */
    public void setCustomerPhone(String customerPhone) {
        this.customerPhone = customerPhone;
    }

    /**
     * @return this createDate. The createDate LocalDateTime given to this Customer.
     */
    public LocalDateTime getCreateDate() {
        return createDate;
    }

    /**
     * @param createDate a LocalDateTime that will set the createDate field of this Customer.
     */
    public void setCreateDate(LocalDateTime createDate) {
        this.createDate = createDate;
    }

    /**
     * @return this createdBy. The createdBy string given to this Customer.
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy a String that will set the createdBy field of this Customer.
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return this lastUpdate. The lastUpdate LocalDateTine given to this Customer.
     */
    public LocalDateTime getLastUpdate() {
        return lastUpdate;
    }

    /**
     * @param lastUpdate a LocalDateTime that will set the lastUpdate field of this Customer.
     */
    public void setLastUpdate(LocalDateTime lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    /**
     * @return this lastUpdateBy. The lastUpdatedBy string given to this Customer.
     */
    public String getLastUpdatedBy() {
        return lastUpdatedBy;
    }

    /**
     * @param lastUpdatedBy a String that will set the lastUpdated field of this Customer.
     */
    public void setLastUpdatedBy(String lastUpdatedBy) {
        this.lastUpdatedBy = lastUpdatedBy;
    }

    /**
     * @return this divisionId. The divisionId int given to this Customer.
     */
    public int getDivisionID() {
        return divisionID;
    }

    public void setDivisionID(int divisionID) {
        this.divisionID = divisionID;
    }
    /** This method overrides the toString method and returns a String that consists of the Customer object's customerName.
     * @return
     */
    public String toString() {
        return customerName;
    }


}

