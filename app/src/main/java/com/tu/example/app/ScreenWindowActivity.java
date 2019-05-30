package com.tu.example.app;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.PowerManager;
import android.support.annotation.Nullable;
import android.view.Window;
import android.view.WindowManager;
import com.tu.example.R;

/**
 * 替代锁屏
 * @author Tu enum@foxmail.com
 * @date 16/7/1
 */
public class ScreenWindowActivity extends Activity {
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    final Window window = getWindow();
    window.addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED
        | WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD
        | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON
        | WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);

    setContentView(R.layout.activity_test);
    startService(new Intent(ScreenWindowActivity.this, ScreenService.class));
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    stopService(new Intent(ScreenWindowActivity.this, ScreenService.class));
  }

  @Override protected void onNewIntent(Intent intent) {
    super.onNewIntent(intent);
    PowerManager pm = (PowerManager) this.getSystemService(Context.POWER_SERVICE);
    if (!pm.isScreenOn()) {
      PowerManager.WakeLock wl =
          pm.newWakeLock(PowerManager.ACQUIRE_CAUSES_WAKEUP | PowerManager.SCREEN_BRIGHT_WAKE_LOCK,
              "bright");
      wl.acquire();
      wl.release();
    }
  }
}
