package com.example.mentalhealth;

/**
* MedicationModal is a model that will be used to contain and post information respective to the data.
 *
 *
* */

public class MedicationModal {

    private long id;
    private String brandName;
    private String commonName;
    private String dosage, frequency;
    private String dosageUnit;

    /**
     * MedicationModal constructor will initialize the parameters into attributes.
     * @param brandName
     * @param commonName
     * @param frequency
     * @param dosage
     * @param dosageUnit
     */
    public MedicationModal(String brandName, String commonName, String frequency, String dosage, String dosageUnit){
        this.brandName =brandName;
        this.commonName = commonName;
        this.frequency = frequency;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
    }

    /**
     * dosage getter method
     * @return the string of dosage
     */
    public String getDosage() {
        return dosage;
    }

    /**
     * dosage setter method
     * @param dosage
     */
    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    /**
     * frequency setter method
     * @return a string of frequency
     */
    public String getFrequency() {
        return frequency;
    }

    /**
     * frequency setter method
     * @param frequency
     */
    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    /**
     * id getter method
     * @return
     */
    public long getId() {
        return id;
    }

    /**
     * id setter method
     * @param id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * brand setter method
     * @return return a string with name of the brand.
     */
    public String getBrandName() {
        return brandName;
    }

    /**
     * setter method for the brand name
     * @param brandName
     */
    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    /**
     * get the dosage unit
     * @return the string of the dosage unit
     */
    public String getDosageUnit() {
        return dosageUnit;
    }

    /**
     * setter method for the dosage unit
     * @param dosageUnit
     */
    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    /**
     * getter for the common name
     * @return
     */
    public String getCommonName() {
        return commonName;
    }

    /**
     * setter common name
     * @param commonName
     */
    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}
