package com.rguktbasar.cse_b09;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FacultyListAdapter extends ArrayAdapter<String>{

	public FacultyListAdapter(Context context, List<String> objects) {
		super(context, R.layout.faculty_list_view, objects);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String data = getItem(position);
		String arr[] = data.split("#");
		
		
		View v;
		if(convertView == null){
			convertView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            v = vi.inflate(R.layout.faculty_list_view, parent, false);
		}
		else{
			v = (View)convertView;
		}
		
		ImageView img = (ImageView)v.findViewById(R.id.image);
        img.setImageResource(Result.facultyImages[position]);
        TextView name = (TextView)v.findViewById(R.id.name);
        TextView mobile = (TextView)v.findViewById(R.id.mobile);
        TextView email = (TextView)v.findViewById(R.id.email);
        
        name.setText(arr[0]);
        mobile.setText(arr[1]);
        email.setText(arr[2]);
        return v;
	}

}
