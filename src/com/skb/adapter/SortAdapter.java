package com.skb.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.example.skb.R;
import com.skb.entity.SortFoodItem;


	public class SortAdapter extends BaseAdapter {

		List<SortFoodItem> mList;
		Context context;
		SortFoodItem sortItem ;

		public SortAdapter(Context context, List<SortFoodItem> food_List) {
			mList = food_List;
			this.context = context;
		}
		public void onDataChanged(List<SortFoodItem> list){
			this.mList=list;
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
		public View getView(int position, View convertView, ViewGroup parent) {
			
			ViewHolder holder;
			sortItem=mList.get(position);
			if (convertView == null) {
				holder=new ViewHolder();
				LayoutInflater mInflater = LayoutInflater.from(context);
				convertView = mInflater.inflate(R.layout.sort_item, null);
				holder.image=(ImageView) convertView.findViewById(R.id.sort_imageView);
				convertView.setTag(holder);
			}else{
				holder=(ViewHolder) convertView.getTag();
			}
			holder.image.setImageBitmap(sortItem.getItemResId());
			return convertView;

		}

		class ViewHolder {
			ImageView image;
		}

	}

