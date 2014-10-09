package com.beeno.trucktracker.amazon;
import android.os.AsyncTask;

import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBMapper;
import com.amazonaws.mobileconnectors.dynamodbv2.dynamodbmapper.DynamoDBScanExpression;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.model.AttributeValue;
import com.amazonaws.services.dynamodbv2.model.ComparisonOperator;
import com.amazonaws.services.dynamodbv2.model.Condition;
import com.beeno.trucktracker.model.dao.PickUpTask;
import com.beeno.trucktracker.model.dao.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by dcharp on 10/2/14.
 */
public class DynamoDBHelper {
    private static AmazonDynamoDBClient dbClient;
    private static  DynamoDBMapper mapper;

    public static void initDbClient() {
        dbClient = AmazonUtil.getDynamnoDBClient();
        mapper = new DynamoDBMapper(dbClient);
    }


    public static class DynamoTableTask extends AsyncTask<Void,Void,Void> {
        DynamoDbAction dbAction;
        public DynamoTableTask(DynamoDbAction action) {
            super();
            dbAction = action;
        }
        public Void doInBackground(Void...voids) {
            dbAction.dynamoExecuteAction();
            return null;
        }
    }

    public interface DynamoDbAction {
        public void dynamoExecuteAction();
    }

    public static class DynamoDbGetUsersTaskAction implements DynamoDbAction {
        private String userId;

        public DynamoDbGetUsersTaskAction(String id) {
            this.userId = id;
        }
        public void dynamoExecuteAction() {
            getPickupTaskForUser();
        }

        public  List<PickUpTask> getPickupTaskForUser() {
            List<PickUpTask> tasks = new ArrayList<PickUpTask>();
            Map<String, Condition> key = new HashMap<String, Condition>();
            DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

            Condition KeyCondition = new Condition()
                    .withComparisonOperator(ComparisonOperator.EQ.toString())
                    .withAttributeValueList(new AttributeValue().withS(userId));


            scanExpression.addFilterCondition("User_ID", KeyCondition);

            try {
                 tasks = mapper.scan(PickUpTask.class, scanExpression);
            } catch (Exception e) {
                e.toString();
            }


            return tasks;
        }

        public void setUserId(String id) {this.userId = id;}
    }


    public static class DynamoExecuteAddToTableAction implements DynamoDbAction {
        public PickUpTask pickUpTask;
        public void dynamoExecuteAction() {
            try {
                mapper.save(pickUpTask);
            } catch (Exception e) {
                e.toString();
            }
        }
    }

    public static boolean CheckUserCredits(User user) {
        Map<String, Condition> key = new HashMap<String, Condition>();
        DynamoDBScanExpression scanExpression = new DynamoDBScanExpression();

        Condition KeyCondition = new Condition()
                .withComparisonOperator(ComparisonOperator.EQ.toString())
                .withAttributeValueList(new AttributeValue().withS(user.getUserId()));
        scanExpression.addFilterCondition("User_ID", KeyCondition);
        List<User> userFromDB = null;
        try {
            userFromDB = mapper.scan(User.class, scanExpression);
        } catch ( Exception e) {
            e.toString();
        }

        if(!userFromDB.isEmpty()) {
            return user.getPassword().equals(userFromDB.get(0).getPassword());
        }
        return  false;

    }


    public static class DynamoExecuteCreateUserAction implements DynamoDbAction {
        public User user;

        @Override
        public void dynamoExecuteAction() {
            try {
                //add check to see if user exist
                mapper.save(user);
            } catch (Exception e) {
                e.toString();
            }
        }
    }

    public static class DynamoExecuteCreateTaskAction implements DynamoDbAction {
        PickUpTask pickUpTask;
        public DynamoExecuteCreateTaskAction(PickUpTask pickUpTask) {
            this.pickUpTask = pickUpTask;
        }
        @Override
        public void dynamoExecuteAction() {
            try {
                mapper.save(pickUpTask);
            } catch (Exception e) {
                e.toString();
            }
        }
    }
}
