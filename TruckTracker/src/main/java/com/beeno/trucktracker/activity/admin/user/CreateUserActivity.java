package com.beeno.trucktracker.activity.admin.user;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.beeno.trucktracker.R;
import com.beeno.trucktracker.amazon.DynamoDBHelper;
import com.beeno.trucktracker.model.dao.User;


public class CreateUserActivity extends ActionBarActivity {
    private EditText name;
    private EditText userId;
    private EditText password;
    private Button createUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_user);
        name = (EditText) findViewById(R.id.realName);
        userId = (EditText) findViewById(R.id.userId);
        password = (EditText) findViewById(R.id.password);
        createUser = (Button) findViewById(R.id.createUser);
        setOnclickListeners();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.create_user, menu);
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

    private void setOnclickListeners() {
        createUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = name.getText().toString();
                String userIdValue = userId.getText().toString();
                String passwordValue = password.getText().toString();
                if (fieldsAreValid(nameValue, userIdValue, passwordValue)) {
                    Toast.makeText(getApplicationContext(), "Please fill out all of the fields !!",
                            Toast.LENGTH_SHORT).show();
                    return;
                }

                DynamoDBHelper.DynamoExecuteCreateUserAction createUserAction =
                        new DynamoDBHelper.DynamoExecuteCreateUserAction();
                createUserAction.user = new User(nameValue, userIdValue, passwordValue);
                new DynamoDBHelper.DynamoTableTask(createUserAction).execute();
            }
        });
    }

    private boolean fieldsAreValid(String userName, String userId, String password) {
        return (!userName.equals("") ) && (userId.equals("")) && (!password.equals("")) ;
    }
}
