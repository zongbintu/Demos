package com.tu.example.mainlist;

import android.content.Context;
import android.content.Intent;
import com.tu.example.BasePresenter;
import com.tu.example.BaseView;
import java.util.List;
import java.util.Map;

/**
 * @auther Tu enum@foxmail.com
 * @date 16/7/1
 */
public interface MainContract {
  interface View extends BaseView<Presenter> {
    void showActivities(List<Map<String, Object>> activities);
  }

  interface Presenter extends BasePresenter {
    void loadActivities(Context context, String prefix);

    void toActivity(Context context, Intent intent);
  }
}
