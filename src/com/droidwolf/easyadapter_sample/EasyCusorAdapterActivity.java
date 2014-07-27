package com.droidwolf.easyadapter_sample;

import com.droidwolf.easyadapter_sample.db.DbOpenHelper;
import com.droidwolf.easyadapter_sample.db.PersonDao;
import com.droidwolf.easyadapter.EasyCusorAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class EasyCusorAdapterActivity extends Activity {
	EasyCusorAdapter mAdapter;

	@Override
	protected void onDestroy() {
		if (mAdapter.getCursor() != null) {
			mAdapter.getCursor().close();
		}
		super.onDestroy();
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView list =new ListView(this);
		setContentView(list);
		
		PersonDao pd = new PersonDao(new DbOpenHelper(this));
		Cursor c = pd.query(null, null);
		mAdapter = new EasyCusorAdapter(this, c, list, new MyCursorViewItemFactory());
		list.setAdapter(mAdapter);
	}
}
