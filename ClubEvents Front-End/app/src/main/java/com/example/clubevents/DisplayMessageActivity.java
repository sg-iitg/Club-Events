package com.example.clubevents;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

public class DisplayMessageActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String item = intent.getStringExtra(ClubsList.EXTRA_MESSAGE);
        setTitle(item);

        setContentView(R.layout.activity_display_message);

        FireStoreContents fc = new FireStoreContents();
        fc.getDocument(item, this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case android.R.id.home:
                finish();
                return true;
        }
        return false;
    }
}
