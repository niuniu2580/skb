package com.skb.adapter;

import java.util.List;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.ImageView;

public class HangoutViewPageAdapter extends PagerAdapter{

	private List<ImageView> mViews;
	private Context context;
	public HangoutViewPageAdapter(Context context,List<ImageView> views) {
			mViews=views;
			this.context=context;
	}
	@Override
	public void destroyItem(View container, int position, Object object) {
		
	}
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
			
			position %= mViews.size();
			if(position<0){
				position=mViews.size()+position;
			}
			ImageView view=mViews.get(position);
			ViewParent vp=view.getParent();
			if(vp!=null){
				ViewGroup parent=(ViewGroup) vp;
				parent.removeView(view);
			}
			container.addView(view);
			return view;
	}
	
	@Override
	public int getCount() {

		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {

		return arg0==arg1;
	}

}
