package com.sivaram.session12assignment2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {


    // Declare Required Objects to TypeCast from xml to access in java [Edit Text View and Button View]
    EditText nameEditText, ageEditText, phoneNumberEditText, cityEditText;
    Button saveButton, showButton;

    // Declare Shared Preferences Object
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // TypeCast All Views to Java Objects for Reference.
        nameEditText = (EditText) findViewById(R.id.nameEditText);
        ageEditText = (EditText) findViewById(R.id.ageEditText);
        phoneNumberEditText = (EditText) findViewById(R.id.phoneNumberEditText);
        cityEditText = (EditText) findViewById(R.id.cityEditText);

        saveButton = (Button) findViewById(R.id.saveButton);
        showButton = (Button) findViewById(R.id.showButton);

        // Initialize Shared Preference Object which might be used before saving any information. via show button
        sharedPreferences = getSharedPreferences("sharedPreferenceAssignment", Context.MODE_PRIVATE);

        // Setup On Click Listener for Save Button. Which will save the required information into shared preferences.
        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SharedPreferences.Editor sharedPrefEditor = sharedPreferences.edit();

                sharedPrefEditor.putString("userName",nameEditText.getText().toString());
                sharedPrefEditor.putString("age",ageEditText.getText().toString());
                sharedPrefEditor.putString("phonenumber",phoneNumberEditText.getText().toString());
                sharedPrefEditor.putString("city",cityEditText.getText().toString());

                sharedPrefEditor.commit();

                // Toast on Successful save.
                Toast.makeText(MainActivity.this, "Shared Preferences Stored successfully...", Toast.LENGTH_SHORT).show();

                // Clear Data From TextView

                nameEditText.setText("");
                ageEditText.setText("");
                phoneNumberEditText.setText("");
                cityEditText.setText("");
            }
        });


        // Setup on Click Listener for Show Button. Which will read the data from shared preferences and display as TOAST
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (sharedPreferences.contains("userName")){
                    String showSharedPreferencesData = ""; // Create String to Store Data from shared preference.

                    // Read Data From Shared Preferences.
                    showSharedPreferencesData = " User Name : " + sharedPreferences.getString("userName","") // Get user Name
                                                + " Age : " + sharedPreferences.getString("age","")  // Get Saved Age
                                                + " Phone Number : " +  sharedPreferences.getString("phonenumber","") // Get Phone Number
                                                + " City " + sharedPreferences.getString("city",""); // Get City

                    // Display Read Data As TOAST
                    Toast.makeText(MainActivity.this, showSharedPreferencesData, Toast.LENGTH_SHORT).show();

                }
                else{
                    Toast.makeText(MainActivity.this, "No data found in Shared Preferences for This APP.", Toast.LENGTH_SHORT).show();
                }
            }
        });



    }
}
