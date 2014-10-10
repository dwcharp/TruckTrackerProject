package com.beeno.trucktracker.activity.admin.task;

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

import com.beeno.trucktracker.R;
import com.beeno.trucktracker.activity.user.EditPickupTaskActivity;
import com.beeno.trucktracker.amazon.DynamoDBHelper;
import com.beeno.trucktracker.model.dao.PickUpTask;

import java.util.ArrayList;
import java.util.List;

public class PickupTaskViewActivity extends ActionBarActivity {
    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> rows = new ArrayList<String>();
    private String[] taskStrings = {};
    private List<PickUpTask> pickUpTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pickup_task_view);
        pickUpTasks = new ArrayList<PickUpTask>();
        ListView listView = (ListView)findViewById(R.id.admin_view_task);
        mAdapter = new ArrayAdapter<String>(getApplicationContext(),  R.layout.activity_listview,
                R.id.loading_point_label, rows);
        mAdapter.addAll(taskStrings);
        listView.setAdapter(mAdapter);
        updateListView();
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), EditPickupTaskActivity.class);
                intent.putExtra(PickUpTask.PICK_UP_KEY, pickUpTasks.get(i));
                startActivity(intent);
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.pickup_task_view, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void updateListView() {
        new UpdateListView().execute();
    }


    public  class UpdateListView extends AsyncTask<Void, Void, Void> {
        public Void doInBackground(Void...voids) {
            new DynamoDBHelper.DynamoDbGetAllTaskAction(pickUpTasks).dynamoExecuteAction();
            //store these task locally
            return null;
        }

        public void onPostExecute(Void result ) {
            mAdapter.clear();
            for (PickUpTask task : pickUpTasks) {
                mAdapter.add(task.getBookingNumber());
            }
            mAdapter.notifyDataSetChanged();

        }
    }
}
