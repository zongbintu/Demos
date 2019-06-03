package com.tu.example.app;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;
import com.tu.example.mainlist.MainActivity;

/**
 * Created by guanchao.gao on 2018/1/16.
 */

public class BootBroadcastReceiver extends BroadcastReceiver {
  public static final String TAG = BootBroadcastReceiver.class.getName();

  @Override
  public void onReceive(Context context, Intent intent) {
    String action = intent.getAction();
    Log.d(TAG, action);
    if (Intent.ACTION_PACKAGE_REPLACED.equals(action)
        || Intent.ACTION_BOOT_COMPLETED.equals(action)) {
      Intent intent2 = new Intent(context, MainActivity.class);
      intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
      context.startActivity(intent2);
    }
    if (Intent.ACTION_PACKAGE_REPLACED.equals(action)) {
      Toast.makeText(context, "PACKAGE_REPLACED", Toast.LENGTH_SHORT).show();
    }
  }
}
