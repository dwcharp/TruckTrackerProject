package com.beeno.trucktracker.model;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;

/**
 * Created by dcharp on 10/4/14.
 */

@DynamoDBTable(tableName = "Users")
public class User {

    private String password;
    private String userName;
    private int role;

    public User() {}

    public User(String user, String pass) {
        this.userName  = user;
        this.password = pass;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @DynamoDBAttribute(attributeName = "User_Name")
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    @DynamoDBAttribute(attributeName = "role")
    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }


}
