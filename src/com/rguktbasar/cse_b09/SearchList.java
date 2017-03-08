package com.rguktbasar.cse_b09;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class SearchList extends Activity{

	final Context mHelperContext = this;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.searchlist);
		showDetails();
		Result.result.removeAll(Result.result);
		Result.allResult.removeAll(Result.allResult);
	}

	public void showDetails(){
		Thread thread = new Thread(){
			public void run(){
				String line=null;
				final Resources resources = mHelperContext.getResources();
				InputStream inputStream = resources.openRawResource(R.raw.cse_2k9);
		        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
		        ArrayList<String> arr = new ArrayList<String>();
				Bundle gotbasket = getIntent().getExtras();
				String id = gotbasket.getString("keyword");
					try {
						while ((line = br.readLine()) != null) {
							{
								if (line.contains(id)){
									arr.add(line+"\n");
									Result.allResult.add(line);
								}
							}
						}
					} catch (IOException e1) {
						e1.printStackTrace();
					}
					try {
						br.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				if(arr.isEmpty()){
					runOnUiThread(new Runnable() {
			            @Override
			            public void run() {
			            	Toast.makeText(getApplicationContext(), "Details NOT FOUND", Toast.LENGTH_SHORT).show();
							finish();			            }
			        });
				}
				while(!arr.isEmpty()){
					StringTokenizer st = new StringTokenizer(arr.get(0), "$");
					String s="";
					String detail = null;
					String studId = st.nextToken();
					String imageUrl = "@drawable/".concat(studId.toLowerCase()).toString();
					int res = getResources().getIdentifier(imageUrl, null, mHelperContext.getPackageName());
					if(res == 0)
						res = R.drawable.minion;
					Result.images.add(res);
										
					s = "<br>"+studId+"<br>"+st.nextToken();
					while (st.hasMoreTokens()) {
						detail = st.nextToken();
						if(detail.contains(id))
							s+="<br>"+detail;
					}
					Result.result.add(s);
					arr.remove(0);
				}
				Intent person = new Intent();
				setResult(RESULT_OK,person);
				finish();
			}
		};
		thread.start();
	}
}
