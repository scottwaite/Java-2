package com.example.android.fragments;

/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Multi-Activity App
Date: November 13, 2014
*/

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.contactsContract;
import android.provider.contactsContract.CommonDataKinds;
import android.provider.contactsContract.CommonDataKinds.Phone;
import android.provider.contactsContract.CommonDataKinds.Photo;
import android.provider.contactsContract.CommonDataKinds.StructuredName;
import android.provider.contactsContract.Rawcontacts;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.scottwaite.android.fragments.R;

public class ShowtimesActivity extends Activity
{
    private final int PICK_PHOTO = 1;

    Bitmap mBitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Defining OnClick listener for the photo
        OnClickListener photoClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_PHOTO);
            }
        };

        // Defining OnClick listener for the Add contact Button
        OnClickListener addClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {

                // Getting reference to Name EditText
                EditText etName = (EditText) findViewById(R.id.et_name);

                // Getting reference to Mobile EditText
                EditText etMobile = (EditText) findViewById(R.id.et_mobile);



                ArrayList<ContentProviderOperation> ops =
                        new ArrayList<ContentProviderOperation>();

                int rawcontactID = ops.size();

                // Adding insert operation to operations list
                // to insert a new raw contact in the table contactsContract.Rawcontacts
                ops.add(ContentProviderOperation.newInsert(contactsContract.Rawcontacts.CONTENT_URI)
                        .withValue(contactsContract.Rawcontacts.ACCOUNT_TYPE, null)
                        .withValue(Rawcontacts.ACCOUNT_NAME, null)
                        .build());

                // Adding insert operation to operations list
                // to insert display name in the table contactsContract.Data
                ops.add(ContentProviderOperation.newInsert(contactsContract.Data.CONTENT_URI)
                        .withValueBackReference(contactsContract.Data.RAW_CONTACT_ID, rawcontactID)
                        .withValue(contactsContract.Data.MIMETYPE, StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(StructuredName.DISPLAY_NAME, etName.getText().toString())
                        .build());

                // Adding insert operation to operations list
                // to insert Mobile Number in the table contactsContract.Data
                ops.add(ContentProviderOperation.newInsert(contactsContract.Data.CONTENT_URI)
                        .withValueBackReference(contactsContract.Data.RAW_CONTACT_ID, rawcontactID)
                        .withValue(contactsContract.Data.MIMETYPE, Phone.CONTENT_ITEM_TYPE)
                        .withValue(Phone.NUMBER, etMobile.getText().toString())
                        .withValue(Phone.TYPE, CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                if(mBitmap!=null){	// If an image is selected successfully
                    mBitmap.compress(Bitmap.CompressFormat.PNG , 75, stream);

                    // Adding insert operation to operations list
                    // to insert Photo in the table contactsContract.Data
                    ops.add(ContentProviderOperation.newInsert(contactsContract.Data.CONTENT_URI)
                            .withValueBackReference(contactsContract.Data.RAW_CONTACT_ID, rawcontactID)
                            .withValue(contactsContract.Data.IS_SUPER_PRIMARY, 1)
                            .withValue(contactsContract.Data.MIMETYPE,Photo.CONTENT_ITEM_TYPE)
                            .withValue(contactsContract.CommonDataKinds.Photo.PHOTO,stream.toByteArray())
                            .build());

                    try {
                        stream.flush();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try{
                    // Executing all the insert operations as a single database transaction
                    getContentResolver().applyBatch(contactsContract.AUTHORITY, ops);
                    Toast.makeText(getBaseContext(), "contact is successfully added", Toast.LENGTH_SHORT).show();
                }catch (RemoteException e) {
                    e.printStackTrace();
                }catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
            }
        };


        // Creating a button click listener for the "Add contact" button
        OnClickListener contactsClickListener = new OnClickListener() {

            @Override
            public void onClick(View v) {
                // Creating an intent to open Android's contacts List
                Intent contacts = new Intent(Intent.ACTION_VIEW,contactsContract.contacts.CONTENT_URI);

                // Starting the activity
                startActivity(contacts);
            }
        };


        // Getting reference to ImageView
        ImageButton ibPhoto = (ImageButton) findViewById(R.id.ib_photo);

        // Getting Reference to Add contact Button
        Button btnAdd = (Button) findViewById(R.id.btn_add);

        // Getting Reference to contact List Button
        Button btnList = (Button) findViewById(R.id.btn_list);

        // Setting OnClick Listener for the photo
        ibPhoto.setOnClickListener(photoClickListener);


        // Setting OnClick Listener of the Add contact button
        btnAdd.setOnClickListener(addClickListener);

        // Setting OnClick Listener for the contacts List button
        btnList.setOnClickListener(contactsClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case PICK_PHOTO:
                if(resultCode == RESULT_OK){
                    // Getting the uri of the picked photo
                    Uri selectedImage = data.getData();

                    InputStream imageStream = null;
                    try {
                        // Getting InputStream of the selected image
                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    // Creating bitmap of the selected image from its inputstream
                    mBitmap = BitmapFactory.decodeStream(imageStream);

                    // Getting reference to ImageView
                    ImageButton ibPhoto = (ImageButton) findViewById(R.id.ib_photo);

                    // Setting Bitmap to ImageButton
                    ibPhoto.setImageBitmap(mBitmap);
                }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.layout.activity_main, menu);
        return true;
    }
}
