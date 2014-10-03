package com.beeno.trucktracker.amazon;
import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.beeno.trucktracker.model.PickUpTask;


/**
 * Created by dcharp on 10/2/14.
 */
public class DynamoDBHelper {
    private static AmazonDynamoDBClient dbClient;

    public static void initDbClient() {
        dbClient = AmazonUtil.getDynamnoDBClient();
    }
    public static void addDataToTable(PickUpTask pickUpTask) {
        DynamoDBMapper mapper = new DynamoDBMapper(dbClient);
        try{
            mapper.save(pickUpTask);
        } catch (Exception e) {
            e.toString();
        }

    }
    public static class DynamoAddToTableTask extends AsyncTask<Void,Void,Void> {
        PickUpTask pickUpTask;
        public DynamoAddToTableTask(PickUpTask pt) {
            super();
            pickUpTask = pt;
        }
        public Void doInBackground(Void...voids) {
            addDataToTable(pickUpTask);
            return null;
        }
    }
}
