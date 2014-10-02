package com.beeno.trucktracker;

import com.beeno.trucktracker.util.SystemUiHider;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;



/**
 * An example full-screen activity that shows and hides the system UI (i.e.
 * status bar and navigation/system bar) with user interaction.
 *
 * @see SystemUiHider
 */
public class TaskDescriptionActivity extends Activity {
    private EditText equipmentNumber;
    private TextView bookingNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fullscreen);
        Intent intent = getIntent();
        String eNum = intent.getStringExtra("E#");
        String bNum = intent.getStringExtra("B#");
        equipmentNumber = (EditText) findViewById(R.id.editEquipment);
        bookingNumber = (TextView) findViewById(R.id.bookingValue);
        equipmentNumber.setText(eNum);
        bookingNumber.setText(bNum);

    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

    }

}
