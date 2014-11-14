package fragments.android.scottwaite.com.myapplication;
/*
Created By: Scott Waite
Course: Java II
Instructor: Sherry Dubin
Assignment: Multi-Activity App
Date: November 13, 2014
*/
import android.app.Activity;
import android.content.ContentProviderOperation;
import android.content.Intent;
import android.content.OperationApplicationException;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.RemoteException;
import android.provider.ContactsContract;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;
import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends Activity {
    private final int PICK_PHOTO = 1;

    Bitmap mBitmap;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        View.OnClickListener photoClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                startActivityForResult(intent, PICK_PHOTO);
            }
        };

        View.OnClickListener addClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                EditText etName = (EditText) findViewById(R.id.et_name);
                EditText etMobile = (EditText) findViewById(R.id.et_mobile);



                ArrayList<ContentProviderOperation> ops =
                        new ArrayList<ContentProviderOperation>();

                int rawContactID = ops.size();

                ops.add(ContentProviderOperation.newInsert(ContactsContract.RawContacts.CONTENT_URI)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_TYPE, null)
                        .withValue(ContactsContract.RawContacts.ACCOUNT_NAME, null)
                        .build());

                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.StructuredName.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.StructuredName.DISPLAY_NAME, etName.getText().toString())
                        .build());

                ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                        .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                        .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Phone.CONTENT_ITEM_TYPE)
                        .withValue(ContactsContract.CommonDataKinds.Phone.NUMBER, etMobile.getText().toString())
                        .withValue(ContactsContract.CommonDataKinds.Phone.TYPE, ContactsContract.CommonDataKinds.Phone.TYPE_MOBILE)
                        .build());


                ByteArrayOutputStream stream = new ByteArrayOutputStream();
                if(mBitmap!=null){	// If an image is selected successfully
                    mBitmap.compress(Bitmap.CompressFormat.PNG , 75, stream);

                    // Adding insert operation to operations list
                    // to insert Photo in the table ContactsContract.Data
                    ops.add(ContentProviderOperation.newInsert(ContactsContract.Data.CONTENT_URI)
                            .withValueBackReference(ContactsContract.Data.RAW_CONTACT_ID, rawContactID)
                            .withValue(ContactsContract.Data.IS_SUPER_PRIMARY, 1)
                            .withValue(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Photo.CONTENT_ITEM_TYPE)
                            .withValue(ContactsContract.CommonDataKinds.Photo.PHOTO,stream.toByteArray())
                            .build());

                    try {
                        stream.flush();
                    }catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                try{
                    // Executing all the insert operations as a single database transaction
                    getContentResolver().applyBatch(ContactsContract.AUTHORITY, ops);
                    Toast.makeText(getBaseContext(), "Contact is successfully added", Toast.LENGTH_SHORT).show();
                }catch (RemoteException e) {
                    e.printStackTrace();
                }catch (OperationApplicationException e) {
                    e.printStackTrace();
                }
            }
        };



        View.OnClickListener contactsClickListener = new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                Intent contacts = new Intent(Intent.ACTION_VIEW,ContactsContract.Contacts.CONTENT_URI);

                startActivity(contacts);
            }
        };


        ImageButton ibPhoto = (ImageButton) findViewById(R.id.ib_photo);

        Button btnAdd = (Button) findViewById(R.id.btn_add);

        Button btnList = (Button) findViewById(R.id.btn_list);

        ibPhoto.setOnClickListener(photoClickListener);

        btnAdd.setOnClickListener(addClickListener);

        btnList.setOnClickListener(contactsClickListener);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        switch(requestCode) {
            case PICK_PHOTO:
                if(resultCode == RESULT_OK){

                    Uri selectedImage = data.getData();

                    InputStream imageStream = null;
                    try {

                        imageStream = getContentResolver().openInputStream(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                    mBitmap = BitmapFactory.decodeStream(imageStream);

                    ImageButton ibPhoto = (ImageButton) findViewById(R.id.ib_photo);

                    ibPhoto.setImageBitmap(mBitmap);
                }
        }
    }


}
