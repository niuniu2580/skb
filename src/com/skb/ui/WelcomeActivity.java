package com.skb.ui;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.skb.R;

public class WelcomeActivity extends Activity {
	public static final long WELCOME_TIME = 3000;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.welcome);
		new Handler().postDelayed(new Runnable() {
			public void run() {
				goHome();
			}
		}, WELCOME_TIME);
	}

	private void goHome() {
		Intent intent = new Intent(this, SortActivity.class);
		WelcomeActivity.this.startActivity(intent);
		WelcomeActivity.this.finish();
	}

}
