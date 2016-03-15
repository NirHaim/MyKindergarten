package com.example.mykindergarten;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Nir_2 on 09/03/2016.
 */
public class AddChild extends AppCompatActivity{

    private final String LOG_TAG = "AddChild";

    EditText etFName;
    EditText etLName;
    EditText etContact1;
    EditText etPhone1;
    EditText etContact2;
    EditText etPhone2;

    final Context context = this;

    private ChildrenDB children;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_child);

        Log.d(LOG_TAG, "In onCreate()");

//        TextView txt = (TextView) findViewById(R.id.tvHeader);
//        Typeface font = Typeface.createFromAsset(getAssets(), "fonts/alex.ttf");
//        txt.setTypeface(font);

        children=new ChildrenDB(this);

        etFName = (EditText) findViewById(R.id.etFName);
        etLName = (EditText) findViewById(R.id.etLName);
        etContact1 = (EditText) findViewById(R.id.etContact1);
        etPhone1 = (EditText) findViewById(R.id.etPhone1);
        etContact2 = (EditText) findViewById(R.id.etContact2);
        etPhone2 = (EditText) findViewById(R.id.etPhone2);

        Button OK = (Button) findViewById(R.id.bOk);
        OK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveChildrenData();

                AlertDialog.Builder adBuilder = new AlertDialog.Builder(
                        context);

                // set title
                adBuilder.setTitle("הוספת ילד נוסף");

                // set dialog message
                adBuilder.setMessage("האם ברצונך להוסיף כעת ילד נוסף?")
                        .setCancelable(false)
                        .setPositiveButton("כן", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, clear the data of the child
                                // and then dismiss the dialog
                                etFName.setText("");
                                etLName.setText("");
                                etContact1.setText("");
                                etPhone1.setText("");
                                etContact2.setText("");
                                etPhone2.setText("");
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("לא", new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                // if this button is clicked, return to MainActivity
                                Intent i = new Intent(context, MainActivity.class);
                                startActivity(i);
                            }
                        });
                // create alert dialog
                AlertDialog alertDialog = adBuilder.create();

                // show it
                alertDialog.show();
            }
        });
    }

    private void saveChildrenData()
    {
        String fName= etFName.getText().toString();
        String lName= etLName.getText().toString();
        String contact1= etContact1.getText().toString();
        String phone1= etPhone1.getText().toString();
        String contact2= etContact2.getText().toString();
        String phone2= etPhone2.getText().toString();

        addChildToDB(fName, lName, contact1, phone1, contact2, phone2);

//        Cursor cursor = getData();
//        showData(cursor);
    }

    private void addChildToDB(String fName, String lName, String contact1, String phone1, String contact2, String phone2)
    {

        SQLiteDatabase db= children.getWritableDatabase();

        String sql = "SELECT * FROM CHILDREN WHERE FIRSTNAME = '" + fName + "'" + "AND LASTNAME = '" + lName + "'";
        Cursor c = db.rawQuery(sql, null);
        int results = c.getCount();
        if(results == 0)
        {
            //If the child doesn't exists therefore insert record
            SQLiteDatabase dbw= children.getWritableDatabase();
            ContentValues values= new ContentValues();
            values.put("FIRSTNAME", fName);
            values.put("LASTNAME", lName);
            values.put("CONTACT1", contact1);
            values.put("PHONE1", phone1);
            values.put("CONTACT2", contact2);
            values.put("PHONE2", phone2);
            dbw.insertOrThrow("CHILDREN", null, values);

//			//Id++;
//			int oldVersion= dbw.getVersion();
//			Toast.makeText(getBaseContext(), "The oldVersion is: "+ oldVersion,
//					Toast.LENGTH_SHORT).show();
//			 db.beginTransaction();
//						dbw.setVersion(oldVersion+1);
//						db.setTransactionSuccessful();
//						 db.endTransaction();
//			children.onUpgrade(dbw, oldVersion, oldVersion+1);
//
//
//			numOfChildren++;
//
//			prefs = getSharedPreferences("myPref1", MODE_WORLD_WRITEABLE);
//			editor = prefs.edit();
//
//			editor.putInt("id",Id);
//			editor.commit();

            Toast.makeText(getBaseContext(), fName + " " + lName + " התווסף",
                    Toast.LENGTH_LONG).show();
        }
        else
        {
            Toast.makeText(getBaseContext(), "ילד זה כבר נמצא במערכת",
                    Toast.LENGTH_LONG).show();
        }
    }

}



