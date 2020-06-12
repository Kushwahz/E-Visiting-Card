package com.wordpress.helpmevishal.vishalsingh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class hmv_website extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hmv_website);
        Button visit = (Button)findViewById(R.id.visit_hmw);
        visit.setOnClickListener(this);
        Button next = (Button) findViewById(R.id.button_hmw);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_hmw:startActivity(new Intent(getApplicationContext(),visiting_app.class));
                break;
            case R.id.visit_hmw:Uri uri = Uri.parse("https://helpmevishal.wordpress.com/"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                break;
        }

    }
}
