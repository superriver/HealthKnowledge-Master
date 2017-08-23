package com.river.app.module.ui.activity;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.WindowManager;
import com.river.app.R;
import com.river.app.base.BaseActivity;
import com.river.app.module.ui.fragment.HealthDetailFragment;
import com.river.app.util.ActivityUtils;
import com.river.app.util.ViewUtil;

/**
 * Created by Administrator on 2017/5/31.
 */

public class HealthDetailActivity extends BaseActivity {

  private HealthDetailFragment mHealthDetailFragment;
  @Override protected void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setToolBar("健康详情");

    if(Build.VERSION.SDK_INT==Build.VERSION_CODES.KITKAT){
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_IN_SCREEN);
      getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
      ViewUtil.showStatusBar(this);
    }
    String id = getIntent().getStringExtra("postId");
    mHealthDetailFragment =
        (HealthDetailFragment) getSupportFragmentManager().findFragmentById(R.id.contentFrame);
    if (mHealthDetailFragment == null) {
      mHealthDetailFragment = HealthDetailFragment.newInstance(id);
      ActivityUtils.addFragmentToActivity(getSupportFragmentManager(), mHealthDetailFragment,
          R.id.contentFrame);
    }
  }
  @Override protected int getLayoutId() {
    return R.layout.activity_health_detail;
  }
  //@Override public void setPresenter(HealthDetailContract.Presenter presenter) {
  //
  //}


}
