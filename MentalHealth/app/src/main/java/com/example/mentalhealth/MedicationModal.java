package com.example.mentalhealth;

public class MedicationModal {
    private long id;
    private String brandName;
    private String commonName;
    private int dosage, frequency;
    private String dosageUnit;

    public MedicationModal(long id, String brandName, String commonName, int frequency, int dosage, String dosageUnit){
        this.id = id;
        this.brandName =brandName;
        this.commonName = commonName;
        this.frequency = frequency;
        this.dosage = dosage;
        this.dosageUnit = dosageUnit;
    }

    public int getDosage() {
        return dosage;
    }

    public void setDosage(int dosage) {
        this.dosage = dosage;
    }

    public int getFrequency() {
        return frequency;
    }

    public void setFrequency(int frequency) {
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
