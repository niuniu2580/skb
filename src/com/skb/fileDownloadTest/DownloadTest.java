package com.skb.fileDownloadTest;

import java.io.ByteArrayOutputStream;
import java.util.List;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.listener.FindListener;

import com.example.skb.R;
import com.skb.entity.FoodInfo;
import com.skb.entity.HangoutItem;
import com.skb.paint.MyView;

public class DownloadTest extends Activity {

	private ImageView showTest;
	private TextView text;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show_image);
		Bmob.initialize(this, "8774785dac79711f4f8a914efce9a4bf");

		initView();

		sendMessage();
		 downMessage();

	}

	/**
	 * 将本地的图片转换成字节流
	 * 
	 * @param resId
	 * @return
	 */

	public byte[] bitmap2Stream(int resId) {
		Bitmap bitmap = BitmapFactory.decodeResource(getResources(), resId);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		 bitmap.compress(Bitmap.CompressFormat.JPEG, 85, baos);
		return baos.toByteArray();
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

	public void downMessage() {
//		final MyView view=new MyView(DownloadTest.this);
		BmobQuery<HangoutItem> query = new BmobQuery<HangoutItem>();
		query.include("recipe1");
		query.findObjects(DownloadTest.this,
				new FindListener<HangoutItem>() {

					@Override
					public void onSuccess(List<HangoutItem> args) {

					for(HangoutItem item:args){

							String title =item.getTitle();

							FoodInfo recipe1 = item.getRecipe1();
							String title1 = recipe1.getTitle();
							byte[] img1 = recipe1.getFoodImg();
							System.out.println(title1
									+ "-------------------------");
							System.out.println(img1
									+ "------------------------");
							showTest.setImageBitmap(getPicFromByte(img1, 160, 200));
							text.setText(title1+"\n"+title);
						}
					}	
					@Override
					public void onError(int arg0, String arg1) {
								Toast.makeText(DownloadTest.this, "出错啦", Toast.LENGTH_SHORT).show();
					}
				});
	}

	private void sendMessage() {
		FoodInfo foodInfo1 = new FoodInfo();
		foodInfo1.setTitle("松仁玉米");
		foodInfo1.setFoodImg(bitmap2Stream(R.drawable.songrenyumi));
		foodInfo1.setFoodMain("。松子仁，80克。玉米，1根。");
		foodInfo1.setFoodSecond("。青红椒，20克。");
		foodInfo1.setFoodMethod( "1、 把玉米剥粒，青红椒切小粒#2、锅内坐水，放一点糖，水开后下玉米焯烫至熟，然后捞出过凉，沥干水备用#3、 炒锅不放油，直接把松子仁煸香#4、  开火，锅烧热，放油，下青红椒煸炒断生#5、下玉米粒翻炒#6、调入适量盐#7、大火炒匀即可出锅#8、盛盘后，往上面洒上适量炒香的松子仁即可");
		
		foodInfo1.save(DownloadTest.this);
		Toast.makeText(DownloadTest.this, "上传成功", Toast.LENGTH_SHORT).show();
		
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
	

	private void initView() {
		showTest = (ImageView) findViewById(R.id.imageView_showTest);
		text=(TextView) findViewById(R.id.text);
	}

}
