package com.tu.example.app;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import com.tu.example.R;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;

public class InstallSilentActivity extends AppCompatActivity {
  public static final String TAG = InstallSilentActivity.class.getName();
  EditText apkPathEditText;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_install_slient);

    apkPathEditText = findViewById(R.id.path_et);
    apkPathEditText.setText("/mnt/internal_sd/Download/1.2.apk");

    findViewById(R.id.install_btn).setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        installSlient(apkPathEditText.getText().toString());
      }
    });
  }

  /**
   * 静默安装
   *
   * @param apkPath apk path
   */
  public static boolean installSlient(String apkPath) {
    String cmd = "pm install -r " + apkPath;
    Process process = null;
    DataOutputStream os = null;
    BufferedReader successResult = null;
    BufferedReader errorResult = null;
    StringBuilder successMsg = null;
    StringBuilder errorMsg = null;
    try {
      //静默安装需要root权限
      process = Runtime.getRuntime().exec("su");
      os = new DataOutputStream(process.getOutputStream());
      os.write(cmd.getBytes());
      os.writeBytes("\n");
      os.writeBytes("exit\n");
      os.flush();
      //执行命令
      process.waitFor();
      //获取返回结果
      successMsg = new StringBuilder();
      errorMsg = new StringBuilder();
      successResult = new BufferedReader(new InputStreamReader(process.getInputStream()));
      errorResult = new BufferedReader(new InputStreamReader(process.getErrorStream()));
      String s;
      while ((s = successResult.readLine()) != null) {
        successMsg.append(s);
      }
      if (successMsg.toString().contains("Success")) {
        return true;
      }
      while ((s = errorResult.readLine()) != null) {
        errorMsg.append(s);
      }
      Log.d(TAG, successMsg.toString());
      Log.d(TAG, errorMsg.toString());
    } catch (Exception e) {
      Log.e(TAG, e.toString());
    } finally {
      try {
        if (os != null) {
          os.close();
        }
        if (process != null) {
          process.destroy();
        }
        if (successResult != null) {
          successResult.close();
        }
        if (errorResult != null) {
          errorResult.close();
        }
      } catch (Exception e) {
        Log.e(TAG, e.toString());
      }
    }
    return false;
  }
}
