package com.example.mykindergarten;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

//This class create the DB table
public class ChildrenDB extends SQLiteOpenHelper
{
	public static int count=1;
	private final Context myContext;
	private static final String TAG = "MyActivity";
	private static final int DATABASE_VERSION = 1;
    
	
    public ChildrenDB(Context context)
	{
		super(context, "ChildrenDB", null, DATABASE_VERSION);
		 this.myContext = context;
	}

    
	@Override
	public void onCreate(SQLiteDatabase db)
	{
		db.execSQL("CREATE TABLE " + "PRESENCE" + "(" + "_INDEX" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "DATE" + " TEXT, "
				+ "ID" + " INTEGER);" );
		
		db.execSQL("CREATE TABLE " + "CHILDREN" + "(" + "_ID" + " INTEGER PRIMARY KEY AUTOINCREMENT, "
				+ "FIRSTNAME" + " TEXT, " + "LASTNAME" + " TEXT, "
				+ "CONTACT1" + " TEXT, " + "PHONE1" + " TEXT, "
				+ "CONTACT2" + " TEXT, " + "PHONE2" + " TEXT);" );
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//		count++;
//		String upgradeQuery = "ALTER TABLE PRESENCE ADD COLUMN CHILD" + count + " INTEGER";
//
////		Toast.makeText(myContext, "oldVersion= "+oldVersion,
////				Toast.LENGTH_SHORT).show();
//		Log.d(TAG, "onupgrade from ver " + oldVersion + "to ver " + newVersion);
//		//if (oldVersion == 1 && newVersion == 2)
//		if (newVersion - oldVersion == 1) {
//			db.setVersion(newVersion);
//
//			//db.execSQL(upgradeQuery);
//
//		}
	}
}