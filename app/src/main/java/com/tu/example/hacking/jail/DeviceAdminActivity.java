package com.tu.example.hacking.jail;

import android.app.admin.DevicePolicyManager;
import android.content.ComponentName;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import com.tu.example.R;

/**
 * 无法卸载的app
 */
public class DeviceAdminActivity extends AppCompatActivity implements View.OnClickListener {

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_device_admin);

    findViewById(R.id.hacking_disable).setOnClickListener(this);
    findViewById(R.id.hacking_enable).setOnClickListener(this);
  }

  @Override public void onClick(View view) {
    switch (view.getId()) {
      case R.id.hacking_disable:
        Intent intent = new Intent(DevicePolicyManager.ACTION_ADD_DEVICE_ADMIN);
        ComponentName deviceComponentName = new ComponentName("com.tu.example",
            DisableDeviceAdminReceiver.class.getName());
        intent.putExtra(DevicePolicyManager.EXTRA_DEVICE_ADMIN, deviceComponentName);
        this.startActivity(intent);
        break;
    }
  }
}
