package com.beeno.trucktracker.model;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dcharp on 10/1/14.
 */

@DynamoDBTable(tableName="Task")
public class PickUpTask {
    final private String loadingPoint;
    final private String Booking_Number;
    private String equipmentNumber;

    public PickUpTask(String loadingPoint, String bookingNumber) {
        this.loadingPoint = loadingPoint;
        this.Booking_Number = bookingNumber;
    }

    public PickUpTask(String  equipmentNumber, String bookingNumber, String loadingPoint) {
        this.equipmentNumber = equipmentNumber;
        this.Booking_Number = bookingNumber;
        this.loadingPoint = loadingPoint;
    }

    @DynamoDBHashKey(attributeName = "Booking_Number")
    public String getBookingNumber() {
        return  Booking_Number;
    }

    @DynamoDBAttribute(attributeName = "Equipment_Number")
    public String getEquipmentNumber() {
        return equipmentNumber;
    }
    public  void setEquipmentNumber(String number) { equipmentNumber = number;}

    @DynamoDBAttribute(attributeName = "Loading_Point")
    public String getLoadingPoint() {
        return  loadingPoint;
    }

}
