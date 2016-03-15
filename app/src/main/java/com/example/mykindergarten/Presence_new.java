package com.example.mykindergarten;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by Nir_2 on 10/03/2016.
 */
public class Presence_new extends AppCompatActivity implements android.widget.CompoundButton.OnCheckedChangeListener, View.OnClickListener
{

    private List<Data> dataList;
    DataAdapter chAdapter;
    private ChildrenDB children;
    private int numOfChilds;
    private int year, month, day;
    private ListView lvChildsPresence;
    private TextView tvDate;
    private TextView tvPresence, tvAbsent;
    public int presence=0;
    private static String currentDate;
    private Button bSave;
    int[] anArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.presence);

        children=new ChildrenDB(this);

        lvChildsPresence = (ListView)findViewById(R.id.lvChilds);
        bSave = (Button) findViewById(R.id.bSave);
        bSave.setOnClickListener(this);
        tvDate=(TextView) findViewById(R.id.tvDate);
        tvPresence=(TextView) findViewById(R.id.tvPresence);
        tvAbsent=(TextView) findViewById(R.id.tvAbsent);
        readChildsFromDB();
        setCurrentDate();

    }

    private void readChildsFromDB ()
    {
        dataList = new ArrayList<Data>();

        SQLiteDatabase db= children.getReadableDatabase();
        String query = "SELECT * FROM CHILDREN";
        Cursor c = db.rawQuery(query, null);
        numOfChilds = c.getCount();

        anArray=new int[numOfChilds];

        Cursor q= db.rawQuery("SELECT * FROM CHILDREN", null);
        while(q.moveToNext())
        {
            int Id=q.getInt(0);
            String fName= q.getString(1);
            String lName= q.getString(2);
            dataList.add(new Data(fName+" "+lName));
        }

        chAdapter = new DataAdapter(dataList,R.layout.list ,this);
        lvChildsPresence.setAdapter(chAdapter);

    }

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        int pos = lvChildsPresence.getPositionForView(buttonView);
        if (pos != ListView.INVALID_POSITION) {
            Data p = dataList.get(pos);
            p.setSelected(isChecked);
            Toast.makeText(
                    this,
                    "position: " + pos, Toast.LENGTH_SHORT).show();

            //save  checkbox
            if (isChecked==true)
            {
                anArray[pos]= 1;
                presence++;
            }
            else
            {
                presence--;
                anArray[pos]= 0;
            }
            tvPresence.setText(String.valueOf(presence));
            tvAbsent.setText(String.valueOf(numOfChilds -presence));
//            Toast.makeText(
//                    this,
//                    "Clicked on Planet: " + p.getName() + ". State: is "
//                            + isChecked, Toast.LENGTH_SHORT).show();
        }
    }

    public void setCurrentDate()
    {
        final Calendar calendar = Calendar.getInstance();

        year = calendar.get(Calendar.YEAR);
        month = calendar.get(Calendar.MONTH);
        day = calendar.get(Calendar.DAY_OF_MONTH);

        // set current date into edit text
        tvDate.setText(new StringBuilder()
                // Month is 0 based, so you have to add 1
                .append(day).append("-")
                .append(month + 1).append("-")
                .append(year).append(" "));
        currentDate=tvDate.getText().toString();
    }

    @Override
    public void onClick(View view)
    {
        saveToDB();
    }

    private void saveToDB() {

        SQLiteDatabase db = children.getWritableDatabase();

        String sql = "SELECT * FROM PRESENCE WHERE _INDEX = '" + currentDate + "'";
        Cursor c = db.rawQuery(sql, null);
        int results = c.getCount();
        if (results == 0) {

            //If the child doesn't exists therefore insert record
            SQLiteDatabase dbw = children.getWritableDatabase();
            ContentValues values = new ContentValues();

            for (int i = 0; i < anArray.length; i++) {
                //Toast.makeText(this, "value is " + anArray[i], Toast.LENGTH_SHORT).show();
                values.put("DATE", currentDate);
                if (anArray[i]==1)
                {
                    values.put("ID", anArray[i]);
                    continue;
                }
                dbw.insertOrThrow("PRESENCE", null, values);
                Toast.makeText(this, "savedb", Toast.LENGTH_SHORT).show();
            }


        }
    }
}
