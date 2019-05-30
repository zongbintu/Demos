package com.tu.example.data.datasource;

import android.content.Context;
import android.support.annotation.NonNull;
import java.util.List;
import java.util.Map;

/**
 * @author Tu enum@foxmail.com
 * @date 16/7/1
 */
public interface MainListRepository {
  List<Map<String, Object>> loadMainList(@NonNull Context context, String prefix);
}
