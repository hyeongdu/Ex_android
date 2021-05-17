package com.study.android.service2;

import android.content.*;
import android.util.Log;
import android.widget.*;

public class AlarmReceiver extends BroadcastReceiver {
	private static final String TAG = "lecture";

	public void onReceive(Context context, Intent intent) {
		Log.d(TAG, "지정한 시간입니다.");
		Toast.makeText(context, "지정한 시간입니다.",
				Toast.LENGTH_LONG).show();
	}
}