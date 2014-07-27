package com.droidwolf.easyadapter_sample;

import android.content.Context;
import android.database.Cursor;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidwolf.easyadapter.AbsCursorViewItem;
import com.droidwolf.easyadapter.CursorViewItemFactory;
import com.droidwolf.easyadapter.ViewItem;
import com.droidwolf.easyadapter_sample.db.PersonDao.COLUMNINDEXS;

public class MyCursorViewItemFactory implements CursorViewItemFactory {
	private final int YELLOW = 0, GREEN = 1;

	@Override
	public ViewItem<Cursor> newViewItem(Cursor c, int position) {
		switch (getItemViewType(c, position)) {
		case YELLOW:
			return new YellowView();
		case GREEN:
			return new GreenView();
		}
		return null;
	}

	@Override
	public int getItemViewType(Cursor c, int position) {
		if (position % 2 == 0) {
			return YELLOW;
		}
		return GREEN;
	}

	@Override
	public int getViewTypeCount() {
		return 2;
	}

	public static class YellowView extends AbsCursorViewItem  implements View.OnClickListener{
		@Override
		public View newView(Context context, Cursor data, ViewGroup parent) {
			TextView tv =(TextView) LayoutInflater.from(context).inflate(R.layout.viewitem, null);
			tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			tv.setBackgroundColor(Color.YELLOW);
			return tv;
		}

		@Override
		public void bindView(View convertView, Context context, Cursor c,
				int scrollState) {
			TextView tv = (TextView) convertView;
			switch (scrollState) {
			case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
				tv.setText(String.format("Fling--%s(%s)" ,c.getString(COLUMNINDEXS.NAME), getClass().getSimpleName()));
				tv.setOnClickListener(null);
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				tv.setText(String.format("Scrolling--%s(%s)" ,c.getString(COLUMNINDEXS.NAME), getClass().getSimpleName()));
				tv.setOnClickListener(null);
				break;
			default:
				tv.setText(String.format("%s(%s)" ,c.getString(COLUMNINDEXS.NAME), getClass().getSimpleName()));
				tv.setTag(c.getString(COLUMNINDEXS.NAME));
				tv.setOnClickListener(this);
				break;
			}
		}
		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), String.format("%s(%s)" ,v.getTag(), getClass().getSimpleName()), Toast.LENGTH_SHORT).show();;
		}
	}// end class TextViewItem

	public static class GreenView extends YellowView {
		@Override
		public View newView(Context context, Cursor data, ViewGroup parent) {
			TextView tv = (TextView) super.newView(context, data, parent);
			tv.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
			tv.setBackgroundColor(Color.GREEN);
			return tv;
		}

	}// end class ButtonViewItem

}// end class MyViewItemFactory
