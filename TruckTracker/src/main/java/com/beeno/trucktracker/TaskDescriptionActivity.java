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



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TaskDescriptionActivity extends Activity {
    private EditText equipmentNumberText;
    private TextView bookingNumberText;
    private String bookingNumberValue;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Intent intent = getIntent();

        bookingNumberValue  = intent.getStringExtra("bookingNumber");
        equipmentNumberText = (EditText) findViewById(R.id.editEquipment);
        bookingNumberText = (TextView) findViewById(R.id.bookingValue);
        saveButton = (Button) findViewById(R.id.save_details_button);
        bookingNumberText.setText(bookingNumberValue);

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
                    new DynamoDBHelper.DynamoAddToTableTask(new PickUpTask(eNumber, bookingNumberValue, null)).execute();
                }
                 else {
                    //pop up a toast
                }
            }
        });
    }

}
