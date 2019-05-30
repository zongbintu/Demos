package com.tu.example.mainlist;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import com.tu.example.common.Constants;
import com.tu.example.data.datasource.MainListRepository;
import java.util.List;
import java.util.Map;

/**
 * @author Tu enum@foxmail.com
 * @date 16/7/1
 */
public class MainPresenter implements MainContract.Presenter {
  MainListRepository mainListRepository;
  MainContract.View mainView;

  public MainPresenter(@NonNull MainListRepository mainListRepository,
      @NonNull MainContract.View mainView) {
    this.mainListRepository = mainListRepository;
    this.mainView = mainView;
  }

  @Override public void loadActivities(Context context, String prefix) {
    List<Map<String, Object>> activities = mainListRepository.loadMainList(context, prefix);
    mainView.showActivities(activities);
  }

  @Override public void toActivity(Context context, Intent intent) {
    intent.addCategory(Constants.CATEGORY_SAMPLE_CODE);
    context.startActivity(intent);
  }

  @Override public void start() {
  }
}
