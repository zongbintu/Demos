package com.tu.example.data.datasource;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.NonNull;
import com.tu.example.common.Constants;
import com.tu.example.mainlist.MainActivity;
import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tu enum@foxmail.com
 * @date 16/7/1
 */
public class MainListDataSource implements MainListRepository {
  @Override public List<Map<String, Object>> loadMainList(@NonNull Context context, String prefix) {
    List<Map<String, Object>> myData = new ArrayList<Map<String, Object>>();

    Intent mainIntent = new Intent(Intent.ACTION_MAIN, null);
    mainIntent.addCategory(Constants.CATEGORY_SAMPLE_CODE);
    PackageManager packageManager = context.getPackageManager();
    List<ResolveInfo> list = packageManager.queryIntentActivities(mainIntent, 0);

    if (null == list) return myData;

    String[] prefixPath;
    String prefixWithSlash = prefix;

    if (prefix.equals("")) {
      prefixPath = null;
    } else {
      prefixPath = prefix.split("/");
      prefixWithSlash = prefix + "/";
    }

    int len = list.size();

    Map<String, Boolean> entries = new HashMap<String, Boolean>();

    for (int i = 0; i < len; i++) {
      ResolveInfo info = list.get(i);
      CharSequence labelSeq = info.loadLabel(packageManager);
      String label = labelSeq != null ? labelSeq.toString() : info.activityInfo.name;

      if (prefixWithSlash.length() == 0 || label.startsWith(prefixWithSlash)) {

        String[] labelPath = label.split("/");

        String nextLabel = prefixPath == null ? labelPath[0] : labelPath[prefixPath.length];

        if ((prefixPath != null ? prefixPath.length : 0) == labelPath.length - 1) {
          addItem(myData, nextLabel, activityIntent(info.activityInfo.applicationInfo.packageName,
              info.activityInfo.name));
        } else {
          if (entries.get(nextLabel) == null) {
            addItem(myData, nextLabel,
                browseIntent(context, prefix.equals("") ? nextLabel : prefix + "/" + nextLabel));
            entries.put(nextLabel, true);
          }
        }
      }
    }

    Collections.sort(myData, sDisplayNameComparator);

    return myData;
  }

  private void addItem(List<Map<String, Object>> data, String name, Intent intent) {
    Map<String, Object> temp = new HashMap<String, Object>();
    temp.put("title", name);
    temp.put("intent", intent);
    data.add(temp);
  }

  private final static Comparator<Map<String, Object>> sDisplayNameComparator =
      new Comparator<Map<String, Object>>() {
        private final Collator collator = Collator.getInstance();

        public int compare(Map<String, Object> map1, Map<String, Object> map2) {
          return collator.compare(map1.get("title"), map2.get("title"));
        }
      };

  private Intent activityIntent(String pkg, String componentName) {
    Intent result = new Intent();
    result.setClassName(pkg, componentName);
    return result;
  }

  private Intent browseIntent(Context context, String path) {
    Intent result = new Intent();
    result.setClass(context, MainActivity.class);
    result.putExtra(Constants.PATH, path);
    return result;
  }
}
