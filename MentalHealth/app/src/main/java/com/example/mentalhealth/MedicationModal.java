package com.example.mentalhealth;

public class MedicationModal {
    private long id;
    private String brandName;
    private String commonName;
    private String dosage, frequency;
    private String dosageUnit;

    public MedicationModal(String brandName, String commonName, String frequency, String dosage, String dosageUnit){

        this.brandName =brandName;
        this.commonName = commonName;
        this.frequency = frequency;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getFrequency() {
        return frequency;
    }

    public void setFrequency(String frequency) {
        this.frequency = frequency;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getDosageUnit() {
        return dosageUnit;
    }

    public void setDosageUnit(String dosageUnit) {
        this.dosageUnit = dosageUnit;
    }

    public String getCommonName() {
        return commonName;
    }

    public void setCommonName(String commonName) {
        this.commonName = commonName;
    }
}
