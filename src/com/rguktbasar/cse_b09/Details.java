package com.rguktbasar.cse_b09;

import java.util.StringTokenizer;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Details extends Activity {
	private TextView name;
	private TextView sirname;
	private TextView fathername;
	private TextView mothername;
	private TextView dob;
	private TextView cell;
	private TextView email;
	private TextView hno;
	private TextView village;
	private TextView mandal;
	private TextView district;
	private TextView schoolname;
	private TextView sschallticket;
	private TextView caste;
	private ImageView image;
	private ImageView callIcon;
	private ImageView smsIcon;
	private ImageView mailIcon;
	private int index;

	private String cellno;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.details2);
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);

		index = getIntent().getExtras().getInt("index");

		name = (TextView) findViewById(R.id.name);
		sirname = (TextView) findViewById(R.id.sirname);
		fathername = (TextView) findViewById(R.id.fathername);
		mothername = (TextView) findViewById(R.id.mothername);
		dob = (TextView) findViewById(R.id.dob);
		cell = (TextView) findViewById(R.id.cell);
		email = (TextView) findViewById(R.id.email);
		hno = (TextView) findViewById(R.id.hno);
		village = (TextView) findViewById(R.id.village);
		mandal = (TextView) findViewById(R.id.mandal);
		district = (TextView) findViewById(R.id.district);
		// pincode = (TextView)findViewById(R.id.pincode);
		schoolname = (TextView) findViewById(R.id.schoolname);
		sschallticket = (TextView) findViewById(R.id.sschallticket);
		caste = (TextView) findViewById(R.id.caste);
		image = (ImageView) findViewById(R.id.studentimage);

		callIcon = (ImageView) findViewById(R.id.call_icon);
		callIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				makeCall();
			}
		});

		smsIcon = (ImageView) findViewById(R.id.sms_icon);
		smsIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sendSMS();
			}
		});

		mailIcon = (ImageView) findViewById(R.id.mail_icon);
		mailIcon.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				sendMail();
			}
		});

		displayDetails(Result.allResult.get(index));
	}

	private void makeCall() {
		Intent phoneIntent = new Intent(Intent.ACTION_CALL);
		Toast.makeText(getApplicationContext(), "Calling to: " + cellno,
				Toast.LENGTH_LONG).show();

		phoneIntent.setData(Uri.parse("tel:" + cellno));
		try {
			startActivity(phoneIntent);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(getApplicationContext(),
					"Call faild, please try again later.", Toast.LENGTH_SHORT)
					.show();
		}
	}

	private void sendSMS() {
		Toast.makeText(getApplicationContext(), "Sending SMS to: " + cellno,
				Toast.LENGTH_LONG).show();
		
		Intent smsIntent = new Intent(Intent.ACTION_VIEW);
		smsIntent.setData(Uri.parse("smsto:"));
		smsIntent.setType("vnd.android-dir/mms-sms");

		smsIntent.putExtra("address", cellno);
		smsIntent.putExtra("sms_body", "SMS ...");
		try {
			startActivity(smsIntent);
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(getApplicationContext(),
					"SMS faild, please try again later.", Toast.LENGTH_SHORT).show();
		}
	}

	private void sendMail() {
		Log.i("Send email", "");

		Toast.makeText(getApplicationContext(),
				"Email to: " + email.getText().toString(), Toast.LENGTH_LONG)
				.show();

		String[] TO = { email.getText().toString() };
		Intent emailIntent = new Intent(Intent.ACTION_SEND);
		emailIntent.setData(Uri.parse("mailto:"));
		emailIntent.setType("text/plain");

		emailIntent.putExtra(Intent.EXTRA_EMAIL, TO);
		emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject ...");
		emailIntent.putExtra(Intent.EXTRA_TEXT, "Email message ...");

		try {
			startActivity(Intent.createChooser(emailIntent, "Send mail..."));
		} catch (android.content.ActivityNotFoundException ex) {
			Toast.makeText(getApplicationContext(),
					"There is no email client installed.", Toast.LENGTH_LONG)
					.show();
		}
	}

	private void displayDetails(String data) {
		image.setImageResource(Result.images.get(index));
		StringTokenizer st = new StringTokenizer(data, "$");
		String id = st.nextToken();
		String n = st.nextToken();
		setTitle(id + "- " + n.toLowerCase());
		name.setText(n);
		sirname.setText(st.nextToken());
		fathername.setText(st.nextToken());
		mothername.setText(st.nextToken());
		cell.setText("Cell: " + st.nextToken());
		email.setText(st.nextToken());
		dob.setText("DOB: " + st.nextToken());
		caste.setText("Caste: " + st.nextToken() + " - " + st.nextToken());
		hno.setText("H-No: " + st.nextToken());
		village.setText("Village: " + st.nextToken());
		mandal.setText("Mandal: " + st.nextToken());
		district.setText(st.nextToken() + " - " + st.nextToken());
		sschallticket.setText("SSC HallTicket: " + st.nextToken());
		schoolname.setText("School: " + st.nextToken());
		
		cellno = cell.getText().toString();
		cellno = cellno.substring(6);
	}
}
