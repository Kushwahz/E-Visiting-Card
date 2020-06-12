package com.wordpress.helpmevishal.vishalsingh;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class explore extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_explore);
        Button Download = (Button)findViewById(R.id.download_hmva);
        Download.setOnClickListener(this);
        Button next = (Button) findViewById(R.id.button_hmva);
        next.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.button_hmva:startActivity(new Intent(getApplicationContext(),hmv_website.class));
                break;
            case R.id.download_hmva:Uri uri = Uri.parse("https://drive.google.com/open?id=0ByA1LNYTorRQLXRQbzJnZzZ3TzA"); // missing 'http://' will cause crashed
                Intent intent = new Intent(Intent.ACTION_VIEW, uri);
                startActivity(intent);
                Toast.makeText(this, "Starting Download...", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
