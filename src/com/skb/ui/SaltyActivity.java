package com.skb.ui;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.skb.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.Mode;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshGridView;
import com.skb.adapter.SortAdapter;
import com.skb.entity.SortFoodItem;
import com.skb.paint.MyView;

public class SaltyActivity extends Activity {
	PullToRefreshGridView mPullRefreshGridView;
	List<SortFoodItem> foodList;
	MyView view;
	SortAdapter adapter;
	final int MENU_SET_MODE=0;
	
	private ImageButton ImgButton_back=null;
	private ImageButton hangout=null;
	private Button button_hangout=null;
	

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.salty);

		ImgButton_back=(ImageButton) findViewById(R.id.imageButton_back);
		hangout=(ImageButton) findViewById(R.id.ImageButton_hangout);
		button_hangout=(Button) findViewById(R.id.button_hangout);
		
		
		ImgButton_back.setOnClickListener(listener);
		hangout.setOnClickListener(listener);
		button_hangout.setOnClickListener(listener);
		setData();
		showList(foodList);
		
	}

	/**
	 * ListView里的数据
	 */
	private void setData() {
		foodList = new ArrayList<SortFoodItem>();
		view = new MyView(SaltyActivity.this);

	/*	SortFoodItem item = new SortFoodItem();
		item.setItemResId(view.toCircle(R.drawable.acake));
		foodList.add(item);

		item = new SortFoodItem();
		item.setItemResId(view.toCircle(R.drawable.bcake));
		foodList.add(item);

		item = new SortFoodItem();
		item.setItemResId(view.toCircle(R.drawable.ccake));
		foodList.add(item);
		item = new SortFoodItem();
		item.setItemResId(view.toCircle(R.drawable.dcake));
		foodList.add(item);
		item = new SortFoodItem();
		item.setItemResId(view.toCircle(R.drawable.ecake));
		foodList.add(item);*/

	}

	/*
	 * 此方法负责调用下拉刷新和加载更多的接口 负责调用界面变化的检测方法 并设置adapter
	 */

	public void showList(List<SortFoodItem> foodItem) {
		if (adapter == null) {
			mPullRefreshGridView = (PullToRefreshGridView) this.findViewById(R.id.pull_grid);
			mPullRefreshGridView.setOnRefreshListener(new OnRefreshListener2<GridView>() {
				@Override
				public void onPullDownToRefresh(PullToRefreshBase refreshView) {
								
					Handler handler=new Handler();
					handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(SaltyActivity.this, "刷新成功！", Toast.LENGTH_SHORT).show();
							mPullRefreshGridView.onRefreshComplete();
						}
					}, 2000);
						
				}
				@Override
				public void onPullUpToRefresh(PullToRefreshBase refreshView) {
					
					Handler handler=new Handler();
					handler.postDelayed(new Runnable() {
						
						@Override
						public void run() {
							// TODO Auto-generated method stub
							Toast.makeText(SaltyActivity.this, "刷新成功！", Toast.LENGTH_SHORT).show();
							mPullRefreshGridView.onRefreshComplete();
						}
					}, 2000);
					
				}
			});
			adapter = new SortAdapter(SaltyActivity.this,
					foodList);
			mPullRefreshGridView.setAdapter(adapter);
		} else {
			adapter.onDataChanged(foodItem);
		}
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		menu.add(0, MENU_SET_MODE, 0,
				mPullRefreshGridView.getMode() == Mode.BOTH ? "Change to MODE_PULL_DOWN"
						: "Change to MODE_PULL_BOTH");
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		MenuItem setModeItem = menu.findItem(MENU_SET_MODE);
		setModeItem.setTitle(mPullRefreshGridView.getMode() == Mode.BOTH ? "Change to MODE_PULL_FROM_START"
				: "Change to MODE_PULL_BOTH");

		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case MENU_SET_MODE:
				mPullRefreshGridView
						.setMode(mPullRefreshGridView.getMode() == Mode.BOTH ? Mode.PULL_FROM_START
								: Mode.BOTH);
				break;
		}

		return super.onOptionsItemSelected(item);
	}
	public OnClickListener listener=new OnClickListener() {
		
		@Override
		public void onClick(View v) {
			Intent intent=new Intent();
			switch(v.getId()){
			 case R.id.imageButton_back:
				 intent.setClass(SaltyActivity.this,SortActivity.class);
				 startActivity(intent);
				 break;
			 case R.id.ImageButton_hangout:
				 intent.setClass(SaltyActivity.this,HangoutActivity.class);
				 startActivity(intent);
				 break;
			 case R.id.button_hangout:
				 intent.setClass(SaltyActivity.this,HangoutActivity.class);
				 startActivity(intent);
				 break;	 
			 
			}
		}
	};
}
