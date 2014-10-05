package com.beeno.trucktracker;

import com.beeno.trucktracker.amazon.DynamoDBHelper;
import com.beeno.trucktracker.model.PickUpTask;
import com.beeno.trucktracker.util.SystemUiHider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TaskDescriptionActivity extends Activity {
    private EditText equipmentNumberText;
    private TextView bookingNumberView;
    private TextView taskDescView;
    private String bookingNumberValue;
    private String taskDescValue;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Intent intent = getIntent();

        bookingNumberValue  = intent.getStringExtra("bookingNumber");
        taskDescValue = intent.getStringExtra("taskDesc");
        equipmentNumberText = (EditText) findViewById(R.id.editEquipment);
        bookingNumberView = (TextView) findViewById(R.id.bookingValue);
        taskDescView = (TextView) findViewById(R.id.taskDesc);
        saveButton = (Button) findViewById(R.id.save_details_button);
        bookingNumberView.setText(bookingNumberValue);
        taskDescView.setText(taskDescValue);

        setUpOnClickListeners();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

    private void setUpOnClickListeners() {
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String eNumber = equipmentNumberText.getText().toString();
                if(! ("".equals(eNumber))) {
                    DynamoDBHelper.DynamoExeuteAddToTableAction action = new DynamoDBHelper.DynamoExeuteAddToTableAction();
                    action.pickUpTask = new PickUpTask(eNumber, bookingNumberValue, "N/A", "Beeno");
                    new DynamoDBHelper.DynamoTableTask(action).execute();
                }
                 else {
                    Toast.makeText(getApplicationContext(), "The Equipment Number needs to be filled out !", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

}
