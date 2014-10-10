package com.beeno.trucktracker.activity.user;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.beeno.trucktracker.R;
import com.beeno.trucktracker.amazon.DynamoDBHelper;
import com.beeno.trucktracker.model.dao.PickUpTask;

import java.util.ArrayList;
import java.util.List;

public class UserMainActivity extends Activity {


    List<PickUpTask> pickUpTasks;
    ArrayList<String> rows = new ArrayList<String>();
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        adapter = new ArrayAdapter<String>(this, R.layout.activity_listview,
                R.id.loading_point_label, rows);
        new InitAmazonAWSTask().execute();

        ListView listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                PickUpTask pickUpTask = pickUpTasks.get(i);
                Intent intent = new Intent(getApplicationContext(), EditPickupTaskActivity.class);
                intent.putExtra(PickUpTask.PICK_UP_KEY, pickUpTasks.get(i));
                startActivity(intent);
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        switch (item.getItemId()) {
            case R.id.action_settings:
                return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private PickUpTask findPickUpTask(String bookingNumber) {
        for(PickUpTask task : pickUpTasks) {
            if(task.getBookingNumber().equals(bookingNumber))
                return task;
        }
        return null;
    }
    public  class InitAmazonAWSTask extends AsyncTask<Void, Void, Void> {


        public Void doInBackground(Void...voids) {
//            AmazonUtil.setCognitoProvider(getApplicationContext());
//            DynamoDBHelper.initDbClient();
            pickUpTasks = new DynamoDBHelper.DynamoDbGetUsersTaskAction("beeno").getPickupTaskForUser();
            return null;
        }


        public void onPostExecute(Void result ) {
            adapter.clear();
            for(PickUpTask task : pickUpTasks) {
               adapter.add(task.getBookingNumber());
            }
            adapter.notifyDataSetChanged();
        }
    }
}
