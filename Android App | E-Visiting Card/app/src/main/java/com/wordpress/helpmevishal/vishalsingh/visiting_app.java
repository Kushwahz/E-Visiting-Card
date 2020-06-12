package com.wordpress.helpmevishal.vishalsingh;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class visiting_app extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_visiting_app);
        Button next = (Button) findViewById(R.id.button_visita);
        next.setOnClickListener(this);
        Button share = (Button) findViewById(R.id.share_visit);
        share.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.button_visita: startActivity(new Intent(getApplicationContext(),cryptography.class));
                break;
            case R.id.share_visit : Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Want an Android App for you? \n"+"Download my (Vishal Singh) visiting app to explore my Android App projects. Follow this link https://drive.google.com/open?id=0ByA1LNYTorRQYkhnN29sLU1CckU");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
        }

    }
}
