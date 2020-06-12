package com.wordpress.helpmevishal.vishalsingh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class Parivartan2K14 extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_parivartan2_k14);
        Button next = (Button) findViewById(R.id.button_par);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),want_app.class));

    }
}
