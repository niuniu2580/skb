package com.skb.paint;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.widget.ImageView;


public class MyView extends ImageView {

	
	

	private Bitmap bitmap;
	private Bitmap outBitmap;
	private Paint paint;
	
	public MyView(Context context){
		
		super(context,null);
	}
	public MyView(Context context,AttributeSet attrs){
		
		super(context,attrs,0);
	}
	public MyView(Context context, AttributeSet attrs, int defStyle) {
		
		super(context, attrs, defStyle);
		toCircle(bitmap);
	}
	public Bitmap toCircle(Bitmap bitmap){
		setLayerType(LAYER_TYPE_SOFTWARE, null);
		outBitmap=Bitmap.createBitmap(bitmap.getWidth(),  bitmap.getHeight(), Bitmap.Config.ARGB_8888);
		Canvas canvas=new Canvas(outBitmap);
		paint=new Paint(Paint.ANTI_ALIAS_FLAG);
		
		int width=bitmap.getWidth() / 2;
		int height=bitmap.getHeight() /2;
		canvas.drawCircle(width, height, Math.min(width, height), paint);
		paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
		canvas.drawBitmap(bitmap, 0, 0, paint);
		paint.setXfermode(null);
		return outBitmap;
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		
		try {
			super.onDraw(canvas);
			canvas.drawBitmap(outBitmap, 0, 0, paint);
			outBitmap.recycle();
		} catch (Exception e) {
		}
	}
}
