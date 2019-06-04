package com.tu.example.bug;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.tu.example.R;
import com.tu.widget.stepview.StepIndicatorView;

public class StepFragment extends Fragment {
  private static final String ARG_STEP = "step";
  private static final String ARG_LAYOUT_ID = "layoutId";

  private int step;
  private int layoutId;

  public StepFragment() {
    // Required empty public constructor
  }

  public static StepFragment newInstance(int param1, int layoutId) {
    StepFragment fragment = new StepFragment();
    Bundle args = new Bundle();
    args.putInt(ARG_STEP, param1);
    args.putInt(ARG_LAYOUT_ID, layoutId);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      step = getArguments().getInt(ARG_STEP);
      layoutId = getArguments().getInt(ARG_LAYOUT_ID);
    }
  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    // Inflate the layout for this fragment
    View root = inflater.inflate(layoutId, container, false);

    StepIndicatorView stepIndicatorView = root.findViewById(R.id.step);
    stepIndicatorView.selectStep(step);
    return root;
  }
}
