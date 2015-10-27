package com.example.leroy.displayajoke;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

public class DisplayAJokeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_ajoke);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Intent intent = getIntent();
        String joke = intent.getStringExtra("joke");
        TextView jokeView = (TextView) findViewById(R.id.jokeTextView);
        jokeView.setText(joke);
        String source = intent.getStringExtra("source");
        TextView sourceView = (TextView) findViewById(R.id.jokeSource);
        sourceView.setText(source);
    }

    @Override
    public Intent getSupportParentActivityIntent() {
        return new Intent(this, getIntent().getClass());
    }
}