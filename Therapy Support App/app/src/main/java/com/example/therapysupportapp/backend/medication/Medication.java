package com.example.therapysupportapp.backend.medication;
/*
Author: Jonathan Alexander
 */

public class Medication {
    private long id;
    private String brandName;
    private String commonName;
    private int dosage;
    private int dailyDosage;
    private int inventory;
    private int inventoryReminder;
    private int frequencyOn;
    private int frequencyOff;
    private int dayInInterval;
    private String dosageUnit;
    private static int IDNUM;


    public Medication(String brandName, String commonName, int dosage, int dailyDosage, int inventory, int inventoryReminder, String dosageUnit, int frequencyOn, int frequencyOff) {

        if (this.checkDatabase(commonName)) {
            return /*DatabaseEntry*/;
        } else {
            this.brandName = brandName;
            this.commonName = commonName;
            this.dosage = dosage;
            this.dailyDosage = dailyDosage;
            this.inventory = inventory;
            this.inventoryReminder = inventoryReminder;
            this.dosageUnit = dosageUnit;
            this.id = IDNUM + 1;
            this.frequencyOn = frequencyOn;
            this.dayInInterval = 0;
            this.frequencyOff = frequencyOff;
            IDNUM++;
        }
    }

    private boolean checkDatabase(String commonName) {
        /*TODO: check if 'common name' is already in the database, if so, fetch row of the medication and propagate it into . Else, return false.
         */
        return false;
    }

    private void pushIntakeNotification() {
        /*TODO: method to create a push notification, based on frequency and so on*/
    }

    private void pushInventoryNotification() {
        if (inventory == inventoryReminder) {
            /*TODO: method to create a push notification, based on remaining notification*/
        }
    }

    private void confirmTaken() {
        if (inventory - dailyDosage / dosage > 0) {
            /*TODO: method to create a push notification - "Invalid inventory - did you take {this.name and dosage} medication?"*/
            return;
        }
        inventory = inventory - dailyDosage / dosage;
        if (inventory <= inventoryReminder) {
            pushIntakeNotification();
        }
        /*TODO: update database: inventory=-1*/
        /*TODO: update database: confirm taken, dosage*/
    }

}
