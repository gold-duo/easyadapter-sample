package com.droidwolf.easyadapter_sample;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.TextView;
import android.widget.Toast;

import com.droidwolf.easyadapter.AbsViewItem;
import com.droidwolf.easyadapter.ViewItem;
import com.droidwolf.easyadapter.ViewItemFactory;

public class MyViewItemFactory implements ViewItemFactory {
	private final int STRING_YELLOW = 0, INTEGER = 1, STRING_GRAY = 2;

	@Override
	public <T> ViewItem<?> newViewItem(T data, int position) {
		switch (getItemViewType(data, position)) {
		case STRING_GRAY:
			return new StringGrayView();
		case STRING_YELLOW:
			return new StringYellowView();
		case INTEGER:
			return new IntegerView();
		}
		return null;
	}

	@Override
	public <T> int getItemViewType(T data, int position) {
		if (data instanceof String) {
			if (position == 2 || position == 4|| position == 8|| position == 12) {
				return STRING_GRAY;
			}
			return STRING_YELLOW;
		} else if (data instanceof Integer) {
			return INTEGER;
		}
		return AbsListView.ITEM_VIEW_TYPE_IGNORE;
	}

	@Override
	public int getViewTypeCount() {
		return 3;
	}

	public static class IntegerView extends AbsViewItem<Integer>implements View.OnClickListener {
		@Override
		public View newView(Context context, Integer data, ViewGroup parent) {
			TextView tv =(TextView) LayoutInflater.from(context).inflate(R.layout.viewitem, null);
			tv.setGravity(Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
			tv.setBackgroundColor(Color.GREEN);
			return tv;
		}

		@Override
		public void bindView(View convertView, Context context, Integer data,
				int scrollState) {
			TextView tv = (TextView) convertView;
			switch (scrollState) {
			case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
				tv.setText(String.format("Fling--%d(%s)" ,data, getClass().getSimpleName()));
				tv.setOnClickListener(null);
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				tv.setText(String.format("Scrolling--%d(%s)" ,data, getClass().getSimpleName()));
				tv.setOnClickListener(null);
				break;
			default:
				tv.setText(String.format("%d(%s)" ,data, getClass().getSimpleName()));
				tv.setTag(data);
				tv.setOnClickListener(this);
				break;
			}
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), String.format("%d(%s)" ,v.getTag(), getClass().getSimpleName()), Toast.LENGTH_SHORT).show();;
		}
	}// end class ButtonViewItem

	public static class StringYellowView extends AbsViewItem<String> implements View.OnClickListener{
		@Override
		public View newView(Context context, String data, ViewGroup parent) {
			TextView tv =(TextView) LayoutInflater.from(context).inflate(R.layout.viewitem, null);
			tv.setGravity(Gravity.LEFT | Gravity.CENTER_VERTICAL);
			tv.setBackgroundColor(Color.YELLOW);
			return tv;
		}

		@Override
		public void bindView(View convertView, Context context, String data,
				int scrollState) {
			TextView tv = (TextView) convertView;
			switch (scrollState) {
			case AbsListView.OnScrollListener.SCROLL_STATE_FLING:
				tv.setText(String.format("Fling--%s(%s)" ,data, getClass().getSimpleName()));
				tv.setOnClickListener(null);
				break;
			case AbsListView.OnScrollListener.SCROLL_STATE_TOUCH_SCROLL:
				tv.setText(String.format("Scrolling--%s(%s)" ,data, getClass().getSimpleName()));
				tv.setOnClickListener(null);
				break;
			default:
				tv.setText(String.format("%s(%s)" ,data, getClass().getSimpleName()));
				tv.setTag(data);
				tv.setOnClickListener(this);
				break;
			}
		}

		@Override
		public void onClick(View v) {
			Toast.makeText(v.getContext(), String.format("%s(%s)" ,v.getTag(), getClass().getSimpleName()), Toast.LENGTH_SHORT).show();;
		}
	}// end class StringView
	
	public static class StringGrayView extends StringYellowView {
		@Override
		public View newView(Context context, String data, ViewGroup parent) {
			TextView tv= (TextView) super.newView(context, data, parent);
			tv.setGravity(Gravity.RIGHT | Gravity.CENTER_VERTICAL);
			tv.setBackgroundColor(Color.GRAY);
			return tv;
		}

	}// end class TextViewItem
}// end class MyViewItemFactory
