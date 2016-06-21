package com.skb.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import cn.bmob.v3.Bmob;
import cn.bmob.v3.BmobInstallation;

import com.example.skb.R;

public class SortActivity extends Activity {
	
	private ImageButton hangout = null;
	private Button button_hangout=null;
	
	private ImageButton acid=null;
	private Button button_acid=null;
	private ImageButton spicy=null;
	private Button button_spicy=null;
	private ImageButton salty=null;
	private Button button_salty=null;
	private ImageButton sweet = null;
	private Button button_sweet=null;
	private ImageButton more = null;
	private Button button_more=null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sort_activity);
		
		Bmob.initialize(this, "8774785dac79711f4f8a914efce9a4bf");
		BmobInstallation.getCurrentInstallation(this).save();
		
		hangout = (ImageButton) findViewById(R.id.ImageButton_hangout);
		button_hangout=(Button) findViewById(R.id.button_hangout);

		sweet=(ImageButton) findViewById(R.id.imageView_sweet);
		button_sweet=(Button) findViewById(R.id.button_sweet);
		acid=(ImageButton) findViewById(R.id.imageView_acid);		
		button_acid=(Button) findViewById(R.id.button_acid);
		salty=(ImageButton) findViewById(R.id.imageView_salt);		
		button_salty=(Button) findViewById(R.id.button_salty);
		spicy=(ImageButton) findViewById(R.id.imageView_spicy);
		button_spicy=(Button) findViewById(R.id.button_spicy);
		more=(ImageButton) findViewById(R.id.imageView_more);
		button_more=(Button) findViewById(R.id.button_more);
		
		
		hangout.setOnClickListener(listener);
		button_hangout.setOnClickListener(listener);
		
		acid.setOnClickListener(listener);
		button_acid.setOnClickListener(listener);
		spicy.setOnClickListener(listener);
		button_spicy.setOnClickListener(listener);
		salty.setOnClickListener(listener);
		button_salty.setOnClickListener(listener);
		sweet.setOnClickListener(listener);
		button_sweet.setOnClickListener(listener);
		more.setOnClickListener(listener);
		button_more.setOnClickListener(listener);
	}
	public OnClickListener listener = new OnClickListener(){

		@Override
		public void onClick(View v) {
			Intent intent = new Intent();
			switch(v.getId())
			{
				case R.id.ImageButton_hangout:
					intent.setClass(SortActivity.this, HangoutActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.button_hangout:
					intent.setClass(SortActivity.this, HangoutActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
					
				case R.id.imageView_acid:
					intent.setClass(SortActivity.this, AcidActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.button_acid:
					intent.setClass(SortActivity.this, AcidActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.imageView_spicy:
					intent.setClass(SortActivity.this, SpicyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.button_spicy:
					intent.setClass(SortActivity.this, SpicyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.imageView_sweet:
					intent.setClass(SortActivity.this, SweetActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.button_sweet:
					intent.setClass(SortActivity.this, SweetActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.imageView_salt:
					intent.setClass(SortActivity.this, SaltyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.button_salty:
					intent.setClass(SortActivity.this, SaltyActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.imageView_more:
					intent.setClass(SortActivity.this, MoreActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;
				case R.id.button_more:
					intent.setClass(SortActivity.this, MoreActivity.class);
					intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
					startActivity(intent);
					break;			
			}
		}
		
	   };
 }	