package com.beeno.trucktracker.activity.admin;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.beeno.trucktracker.R;
import com.beeno.trucktracker.model.admin.AdminConsoleItems;

import java.util.ArrayList;


public class AdminConsoleActivity extends ActionBarActivity {

    private ArrayAdapter<String> mAdapter;
    private ArrayList<String> rows = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_console);
        ListView listView = (ListView) findViewById(R.id.adminListView);
        mAdapter = new ArrayAdapter<String>(getApplicationContext(),  R.layout.activity_listview,
                R.id.loading_point_label, rows);
        mAdapter.addAll(AdminConsoleItems.listItemsTitle);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), AdminConsoleItems.getClass(i));
                startActivity(intent);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.admin_console, menu);
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
}
