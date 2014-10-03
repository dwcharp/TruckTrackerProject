package com.beeno.trucktracker.amazon;

import android.content.Context;

import com.amazonaws.auth.CognitoCachingCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import  com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;

/**
 * Created by dcharp on 10/2/14.
 */
public class AmazonUtil {

    private  static CognitoCachingCredentialsProvider cognitoProvider;
    private static AmazonDynamoDBClient dynamoDBClient = null;

    public static void setCognitoProvider(Context context) {
        if(cognitoProvider == null) {
            cognitoProvider = new CognitoCachingCredentialsProvider(
                    context, // get the context for the current activity
                    "598124958357",
                    "us-east-1:5639bdac-2385-4b51-bb15-9c6cc7f40ef0",
                    "arn:aws:iam::598124958357:role/Cognito_DcharpAuth_DefaultRole",
                    "arn:aws:iam::598124958357:role/Cognito_DcharpAuth_DefaultRole",
                    Regions.US_EAST_1
            );
        }

    }

    public static AmazonDynamoDBClient getDynamnoDBClient() {
        if(dynamoDBClient == null) {
            dynamoDBClient = new AmazonDynamoDBClient(cognitoProvider);
            dynamoDBClient.setRegion(Region.getRegion(Regions.US_EAST_1));
        }
        return dynamoDBClient;
    }

}
