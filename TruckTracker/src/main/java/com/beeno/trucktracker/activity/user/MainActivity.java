package com.beeno.trucktracker.activity.user;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.beeno.trucktracker.R;
import com.beeno.trucktracker.amazon.DynamoDBHelper;
import com.beeno.trucktracker.model.dao.PickUpTask;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends ActionBarActivity {


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
                Intent intent = new Intent(getApplicationContext(), TaskDescriptionActivity.class);
                CharSequence text = rows.get(i);
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                intent.putExtra("taskDesc", "2Km to Chicago");
                intent.putExtra("bookingNumber", rows.get(i));
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

    public  class InitAmazonAWSTask extends AsyncTask<Void, Void, Void> {

        List<PickUpTask> tasks;
        public Void doInBackground(Void...voids) {
//            AmazonUtil.setCognitoProvider(getApplicationContext());
//            DynamoDBHelper.initDbClient();
            //TODO: Save User name in prefs

            tasks = new DynamoDBHelper.DynamoDbGetUsersTaskAction("beeno").getPickupTaskForUser();
            return null;
        }


        public void onPostExecute(Void result ) {
            adapter.clear();
            for(PickUpTask task : tasks) {
               adapter.add(task.getBookingNumber());
            }
            adapter.notifyDataSetChanged();
        }
    }

}
