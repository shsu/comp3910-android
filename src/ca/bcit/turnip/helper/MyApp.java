package ca.bcit.turnip.helper;

import android.app.Application;
import android.util.Log;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * The Class MyApp.
 */
public final class MyApp extends Application {

	/** The m request queue. */
	private static RequestQueue mRequestQueue;

	@Override
	public void onCreate() {
		super.onCreate();
		mRequestQueue = Volley.newRequestQueue(getApplicationContext());
		Log.d("mRequestQueue", mRequestQueue.toString());
	}

	/**
	 * Gets the request queue.
	 * 
	 * @return the request queue
	 */
	public static RequestQueue getRequestQueue() {
		return mRequestQueue;
	}
}
