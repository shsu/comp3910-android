package ca.bcit.turnip.helper;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public final class MyApp extends Application {
	private static RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		Log.d("mRequestQueue", mRequestQueue.toString());
	}

	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
}
