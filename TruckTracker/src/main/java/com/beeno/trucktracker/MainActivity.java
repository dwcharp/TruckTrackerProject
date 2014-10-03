package com.beeno.trucktracker;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.os.Build;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.beeno.trucktracker.amazon.AmazonUtil;
import com.beeno.trucktracker.amazon.DynamoDBHelper;

public class MainActivity extends ActionBarActivity {

    String countries[] = {"US","Africa","UK"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        new InitAmazonAWSTask().execute();

        ArrayAdapter adapter = new ArrayAdapter<String>(this, R.layout.activity_listview,
                R.id.loading_point_label, countries);
        ListView listView = (ListView) findViewById(R.id.country_list);
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(getApplicationContext(), TaskDescriptionActivity.class);
                CharSequence text = countries[i];
                Toast.makeText(getApplicationContext(), text, Toast.LENGTH_SHORT).show();
                intent.putExtra("bookingNumber", "9876");
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

    public class InitAmazonAWSTask extends AsyncTask<Void, Void, Void> {
        public Void doInBackground(Void...voids) {
            AmazonUtil.setCognitoProvider(getApplicationContext());
            DynamoDBHelper.initDbClient();
            return null;
        }
    }

}
