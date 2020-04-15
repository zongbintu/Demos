package com.tu.example.bug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import com.tu.example.R;
import java.util.Random;

public class OnRestoreActivity extends AppCompatActivity implements View.OnClickListener {

  StepFragment bundleFragment1, bundleFragment2, saveFragment1, saveFragment2;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_on_restore);
    findViewById(R.id.bundle1_btn).setOnClickListener(this);
    findViewById(R.id.bundle2_btn).setOnClickListener(this);
    findViewById(R.id.savedstate1_btn).setOnClickListener(this);
    findViewById(R.id.savedstate2_btn).setOnClickListener(this);
  }

  @Override
  public void onClick(View v) {
    Fragment fragment = null;
    switch (v.getId()) {
      case R.id.bundle1_btn:
        if (bundleFragment1 == null) {
          bundleFragment1 =
              StepFragment.newInstance(new Random().nextInt(4), R.layout.fragment_restore_bundle);
        }
        fragment = bundleFragment1;
        break;
      case R.id.bundle2_btn:
        if (bundleFragment2 == null) {
          bundleFragment2 =
              StepFragment.newInstance(new Random().nextInt(4), R.layout.fragment_restore_bundle);
        }
        fragment = bundleFragment2;
        break;

      case R.id.savedstate1_btn:
        if (saveFragment1 == null) {
          saveFragment1 = StepFragment.newInstance(new Random().nextInt(4),
              R.layout.fragment_restore_savedstate);
        }
        fragment = saveFragment1;
        break;
      case R.id.savedstate2_btn:
        if (saveFragment2 == null) {
          saveFragment2 = StepFragment.newInstance(new Random().nextInt(4),
              R.layout.fragment_restore_savedstate);
        }
        fragment = saveFragment2;
        break;
    }
    if (null != fragment) {
      getSupportFragmentManager().beginTransaction()
          .replace(R.id.content, fragment)
          .commitAllowingStateLoss();
    }
  }
}
