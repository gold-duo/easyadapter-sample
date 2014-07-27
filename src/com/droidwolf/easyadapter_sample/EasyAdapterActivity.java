package com.droidwolf.easyadapter_sample;

import java.util.ArrayList;
import java.util.List;

import com.droidwolf.easyadapter_sample.db.DbOpenHelper;
import com.droidwolf.easyadapter_sample.db.PersonDao;
import com.droidwolf.easyadapter.EasyAdapter;
import com.droidwolf.easyadapter.EasyCusorAdapter;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.widget.ListView;

public class EasyAdapterActivity extends Activity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ListView list = new ListView(this);
		setContentView(list);
		list.setAdapter(new EasyAdapter(this, list, genList(), new MyViewItemFactory()));
	}

	private List<Object> genList() {
		List<Object> list = new ArrayList<Object>(300);
		for (int i = 0; i < 300; i++) {
			if (i % 2 == 0) {
				list.add("string" + i);
			} else {
				list.add(i);
			}
		}
		return list;
	}

}
