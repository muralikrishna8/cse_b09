package com.rguktbasar.cse_b09;

import java.util.List;
import android.content.Context;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

public class studentListAdapter extends ArrayAdapter<String>{
	List<String> list;
	private String keyword = null;
	
    public studentListAdapter(Context _context, List<String> _mylist, String keyword) {
        super(_context, R.layout.grid_item, _mylist); 
        this.list = _mylist;
        this.keyword = keyword;
    }

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		String data = getItem(position);
		String result = data.replaceAll(keyword, "<i><font color='#3498DB'>"+keyword+"</font></i>");
		
		View v;
		if(convertView == null){
			convertView = new LinearLayout(getContext());
			String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(inflater);
            v = vi.inflate(R.layout.grid_item, parent, false);
		}
		else{
			v = (View)convertView;
		}
		
		ImageView img = (ImageView)v.findViewById(R.id.image);
        img.setImageResource(Result.images.get(position));
        TextView text = (TextView)v.findViewById(R.id.data);
        text.setText(Html.fromHtml(result));
		
        return v;
	}
    
    
}
