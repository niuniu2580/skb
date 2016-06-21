package com.skb.adapter;

import java.util.List;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.skb.R;
import com.skb.bmob.QueryMessage;
import com.skb.entity.FoodItem;
import com.skb.paint.MyView;
import com.skb.ui.HangoutActivity;

public class ListAdapter extends BaseAdapter {

	List<FoodItem> mList;
	HangoutActivity context;
	ViewHolder holder;

	public ListAdapter(HangoutActivity hangout, List<FoodItem> food_List) {
		mList = food_List;
		this.context = hangout;
	}

	public void onDataChanged(List<FoodItem> list) {
		this.mList = list;
		this.notifyDataSetChanged();
	}

	@Override
	public int getCount() {
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		FoodItem foodItem = mList.get(position);
		if (convertView == null) {
			holder = new ViewHolder();
			LayoutInflater mInflater = LayoutInflater.from(context);
			convertView = mInflater.inflate(R.layout.food_item, null);
			holder.title = (TextView) convertView.findViewById(R.id.item_title);
			holder.image1 = (MyView) convertView.findViewById(R.id.image_first);
			holder.first_msg = (TextView) convertView
					.findViewById(R.id.first_msg);
			holder.image2 = (MyView) convertView
					.findViewById(R.id.image_second);
			holder.second_msg = (TextView) convertView
					.findViewById(R.id.second_msg);
			holder.image3 = (MyView) convertView.findViewById(R.id.image_third);
			holder.third_msg = (TextView) convertView
					.findViewById(R.id.third_msg);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		holder.title.setText(foodItem.getItemTitle());
		holder.image1.setImageBitmap(foodItem.getFirstItemResId());
		holder.first_msg.setText(foodItem.getFirstItemMsg());
		holder.image2.setImageBitmap(foodItem.getSecondItemResId());
		holder.second_msg.setText(foodItem.getSecondItemMsg());
		holder.image3.setImageBitmap(foodItem.getThirdItemResId());
		holder.third_msg.setText(foodItem.getThirdItemMsg());

		holder.image1.setOnClickListener(listener);
		holder.image2.setOnClickListener(listener);
		holder.image3.setOnClickListener(listener);

		return convertView;

	}

	class ViewHolder {
		TextView title, first_msg, second_msg, third_msg;
		MyView image1, image2, image3;
	}

	OnClickListener listener = new OnClickListener() {

		Intent intent;

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.image_first:
				intent = new Intent(context, QueryMessage.class);
				intent.putExtra("foodMsg", holder.first_msg.getText()
						.toString().trim());
				context.startActivity(intent);
				break;
			case R.id.image_second:
				intent = new Intent(context, QueryMessage.class);
				intent.putExtra("foodMsg", holder.second_msg.getText()
						.toString().trim());
				context.startActivity(intent);
				break;
			case R.id.image_third:
				intent = new Intent(context, QueryMessage.class);
				intent.putExtra("foodMsg", holder.third_msg.getText()
						.toString().trim());
				context.startActivity(intent);
				break;

			default:
				break;
			}
		}
	};

}
