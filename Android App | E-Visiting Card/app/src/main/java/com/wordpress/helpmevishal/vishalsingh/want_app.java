package com.wordpress.helpmevishal.vishalsingh;

import android.content.ContentProviderOperation;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class want_app extends AppCompatActivity implements View.OnClickListener {
    ImageView call, whatsapp, mail;
    LinearLayout hmva, hmvw, cry, par, visit;
    TextView tv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_want_app);
        call = (ImageView) findViewById(R.id.image_call);
        call.setOnClickListener(this);
        whatsapp = (ImageView) findViewById(R.id.image_whatsapp);
        whatsapp.setOnClickListener(this);
        mail =(ImageView) findViewById(R.id.image_mail);
        mail.setOnClickListener(this);
        hmva = (LinearLayout) findViewById(R.id.l_hmva);
        hmva.setOnClickListener(this);
        hmvw = (LinearLayout) findViewById(R.id.l_hmvw);
        hmvw.setOnClickListener(this);
        cry = (LinearLayout) findViewById(R.id.l_cry);
        cry.setOnClickListener(this);
        par = (LinearLayout) findViewById(R.id.l_par);
        par.setOnClickListener(this);
        visit = (LinearLayout) findViewById(R.id.l_visit);
        visit.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.mor2);
        tv.setOnClickListener(this);
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.mor2: startActivity(new Intent(getApplicationContext(), explore.class));
                break;
            case R.id.l_hmva: startActivity(new Intent(getApplicationContext(), explore.class));
                break;
            case R.id.l_hmvw: startActivity(new Intent(getApplicationContext(), hmv_website.class));
                break;
            case R.id.l_cry: startActivity(new Intent(getApplicationContext(), cryptography.class));
                break;
            case R.id.l_par: startActivity(new Intent(getApplicationContext(), Parivartan2K14.class));
                break;
            case R.id.l_visit: startActivity(new Intent(getApplicationContext(), visiting_app.class));
                break;
            case R.id.image_call:
                ActivityCompat.requestPermissions(want_app.this,
                        new String[]{android.Manifest.permission.READ_CONTACTS},
                        1);
                break;
            case R.id.image_whatsapp: ActivityCompat.requestPermissions(want_app.this,
                    new String[]{android.Manifest.permission.READ_CONTACTS},
                    2);
                break;
            case R.id.image_mail: Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
                emailIntent.setData(Uri.parse("mailto: kushwahz.vishalsingh@gmail.com"));
                startActivity(Intent.createChooser(emailIntent, "Send feedback"));
                break;

        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1 : Boolean Shamli = contactExists(this, "+917696324435");
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
