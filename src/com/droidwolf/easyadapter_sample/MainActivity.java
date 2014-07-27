package com.droidwolf.easyadapter_sample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.*;

public class MainActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		findViewById(R.id.easyadapter).setOnClickListener(this);
		findViewById(R.id.easycusoradapter).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.easyadapter:
			startActivity(new Intent(this, EasyAdapterActivity.class));
			break;
		case R.id.easycusoradapter:
			startActivity(new Intent(this, EasyCusorAdapterActivity.class));
			break;
		}
	}

}
