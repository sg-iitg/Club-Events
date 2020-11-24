package com.example.clubevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class ContactUs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Contact Us");
        setContentView(R.layout.activity_contact_us);
        Intent intent = getIntent();
    }
}
