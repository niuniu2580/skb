package com.skb.bmob;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.skb.R;
import com.skb.entity.FoodInfo;

public class QueryMessage extends Activity {

	TextView food_title, food_content, food_method;
	ImageView food_image;
	ImageButton backToHangout;
	Bitmap bitmap;
	String title;
	String content;
	String method;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.grid_item);
		showView();
	}

	public void showView() {
		food_title = (TextView) findViewById(R.id.textView_title);
		food_image = (ImageView) findViewById(R.id.grid_food_image);
		food_content = (TextView) findViewById(R.id.grid_food_content);
		food_method = (TextView) findViewById(R.id.grid_food_method);
		backToHangout = (ImageButton) findViewById(R.id.imageButton_back);
		backToHangout.setOnClickListener(listener);
		BmobQuery<FoodInfo> query = new BmobQuery<FoodInfo>();
		
		Intent intent = getIntent();
		String foodMsg = intent.getStringExtra("foodMsg");
		System.out.println(foodMsg+"-------------------------------------");
		query.addWhereEqualTo("title", foodMsg);
		query.findObjects(QueryMessage.this, new FindListener<FoodInfo>() {

			@Override
			public void onSuccess(List<FoodInfo> foodInfo) {
				for (FoodInfo foods : foodInfo) {
 					title = foods.getTitle();
//					content = foods.getFoodContent();
					method = foods.getFoodMethod();
					byte[] b=foods.getFoodImg();
					bitmap=getPicFromByte(b, 80, 60);
				}
				Handler handler = new Handler();
				handler.post(new Runnable() {

					@Override
					public void run() {
						food_title.setText(title);
						food_content.setText(content);
						food_method.setText(method);
						food_image.setImageBitmap(bitmap);
					}
				});
			}

			@Override
			public void onError(int arg0, String arg1) {
				Toast.makeText(QueryMessage.this, "出错啦", Toast.LENGTH_SHORT)
						.show();
			}
		});

	}
	/**
	 * 将服务器上的二进制流读取下来，转换成图片
	 * 并将其的大小改变
	 * @param bytes
	 * @return
	 */
	public Bitmap getPicFromByte(byte[] bytes, int mReqWidth, int mReqHeight) {

		Bitmap bitmap = null;
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

	public OnClickListener listener = new OnClickListener() {

		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.imageButton_back:
					QueryMessage.this.finish();
				break;

			default:
				break;
			}
		}
	};
}
