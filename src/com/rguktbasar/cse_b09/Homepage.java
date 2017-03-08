package com.rguktbasar.cse_b09;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

@SuppressLint("DefaultLocale")
public class Homepage extends Activity implements AbsListView.OnScrollListener{

	private SearchView mSearchView;
	private static int c = 0;
	private String keyword = null;
	private GridView grid = null;
	private studentListAdapter studentList = null;
	private LinearLayout homeLinear = null;
	private LinearLayout introLayout = null;
	private static final int timeInterval = 2000;
	private long backPressed;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		
		grid = (GridView)findViewById(R.id.grid_students);
		homeLinear = (LinearLayout)findViewById(R.id.home_linearlayout);
		introLayout = (LinearLayout)findViewById(R.id.intro);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.homepage, menu);
		
	    MenuItem searchItem = menu.findItem(R.id.action_search);
        mSearchView = (SearchView) searchItem.getActionView();
		//TODO check this is mobile
		mSearchView.setFocusable(false);
        mSearchView.setOnQueryTextListener(new OnQueryTextListener() {
			
			@Override
			public boolean onQueryTextSubmit(String query) {
				if(c%2 != 0){
					keyword = query.toUpperCase();
					//TODO check this is mobile
					mSearchView.setFocusable(false);
					showDetails();
				}else{c++;}
				return false;
			}
			
			@Override
			public boolean onQueryTextChange(String arg0) {
				return false;
			}
		});
        
        return true;
	}
    
    public void showDetails(){
    	Result.result.removeAll(Result.result);
    	Result.allResult.removeAll(Result.allResult);
    	Result.images.removeAll(Result.images);

    	Intent search = new Intent(this, SearchList.class);
    	Bundle data = new Bundle();
    	data.putString("keyword", keyword);
    	search.putExtras(data);
        startActivityForResult(search, 0);
    }

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			mSearchView.setFocusable(false);
			mSearchView.clearFocus();
			buildTheGrid();
			int count = Result.result.size();
			if(count>0)
				Toast.makeText(getApplicationContext(),count+" Results found.", Toast.LENGTH_LONG).show();
		}
	}
	
	private void buildTheGrid(){
		setTitle(keyword);
		if((LinearLayout)findViewById(R.id.intro) != null)
			homeLinear.removeView(introLayout);
		grid = (GridView)findViewById(R.id.grid_students);
		studentList = new studentListAdapter(this, Result.result, keyword);
		grid.setAdapter(studentList);
		grid.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
                Intent details = new Intent(Homepage.this, Details.class);
				details.putExtra("index", position);
				startActivity(details);
            }
		});
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		super.onOptionsItemSelected(item);

		switch (item.getItemId()) {
			case R.id.action_faculty:
				Intent about = new Intent(this, FacultyList.class);
				startActivity(about);
				break;
			case R.id.action_credits:
				Intent credits = new Intent(this, Credits.class);
				startActivity(credits);
				break;
		}
		return false;
	}
	
	
	@Override
	public void onBackPressed() {
		if(backPressed + timeInterval > System.currentTimeMillis()){
			super.onBackPressed();
			return;
		}else{
			Toast.makeText(getApplicationContext(), "press back agian to exit", Toast.LENGTH_SHORT).show();
		}
		backPressed = System.currentTimeMillis();
	}

	@Override
	public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
    }


	public void onScrollStateChanged(AbsListView view, int scrollState) {
        switch (scrollState) {
            case AbsListView.OnScrollListener.SCROLL_STATE_IDLE:
                studentList.notifyDataSetChanged();
                break;
        }
    }
    
}
