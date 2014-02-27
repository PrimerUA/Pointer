package com.primerworldapps.pointer.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout.LayoutParams;
import android.widget.TextView;
import com.android.volley.toolbox.NetworkImageView;
import com.primerworldapps.pointer.R;
import com.primerworldapps.pointer.datastorage.table.ProposalTable;
import com.primerworldapps.pointer.network.VolleyHelper;

/**
 * Created with IntelliJ IDEA. User: roman Date: 2/13/14 Time: 9:06 AM To change
 * this template use File | Settings | File Templates.
 */
public class ProposalsListAdapter extends CursorAdapter {
	private int nameColumnIndex = -1;
	private int greetingColumnIndex = -1;
	private int photoColumnIndex = -1;

	public ProposalsListAdapter(Context context, int flags) {
		super(context, null, flags);
	}

	@Override
	public View newView(Context context, Cursor cursor, ViewGroup viewGroup) {
		// create view
		LayoutInflater inflater = LayoutInflater.from(context);
		View view = inflater.inflate(R.layout.proposals_list_item, viewGroup, false);

		// create holder
		ViewHolder holder = new ViewHolder();
		holder.name = (TextView) view.findViewById(R.id.name);
		holder.greeting = (TextView) view.findViewById(R.id.greeting);
		holder.photo = (NetworkImageView) view.findViewById(R.id.photo);

		view.setTag(holder);
		view.getLayoutParams().height = viewGroup.getHeight() / 3;
		view.requestLayout();
		return view;
	}

	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();

		if (!isColumnIndexesCalculated()) {
			calculateColumnIndexes(cursor);
		}

		holder.name.setText(cursor.getString(nameColumnIndex));
		holder.greeting.setText(cursor.getString(greetingColumnIndex));
		holder.photo.setImageUrl(cursor.getString(photoColumnIndex), VolleyHelper.getInstance().getImageLoader());
	}

	private boolean isColumnIndexesCalculated() {
		return (nameColumnIndex >= 0);
	}

	private void calculateColumnIndexes(Cursor cursor) {
		nameColumnIndex = cursor.getColumnIndex(ProposalTable.NAME_COLUMN);
		greetingColumnIndex = cursor.getColumnIndex(ProposalTable.GREETING_COLUMN);
		photoColumnIndex = cursor.getColumnIndex(ProposalTable.PHOTO_COLUMN);
	}

	private static class ViewHolder {
		public TextView name;
		public TextView greeting;
		public NetworkImageView photo;
	}

}
