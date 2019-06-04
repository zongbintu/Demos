package com.tu.widget.stepview;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;

/**
 * @author Tu enum@foxmail.com
 * @version 1.1 2019/6/4
 */
public class StepIndicatorViewX extends StepIndicatorView {

  public StepIndicatorViewX(Context context) {
    super(context);
  }

  public StepIndicatorViewX(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public StepIndicatorViewX(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @Override protected Parcelable onSaveInstanceState() {
    super.onSaveInstanceState();
    Bundle bundle = new Bundle();
    bundle.putInt("currentStep", mCurrentStep);
    bundle.putInt("selectStep", mSelectStep);
    bundle.putStringArray("mStepNames", mStepNames);
    bundle.putIntArray("mStepIcons", mStepIcons);
    return bundle;
  }

  @Override protected void onRestoreInstanceState(Parcelable state) {
    Bundle bundle = (Bundle) state;
    mCurrentStep = bundle.getInt("currentStep");
    mSelectStep = bundle.getInt("mSelectStep");
    mStepNames = bundle.getStringArray("mStepNames");
    mStepIcons = bundle.getIntArray("mStepIcons");
    super.onRestoreInstanceState(bundle);
    reCreateContentView();
  }
}
