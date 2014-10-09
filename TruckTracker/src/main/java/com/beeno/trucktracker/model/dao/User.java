package com.beeno.trucktracker.model.dao;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBAttribute;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBHashKey;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBTable;

/**
 * Created by dcharp on 10/4/14.
 */

@DynamoDBTable(tableName = "Users")
public class User {

    private String password;
    private String userName;

    private String userId;
    private int role;

    public User() {}

    public User(String user, String pass) {
        this.userId  = user;
        this.password = pass;
    }


    public User(String userName, String userId, String pass) {
        this.userId  = userId;
        this.userName = userName;
        this.password = pass;
    }

    @DynamoDBAttribute(attributeName = "password")
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    @DynamoDBHashKey(attributeName = "UserName")
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

    @DynamoDBAttribute(attributeName = "User_ID")
    public String getUserId() { return userId;}
    public void setUserId(String userId) { this.userId = userId;}


}
