package com.wordpress.helpmevishal.vishalsingh;

import android.Manifest;
import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    ImageView gplus, linkedin, twitter;
    LinearLayout explore_layout, share_layout, want_layout, cv_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        android.support.v7.app.ActionBar supportActionBar = getSupportActionBar();
        supportActionBar.show();
        supportActionBar.setLogo(R.drawable.ic_vishal);
        gplus = (ImageView) findViewById(R.id.ic_gplus);
        gplus.setOnClickListener(this);
        linkedin = (ImageView) findViewById(R.id.ic_linkedin);
        linkedin.setOnClickListener(this);
        twitter = (ImageView) findViewById(R.id.ic_twitter);
        twitter.setOnClickListener(this);
        explore_layout = (LinearLayout) findViewById(R.id.l_explore);
        explore_layout.setOnClickListener(this);
        share_layout = (LinearLayout) findViewById(R.id.l_share);
        share_layout.setOnClickListener(this);
        want_layout = (LinearLayout) findViewById(R.id.l_want);
        want_layout.setOnClickListener(this);
        cv_layout = (LinearLayout) findViewById(R.id.l_cv);
        cv_layout.setOnClickListener(this);

    }

    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.actionbar, menu);

        return super.onCreateOptionsMenu(menu);
    }

    public boolean contactExists(Context context, String number) {
/// number is the phone number
        Uri lookupUri = Uri.withAppendedPath(
                ContactsContract.PhoneLookup.CONTENT_FILTER_URI,
                Uri.encode(number));
        String[] mPhoneNumberProjection = { ContactsContract.PhoneLookup._ID, ContactsContract.PhoneLookup.NUMBER, ContactsContract.PhoneLookup.DISPLAY_NAME };
        Cursor cur = context.getContentResolver().query(lookupUri, mPhoneNumberProjection, null, null, null);
        try {
            if (cur.moveToFirst()) {
                return true;
            }
        } finally {
            if (cur != null)
                cur.close();
        }
        return false;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        switch (id){
            case R.id.action_call:
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager
                        .PERMISSION_GRANTED && checkSelfPermission(Manifest.permission
                        .READ_CONTACTS) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
                    requestPermissions(new String[]{Manifest.permission.CALL_PHONE},1);
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
                    requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},1);
                }
                else {
                    Boolean Shamli = contactExists(this, "+917696324435");
                    if(Shamli== true){
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + "+917696324435"));
                        startActivity(callIntent);
                    } else {
                        String DisplayName = "Vishal Singh";
                        String MobileNumber = "+917696324435";
                        String HomeNumber = "";
                        String WorkNumber = "";
                        String emailID = "kushwahz.vishalsingh@gmail.com";
                        String company = "Chandigarh Group of Colleges, Mohali, Punjab.";
                        String jobTitle = "Student";

                        ArrayList< ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();

                        ops.add(ContentProviderOperation.newInsert(
                                ContactsContract.RawContacts.CONTENT_URI)
                                .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                                .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                                .build());

                        //------------------------------------------------------ Names
                        if (DisplayName != null) {
                            ops.add(ContentProviderOperation.newInsert(
                                    ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                    .withValue(ContactsContract.Data.MIMETYPE,
                                            ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                    .withValue(
                                            ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                            DisplayName).build());
                        }

                        //------------------------------------------------------ Mobile Number
                        if (MobileNumber != null) {
                            ops.add(ContentProviderOperation.
                                    newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                    .withValue(ContactsContract.Data.MIMETYPE,
                                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                            ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                                    .build());
                        }

                        //------------------------------------------------------ Home Numbers
                        if (HomeNumber != null) {
                            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                    .withValue(ContactsContract.Data.MIMETYPE,
                                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                            ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                                    .build());
                        }

                        //------------------------------------------------------ Work Numbers
                        if (WorkNumber != null) {
                            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                    .withValue(ContactsContract.Data.MIMETYPE,
                                            ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
                                    .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                            ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                                    .build());
                        }

                        //------------------------------------------------------ Email
                        if (emailID != null) {
                            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                    .withValue(ContactsContract.Data.MIMETYPE,
                                            ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                                    .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                                    .build());
                        }

                        //------------------------------------------------------ Organization
                        if (!company.equals("") && !jobTitle.equals("")) {
                            ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                    .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                    .withValue(ContactsContract.Data.MIMETYPE,
                                            ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                                    .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
                                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                                    .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
                                    .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                                    .build());
                        }
                        // Asking the Contact provider to create a new contact
                        try {
                            getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                        } catch (Exception e) {
                            e.printStackTrace();
                            Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        //Call
                        Intent callIntent = new Intent(Intent.ACTION_CALL);
                        callIntent.setData(Uri.parse("tel:" + "+917696324435"));
                        startActivity(callIntent);
                    }
                }
                break;
            case  R.id.action_whatsapp:
                ActivityCompat.requestPermissions(MainActivity.this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        2);
                break;
            case  R.id.action_mail: Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: kushwahz.vishalsingh@gmail.com"));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.ic_gplus : startActivity(new Intent(getApplicationContext(),gplus.class));
               break;
            case R.id.ic_linkedin : startActivity(new Intent(getApplicationContext(), linkedin.class));
                break;
            case R.id.ic_twitter : startActivity(new Intent(getApplicationContext(), twitter.class));
                break;
            case R.id.l_cv : startActivity(new Intent(getApplicationContext(), my_cv.class));
                break;
            case R.id.l_explore : startActivity(new Intent(getApplicationContext(), explore.class));
                break;
            case R.id.l_share : Intent sendIntent = new Intent();
                sendIntent.setAction(Intent.ACTION_SEND);
                sendIntent.putExtra(Intent.EXTRA_TEXT, "Want an Android App for you? \n" +
                        "Download my (Vishal Singh) visiting app to explore my Android App projects. Follow this link https://drive.google.com/open?id=0ByA1LNYTorRQYkhnN29sLU1CckU");
                sendIntent.setType("text/plain");
                startActivity(sendIntent);
                break;
            case R.id.l_want : startActivity(new Intent(getApplicationContext(), want_app.class));
                break;

        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1 : if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M ){
                if(checkSelfPermission(Manifest.permission
                        .READ_CONTACTS) != PackageManager.PERMISSION_GRANTED ){
                    requestPermissions(new String[]{Manifest.permission.READ_CONTACTS},1);
                }
                else if(checkSelfPermission(Manifest.permission
                        .WRITE_CONTACTS) != PackageManager.PERMISSION_GRANTED){
                    requestPermissions(new String[]{Manifest.permission.WRITE_CONTACTS},1);
                }
            }

                else {
                Boolean Shamli = contactExists(this, "+917696324435");
                if (Shamli == true) {
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "+917696324435"));
                    startActivity(callIntent);
                } else {
                    String DisplayName = "Vishal Singh";
                    String MobileNumber = "+917696324435";
                    String HomeNumber = "";
                    String WorkNumber = "";
                    String emailID = "kushwahz.vishalsingh@gmail.com";
                    String company = "Chandigarh Group of Colleges, Mohali, Punjab.";
                    String jobTitle = "Student";

                    ArrayList<ContentProviderOperation> ops = new ArrayList<ContentProviderOperation>();

                    ops.add(ContentProviderOperation.newInsert(
                            ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                            .build());

                    //------------------------------------------------------ Names
                    if (DisplayName != null) {
                        ops.add(ContentProviderOperation.newInsert(
                                ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                .withValue(
                                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                        DisplayName).build());
                    }

                    //------------------------------------------------------ Mobile Number
                    if (MobileNumber != null) {
                        ops.add(ContentProviderOperation.
                                newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                                .build());
                    }

                    //------------------------------------------------------ Home Numbers
                    if (HomeNumber != null) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                        ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                                .build());
                    }

                    //------------------------------------------------------ Work Numbers
                    if (WorkNumber != null) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                                .build());
                    }

                    //------------------------------------------------------ Email
                    if (emailID != null) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                                .build());
                    }

                    //------------------------------------------------------ Organization
                    if (!company.equals("") && !jobTitle.equals("")) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
                                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
                                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                                .build());
                    }
                    // Asking the Contact provider to create a new contact
                    try {
                        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    //Call
                    Intent callIntent = new Intent(Intent.ACTION_CALL);
                    callIntent.setData(Uri.parse("tel:" + "+917696324435"));
                    startActivity(callIntent);
                }
            }
                break;
            case 2 : {
                Boolean vishal = contactExists(this, "+917696324435");

                if(vishal== true){
                    Uri uri = Uri.parse("smsto:" + "+917696324435");
                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                    i.putExtra("sms_body", "Hi Vishal");
                    i.setPackage("com.whatsapp");
                    startActivity(i);
                }
                else {
                    String DisplayName = "Vishal Singh";
                    String MobileNumber = "+917696324435";
                    String HomeNumber = "";
                    String WorkNumber = "";
                    String emailID = "kushwahz.vishalsingh@gmail.com";
                    String company = "Chandigarh Group of Colleges, Mohali, Punjab.";
                    String jobTitle = "Student";

                    ArrayList< ContentProviderOperation > ops = new ArrayList < ContentProviderOperation > ();

                    ops.add(ContentProviderOperation.newInsert(
                            ContactsContract.RawContacts.CONTENT_URI)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                            .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                            .build());

                    //------------------------------------------------------ Names
                    if (DisplayName != null) {
                        ops.add(ContentProviderOperation.newInsert(
                                ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                                .withValue(
                                        ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME,
                                        DisplayName).build());
                    }

                    //------------------------------------------------------ Mobile Number
                    if (MobileNumber != null) {
                        ops.add(ContentProviderOperation.
                                newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, MobileNumber)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                        ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                                .build());
                    }

                    //------------------------------------------------------ Home Numbers
                    if (HomeNumber != null) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, HomeNumber)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                        ContactsContract.CommonDataKinds.Phone.TYPE_HOME)
                                .build());
                    }

                    //------------------------------------------------------ Work Numbers
                    if (WorkNumber != null) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, WorkNumber)
                                .withValue(ContactsContract.CommonDataKinds.Phone.TYPE,
                                        ContactsContract.CommonDataKinds.Phone.TYPE_WORK)
                                .build());
                    }

                    //------------------------------------------------------ Email
                    if (emailID != null) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Email.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Email.DATA, emailID)
                                .withValue(ContactsContract.CommonDataKinds.Email.TYPE, ContactsContract.CommonDataKinds.Email.TYPE_WORK)
                                .build());
                    }

                    //------------------------------------------------------ Organization
                    if (!company.equals("") && !jobTitle.equals("")) {
                        ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                                .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, 0)
                                .withValue(ContactsContract.Data.MIMETYPE,
                                        ContactsContract.CommonDataKinds.Organization.CONTENT_ITEM_TYPE)
                                .withValue(ContactsContract.CommonDataKinds.Organization.COMPANY, company)
                                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                                .withValue(ContactsContract.CommonDataKinds.Organization.TITLE, jobTitle)
                                .withValue(ContactsContract.CommonDataKinds.Organization.TYPE, ContactsContract.CommonDataKinds.Organization.TYPE_WORK)
                                .build());
                    }

                    // Asking the Contact provider to create a new contact
                    try {
                        getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                    } catch (Exception e) {
                        e.printStackTrace();
                        Toast.makeText(this, "Exception: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                    //Send message to whatsapp
                    Uri uri = Uri.parse("smsto:" + "+917696324435");
                    Intent i = new Intent(Intent.ACTION_SENDTO, uri);
                    i.putExtra("sms_body", "Hi Vishal");
                    i.setPackage("com.whatsapp");
                    startActivity(i);
                }

                return;
            }

            // other 'case' lines to check for other
            // permissions this app might request
        }
    }

}
