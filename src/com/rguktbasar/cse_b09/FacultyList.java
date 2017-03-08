package com.rguktbasar.cse_b09;

import java.util.ArrayList;
import android.app.Activity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TableLayout;

public class FacultyList extends Activity{

	public TableLayout table;
	public Button button;
	public ArrayList<String> data = new ArrayList<String>();
	public FacultyListAdapter facultyListAdapter = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.faculty);
		data.add("Chandra Shekar#9441347578#chandrashekar512@gmail.com");
		data.add("Anjaneyulu#9573352355#anjaneyuluendurthi@gmail.com");
		data.add("Jabeen Sultana#7702415695#jabeens02@gmail.com");
		data.add("Priyanka Meel#9492640723#priyankameel86@gmail.com");
		data.add("Ranjith Kumar#90000919819#ranjithgarnepudi@gmail.com");
		data.add("Reenu Rajput#9703423913#rajputreenu@gmail.com");
		data.add("Revya Naik#8332040448#revyav@gmail.com");
		data.add("Samith Kumar Pradhan#8500862608#samitkumarcs@gmail.com");
		data.add("Seema Simoliya#8790945329#seema.simoliya@rgukt.in");
		data.add("Shekar Hemanth#9533078570#shekhar1436@gmail.com");
		data.add("Shobit Kumar#7730868684#kumar.shobhit05@gmail.com");
		data.add("Sujoy sarkar#9160352021#sujoy.sarkar88@gmail.com");
		data.add("Surrender Mogilicharla#9666920194#surender.mogilicharla@gmail.com");
		data.add("Venkat Raman#9391386029#venkat521@yahoo.co.in");
		
		
		ListView list = (ListView)findViewById(R.id.listView1);
		facultyListAdapter = new FacultyListAdapter(this, data);
		list.setAdapter(facultyListAdapter);
		}

}
