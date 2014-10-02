package com.beeno.trucktracker.model;

/**
 * Created by dcharp on 10/1/14.
 */
public class PickUpTask {
    final private String loadingPoint;
    final private Integer bookingNumber;
    private Integer equipmentNumber;

    public PickUpTask(String loadingPoint, Integer bookingNumber) {
        this.loadingPoint = loadingPoint;
        this.bookingNumber = bookingNumber;
        this.equipmentNumber = null;
    }

    public String getLoadingPoint() {return  loadingPoint;}
    public Integer getBookingNumber() {return  bookingNumber;}
    public  void setEquipmentNumber(Integer number) {
        equipmentNumber = number;
    }
    public Integer getEquipmentNumber() {return equipmentNumber;}


}
