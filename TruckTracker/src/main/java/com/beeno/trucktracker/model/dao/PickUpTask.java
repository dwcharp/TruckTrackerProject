package com.beeno.trucktracker.model.dao;


import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dcharp on 10/1/14.
 */

@DynamoDBTable(tableName="Task")
public class PickUpTask {
     private String loadingPoint;
     private String Booking_Number;
     private String equipmentNumber;


    private String userId;

    public PickUpTask(){}

    public PickUpTask(String loadingPoint, String bookingNumber) {
        this.loadingPoint = loadingPoint;
        this.Booking_Number = bookingNumber;
    }

    public PickUpTask(String equipmentNumber, String bookingNumber, String loadingPoint) {
        this.equipmentNumber = equipmentNumber;
        this.Booking_Number = bookingNumber;
        this.loadingPoint = loadingPoint;
    }


    public PickUpTask(String  equipmentNumber, String bookingNumber, String loadingPoint, String userId) {
        this.equipmentNumber = equipmentNumber;
        this.Booking_Number = bookingNumber;
        this.loadingPoint = loadingPoint;
        this.userId = userId;
    }


    @DynamoDBHashKey(attributeName = "Booking_Number")
    public String getBookingNumber() {
        return  Booking_Number;
    }
    public void setBookingNumber(String bookNum) { Booking_Number = bookNum; }

    @DynamoDBAttribute(attributeName = "Equipment_Number")
    public String getEquipmentNumber() {
        return equipmentNumber;
    }
    public  void setEquipmentNumber(String number) { equipmentNumber = number;}

    @DynamoDBAttribute(attributeName = "Loading_Point")
    public String getLoadingPoint() {
        return  loadingPoint;
    }
    public void setLoadingPoint(String lPoint) {loadingPoint = lPoint;}

    @DynamoDBAttribute(attributeName = "User_ID")
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }
}
