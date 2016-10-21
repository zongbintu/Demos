package com.tu.example.shortcut;

import android.annotation.TargetApi;
import android.content.Intent;
import android.content.pm.ShortcutInfo;
import android.content.pm.ShortcutManager;
import android.graphics.drawable.Icon;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.tu.example.R;
import java.util.ArrayList;
import java.util.Arrays;

public class ShortcutsActivity extends AppCompatActivity implements View.OnClickListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_shortcuts);
    findViewById(R.id.shortcut_dynamic).setOnClickListener(this);
    findViewById(R.id.shortcut_disable).setOnClickListener(this);
    findViewById(R.id.shortcut_enable).setOnClickListener(this);
  }

  @TargetApi(Build.VERSION_CODES.N_MR1) @Override public void onClick(View view) {
    ShortcutManager shortcutManager = getSystemService(ShortcutManager.class);
    switch (view.getId()) {
      case R.id.shortcut_dynamic:

        ShortcutInfo shortcut = new ShortcutInfo.Builder(this, "id1").setShortLabel("Web site")
            .setLongLabel("Open the web site")
            .setIcon(Icon.createWithResource(this, R.drawable.ic_shortcut_http))
            .setIntent(new Intent(Intent.ACTION_VIEW, Uri.parse("http://2tu.github.io/")))
            .build();

        shortcutManager.setDynamicShortcuts(Arrays.asList(shortcut));
        break;
      case R.id.shortcut_enable:
        ArrayList<String> ids = new ArrayList<>();
        ids.add("id1");
        shortcutManager.enableShortcuts(ids);
        break;
      case R.id.shortcut_disable:
        ArrayList<String> disableIds = new ArrayList<>();
        disableIds.add("id1");
        shortcutManager.disableShortcuts(disableIds);
        break;
    }
  }
}
