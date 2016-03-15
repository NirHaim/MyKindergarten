package com.example.mykindergarten;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;

class Data {

	String name;
	boolean selected = false;

	public Data(String name) {
		super();
		this.name= name;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}

public class DataAdapter extends ArrayAdapter<Data> {

	private List<Data> dataList;
	private Context context;
	private int layout;

	public DataAdapter(List<Data> dataList,int layoutInput,Context context) {
		//super(context, R.layout.list, dataList);
		super(context,layoutInput, dataList);
		this.dataList = dataList;
		this.layout=layoutInput;
		this.context = context;
	}

	private static class DataHolder {
		public TextView childName;
		public CheckBox chkBox;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		View v = convertView;

		DataHolder holder = new DataHolder();

		if(convertView == null) {

			LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			//v = inflater.inflate(R.layout.list, null);
			v = inflater.inflate(layout, null);
			holder.childName = (TextView) v.findViewById(R.id.tvName);
			holder.chkBox = (CheckBox) v.findViewById(R.id.cbPresence);

			holder.chkBox.setOnCheckedChangeListener((Presence_new) context);

		} else {
			holder = (DataHolder) v.getTag();
		}

		Data p = dataList.get(position);
		holder.childName.setText(p.getName());
		holder.chkBox.setChecked(p.isSelected());
		holder.chkBox.setTag(p);

		return v;
	}
}