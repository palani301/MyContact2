package com.example.palani.mycontact;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private  final int PICK_CONTACT=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void callcontacts(View view){
        Intent i=new Intent(Intent.ACTION_PICK, ContactsContract.Contacts.CONTENT_URI);
        startActivityForResult(i,PICK_CONTACT);
    }
    @Override
    protected void onActivityResult(int reqCode,int resultCode,Intent data){
        super.onActivityResult(reqCode,resultCode,data);
        if (reqCode==PICK_CONTACT){
            if (resultCode== ActionBarActivity.RESULT_OK){
                Uri contactData=data.getData();
                Cursor c=getContentResolver().query(contactData,null,null,null,null);
                if (c.moveToFirst()){
                    String name=c.getString(c.getColumnIndexOrThrow(ContactsContract.Contacts.DISPLAY_NAME));
                    Toast.makeText(getApplicationContext(),"You've picked"+name,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }
}
