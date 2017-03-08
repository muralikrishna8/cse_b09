package com.rguktbasar.cse_b09;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class Credits extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.credits);
		
		TextView fb_link = (TextView)findViewById(R.id.facebook_link);
		fb_link.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				openLink("http://www.facebook.com/vmurali1444");
			}
		});
		TextView git_link = (TextView)findViewById(R.id.github_link);
		git_link.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				openLink("https://github.com/muralikrishna8");
			}
		});
		LinearLayout murali = (LinearLayout)findViewById(R.id.murali);
		murali.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showDetails(R.drawable.b091444, "B091444$MURALI KRISHNA$VEMULA$RAMALINGAIAH$RAJESHWARI$9912721636$vmurali1444@gmail.com$1994-08-26$BC-B$PADMASHALI$03/59$SULTHANABAD$SULTANABAD$KARIMNAGAR$505185$34117435$SRI VANI VIDYANIKETAN SULTHANABAD$");
			}
		});
		
		TableRow swamy = (TableRow)findViewById(R.id.swamy);
		swamy.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showDetails(R.drawable.b091578, "B091578$SWAMY$CHINTA KINDI$YELLAIAH$MALLAMMA$9553836518$swamy.chinthakindi@gmail.com$1993-02-03$BC-D$YADAV$-$VELOORU$WARGAL$MEDAK$502336$28141245$Z P H S VELOOR$");
			}
		});
		TableRow vamshi = (TableRow)findViewById(R.id.vamshi);
		vamshi.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				showDetails(R.drawable.b091415, "B091415$VAMSHI KRISHNA$GANJI $GANJI SHIVA$SHOBHA$9492869300$vamshikrishganji123@gmail.com$1993-09-03$BC-B$PADMASHALI$PLOT NO:15, ADARSHA COLONOY$RAVINDRA NAGAR,$NALGONDA$NALGONDA$508001$26100783$GOWTHAM MODEL S KESHARAJUPALLY (PO)$");
			}
		});
	}
	private void showDetails(int image, String data){
		Result.allResult.add(0, data);
		Result.images.add(0,image);
		Intent details = new Intent(Credits.this, Details.class);
		details.putExtra("index", 0);
		startActivity(details);
	}
	private void openLink(String url){
		Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
		startActivity(browserIntent);
	}

}