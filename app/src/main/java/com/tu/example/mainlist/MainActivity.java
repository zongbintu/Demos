package com.tu.example.mainlist;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import com.tu.example.common.Constants;
import com.tu.example.data.datasource.MainListDataSource;
import com.tu.example.utils.Preconditions;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity
    implements AdapterView.OnItemClickListener, MainContract.View {
  private ListView listView;
  MainContract.Presenter mPresenter;

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(android.R.layout.list_content);
    listView = findViewById(android.R.id.list);
    Intent intent = getIntent();
    String path = intent.getStringExtra(Constants.PATH);

    if (path == null) {
      path = "";
    }

    setPresenter(new MainPresenter(new MainListDataSource(), this));

    mPresenter.loadActivities(this, path);

    listView.setTextFilterEnabled(true);
    listView.setOnItemClickListener(this);
  }

  @Override public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
    Map<String, Object> map = (Map<String, Object>) adapterView.getItemAtPosition(position);
    mPresenter.toActivity(this, new Intent((Intent) map.get("intent")));
  }

  @Override public void showActivities(List<Map<String, Object>> activities) {
    listView.setAdapter(new SimpleAdapter(this, activities, android.R.layout.simple_list_item_1,
        new String[] { "title" }, new int[] { android.R.id.text1 }));
  }

  @Override public void setPresenter(MainContract.Presenter presenter) {
    mPresenter = Preconditions.checkNotNull(presenter);
  }
}
