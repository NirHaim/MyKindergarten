package com.example.mykindergarten;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class DeleteChild extends AppCompatActivity implements OnClickListener
{
	private ListView lvChildsPresence;
	private ChildrenDB children;
	private List<Data> dataList;
	DataAdapter chAdapter;
//	private int numOfChilds;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.delete);
		lvChildsPresence = (ListView)findViewById(R.id.lvChilds);
		children=new ChildrenDB(this);

		readChildsFromDB();
	}



	private void readChildsFromDB ()
	{
		dataList = new ArrayList<Data>();

		SQLiteDatabase db= children.getReadableDatabase();
		String query = "SELECT * FROM CHILDREN";
		Cursor c = db.rawQuery(query, null);
//		numOfChilds = c.getCount();

		Cursor q= db.rawQuery("SELECT * FROM CHILDREN", null);
		while(q.moveToNext())
		{
			int Id=q.getInt(0);
			String fName= q.getString(1);
			String lName= q.getString(2);
			dataList.add(new Data(fName+" "+lName));
		}

		chAdapter = new DataAdapter(dataList,R.layout.list_delete ,this);
		lvChildsPresence.setAdapter(chAdapter);

	}
	
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		
	}

}
