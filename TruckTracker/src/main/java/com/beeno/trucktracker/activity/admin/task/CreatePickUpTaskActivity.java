package com.beeno.trucktracker.activity.admin.task;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.beeno.trucktracker.R;
import com.beeno.trucktracker.amazon.DynamoDBHelper;
import com.beeno.trucktracker.model.dao.PickUpTask;


public class CreatePickUpTaskActivity extends ActionBarActivity {
    private EditText loadingPointText;
    private EditText bookingNumberText;
    private EditText assigneeText;
    private Button createTaskButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_pick_up_task);
        loadingPointText = (EditText)findViewById(R.id.loadingPointInput);
        bookingNumberText =(EditText)findViewById(R.id.bookingNumberInput);
        assigneeText = (EditText)findViewById(R.id.assigneeInput);
        createTaskButton = (Button)findViewById(R.id.create_task);
        setOnClickListeners();

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_pick_up_task, menu);
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

    private void setOnClickListeners() {
        createTaskButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String loadingPointValue = loadingPointText.getText().toString();
                String bookingNumberValue = bookingNumberText.getText().toString();
                String assingedUser = assigneeText.getText().toString();

                if(!isValidInput(loadingPointValue, bookingNumberValue, assingedUser))
                    return;
                DynamoDBHelper.DynamoExecuteCreateTaskAction dynamoExecuteCreateUserAction =
                        new DynamoDBHelper.DynamoExecuteCreateTaskAction(new PickUpTask(
                            "", bookingNumberValue, loadingPointValue, assingedUser
                        ));
                new DynamoDBHelper.DynamoTableTask(dynamoExecuteCreateUserAction).execute();
            }
        });
    }

    private boolean isValidInput(String l, String b, String a) {
        return (!l.equals("") ) && (!b.equals("")) && (!a.equals("")) ;
    }
}
