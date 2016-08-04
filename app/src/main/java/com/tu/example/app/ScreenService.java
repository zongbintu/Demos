package com.tu.example.app;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

public class ScreenService extends Service {
  private static final String TAG = ScreenService.class.getSimpleName();

  // 实时监听状态改变
  private BroadcastReceiver mReceiver = new BroadcastReceiver() {
    @Override public void onReceive(Context context, Intent intent) {
      if (Intent.ACTION_SCREEN_ON.equals(intent.getAction())) {
      } else if (Intent.ACTION_SCREEN_OFF.equals(intent.getAction())) {
        Intent alarmIntent = new Intent(context, ScreenWindowActivity.class);
        alarmIntent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.startActivity(alarmIntent);
      }
      Log.d(TAG, intent.getAction());
    }
  };

  @Override public void onCreate() {
    // 注册广播
    IntentFilter filter = new IntentFilter();
    filter.addAction(Intent.ACTION_SCREEN_ON);
    filter.addAction(Intent.ACTION_SCREEN_OFF);
    filter.addAction(Intent.ACTION_USER_PRESENT);
    registerReceiver(mReceiver, filter);
  }

  @Override public void onDestroy() {
    super.onDestroy();
    // 删除广播
    unregisterReceiver(mReceiver);
  }

  @Nullable @Override public IBinder onBind(Intent intent) {
    return null;
  }
}
