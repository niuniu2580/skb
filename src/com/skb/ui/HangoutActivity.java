package com.skb.ui;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.skb.R;
import com.handmark.pulltorefresh.library.PullToRefreshBase;
import com.handmark.pulltorefresh.library.PullToRefreshBase.OnRefreshListener2;
import com.handmark.pulltorefresh.library.PullToRefreshListView;
import com.skb.adapter.HangoutViewPageAdapter;
import com.skb.adapter.ListAdapter;
import com.skb.entity.FoodInfo;
import com.skb.entity.FoodItem;
import com.skb.entity.HangoutItem;
import com.skb.paint.MyView;

public class HangoutActivity extends Activity implements OnPageChangeListener {

	final int MENU_SET_MODE = 0;
	PullToRefreshListView mPullRefreshListView;
	List<FoodItem> foodList;
	MyView view;
	ListAdapter listAdapter;

	private ImageButton ImgButton_back = null;
	private ImageButton ImgButton_sort = null;
	private Button button_sort = null;
	/**
	 * 广告栏的设置
	 */
	public ViewPager mViewPager;
	private HangoutViewPageAdapter adapter;
	private List<ImageView> mViews;

	// public ImageHandler handler=new ImageHandler(new
	// WeakReference<HangoutActivity>(this));

	// 导航点
	// private ImageView[] points;
	// private int[] pointIds={R.id.point1,R.id.point2,R.id.point3};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.hangout);
		ImgButton_back = (ImageButton) findViewById(R.id.imageButton_back);
		ImgButton_sort = (ImageButton) findViewById(R.id.imageButton_sort);
		button_sort = (Button) findViewById(R.id.button_sort);

		ImgButton_back.setOnClickListener(listener);
		ImgButton_sort.setOnClickListener(listener);
		button_sort.setOnClickListener(listener);

		initView();
		// initPoint();

		/**
		 * 逛一逛的ListView
		 */
		Bmob.initialize(this, "8774785dac79711f4f8a914efce9a4bf");
	}

	private  void getAndSetData() {

		foodList = new ArrayList<FoodItem>();
		view = new MyView(HangoutActivity.this);

		BmobQuery<HangoutItem> query = new BmobQuery<HangoutItem>();
		query.include("recipe1,recipe2,recipe3");
		query.findObjects(HangoutActivity.this,
				new FindListener<HangoutItem>() {

					@Override
					public void onSuccess(List<HangoutItem> args) {

						for (int i = 0; i < args.size(); i++) {

							FoodItem item = new FoodItem();
							String title = args.get(i).getTitle();
							
							FoodInfo recipe1 = args.get(i).getRecipe1();
							String title1 = recipe1.getTitle();
							byte[] img1 = recipe1.getFoodImg();
							System.out.println(title1
									+ "-------------------------");
							System.out.println(img1
									+ "------------------------");
						
							FoodInfo recipe2 = args.get(i).getRecipe2();
							String title2 = recipe2.getTitle();
							byte[] img2 = recipe2.getFoodImg();
							System.out.println(title2
									+ "-------------------------");
							System.out.println(img2
									+ "------------------------");
							
							FoodInfo recipe3 = args.get(i).getRecipe3();
							String title3 = recipe3.getTitle();
							byte[] img3 = recipe3.getFoodImg();
							System.out.println(title3
									+ "-------------------------");
							System.out.println(img3
									+ "------------------------");
							

							item.setItemTitle(title);
							item.setFirstItemResId(view.toCircle(getPicFromByte(img1,500,300)));
							item.setFirstItemMsg(title1);
							item.setSecondItemResId(view.toCircle(getPicFromByte(img2,500,300)));
							item.setSecondItemMsg(title2);
							item.setThirdItemResId(view.toCircle(getPicFromByte(img3,500,300)));
							item.setThirdItemMsg(title3);
							
							foodList.add(item);
							System.out.println(foodList.size());
						}

					}

					@Override
					public void onError(int arg0, String arg1) {
						Toast.makeText(HangoutActivity.this,
								"fail to downLoad!", Toast.LENGTH_SHORT).show();
					}
				});

	}
	
	/*
	 * 此方法负责调用下拉刷新和加载更多的接口 负责调用界面变化的检测方法 并设置adapter
	 */
	/*
	 * 此方法负责调用下拉刷新和加载更多的接口 负责调用界面变化的检测方法 并设置adapter
	 */

	public void showList(List<FoodItem> foodItem) {
		System.out.println("执行此方法前");
		if (listAdapter == null) {
			listAdapter = new ListAdapter(HangoutActivity.this, foodList);
			mPullRefreshListView.setAdapter(listAdapter);
		} else {
			listAdapter.onDataChanged(foodItem);
		}
	}
	


	private void initView() {
		/*
		 * ViewPager的设置
		 */
		mViewPager = (ViewPager) findViewById(R.id.ViewPager);
		mViews = new ArrayList<ImageView>();
		LayoutInflater inflater = LayoutInflater.from(this);
		ImageView view1 = (ImageView) inflater.inflate(R.layout.ad1, null);
		ImageView view2 = (ImageView) inflater.inflate(R.layout.ad2, null);
		ImageView view3 = (ImageView) inflater.inflate(R.layout.ad3, null);
		mViews.add(view1);
		mViews.add(view2);
		mViews.add(view3);
		adapter = new HangoutViewPageAdapter(this, mViews);
		mViewPager.setAdapter(adapter);
		mViewPager.setOnPageChangeListener(this);
		mViewPager.setCurrentItem(Integer.MAX_VALUE / 2);
		// handler.sendEmptyMessageDelayed(ImageHandler.MSG_UPDATE_IMAGE,
		// ImageHandler.MSG_DELAY);
		
		/***
		 * 下拉组件的初始化
		 */
		
		mPullRefreshListView = (PullToRefreshListView) this
				.findViewById(R.id.hangout_list);
		mPullRefreshListView
				.setOnRefreshListener(new OnRefreshListener2<ListView>() {
					/**
					 * 上拉刷新
					 */
					@Override
					public void onPullDownToRefresh(
							PullToRefreshBase refreshView) {

						Handler handler = new Handler();
						handler.post(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(HangoutActivity.this,
										"刷新成功！", Toast.LENGTH_SHORT).show();
								getAndSetData();
								showList(foodList);
								System.out.println("执行此方法后");
								mPullRefreshListView.onRefreshComplete();
							}
						});
					}

					/**
					 * 下拉刷新
					 */
					@Override
					public void onPullUpToRefresh(
							PullToRefreshBase refreshView) {

						Handler handler = new Handler();
						handler.post(new Runnable() {

							@Override
							public void run() {
								Toast.makeText(HangoutActivity.this,
										"刷新成功！", Toast.LENGTH_SHORT).show();
								mPullRefreshListView.onRefreshComplete();
							}
						});

					}
				});

	}

	// private void initPoint(){
	// points=new ImageView[mViews.size()];
	// for(int i=0;i<mViews.size();i++){
	// points[i]=(ImageView) findViewById(pointIds[i]);
	// }
	// }

	@Override
	public void onPageScrollStateChanged(int arg0) {
		// switch (arg0) {
		// case ViewPager.SCROLL_STATE_DRAGGING:
		// handler.sendEmptyMessage(ImageHandler.MSG_KEEP_SILENT);
		// break;
		// case ViewPager.SCROLL_STATE_IDLE:
		// handler.sendEmptyMessage(ImageHandler.MSG_UPDATE_IMAGE);
		// break;
		// default:
		// break;
		// }
	}

	@Override
	public void onPageScrolled(int arg0, float arg1, int arg2) {

	}

	@Override
	public void onPageSelected(int arg0) {
		// handler.sendMessage(Message.obtain(handler,ImageHandler.MSG_PAGE_CHANGED
		// ,arg0,0));
		// for(int i=0;i<pointIds.length;i++){
		// if(arg0==i){
		// points[i].setImageResource(R.drawable.selected);
		// }else{
		// points[i].setImageResource(R.drawable.unselected);
		// }
		// }
	}

	/**
	 * 将服务器上的二进制流读取下来，转换成图片 并将其的大小改变
	 * 
	 * @param bytes
	 * @return
	 */
	public Bitmap getPicFromByte(byte[] bytes,int mReqWidth,int mReqHeight) {
			
		
		Bitmap bitmap = null;
		if(bytes!=null){
			bitmap=BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
		}
	
		BitmapFactory.Options option = new BitmapFactory.Options();

		option.inJustDecodeBounds = true;

		if (bytes != null) {
			bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length,
					option);
		}

		option.inSampleSize = caculateBitmap(option, mReqWidth, mReqHeight);

		option.inJustDecodeBounds = false;

		bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length, option);
		return bitmap;
	}

	/***
	 * 重新计算采样比例
	 * 
	 * @param option
	 * @param reqWidth
	 * @param reqHeight
	 * @return
	 */

	private int caculateBitmap(BitmapFactory.Options option, int reqWidth,
			int reqHeight) {

		int width = option.outWidth;
		int height = option.outHeight;
		int sampleSize = 1;
		if (width > reqWidth || height > reqHeight) {
			if (width > height) {
				sampleSize = Math.round((float) height / (float) reqHeight);
			} else {
				sampleSize = Math.round((float) width / (float) reqWidth);
			}
		}

		return sampleSize;
	}
	/**
	 * 对按钮时间的监听
	 */
	public OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch (v.getId()) {
			case R.id.imageButton_back:
				intent.setClass(HangoutActivity.this, SortActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.imageButton_sort:
				intent.setClass(HangoutActivity.this, SortActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			case R.id.button_sort:
				intent.setClass(HangoutActivity.this, SortActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				startActivity(intent);
				break;
			}
		}
	};
}
