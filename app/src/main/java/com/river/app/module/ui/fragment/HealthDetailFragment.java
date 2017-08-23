package com.river.app.module.ui.fragment;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.river.app.R;
import com.river.app.base.BaseFragment;
import com.river.app.bean.HealthDetail;
import com.river.app.module.contract.HealthDetailContract;
import com.river.app.module.presenter.HealthDetailPresenter;
import com.river.app.util.StringUtils;

import static com.river.app.R.id.iv_health_detail_photo;
import static com.river.app.R.id.tv_health_detail_body;
import static com.river.app.R.id.tv_health_detail_from;
import static com.river.app.R.id.tv_health_detail_title;

/**
 * Created by Administrator on 2017/6/15.
 */

public class HealthDetailFragment extends BaseFragment<HealthDetailPresenter> implements HealthDetailContract.View{
  @BindView(iv_health_detail_photo) ImageView mImageView;
  @BindView(tv_health_detail_title) TextView mtitle;
  @BindView(tv_health_detail_from) TextView mFrom;
  @BindView(tv_health_detail_body) TextView mBody;
  public static HealthDetailFragment newInstance(String id) {
    HealthDetailFragment healthDetailFragment = new HealthDetailFragment();
    Bundle bundle = new Bundle();
    bundle.putString("id", id);
    healthDetailFragment.setArguments(bundle);
    return healthDetailFragment;
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    mPresenter.requestData(getArguments().getString("id"));
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_detail;
  }

  @Override public void getHealthDetail(HealthDetail detail) {
    if (detail != null) {
      Glide.with(this)
          .load(detail.body.item.img)
          .placeholder(R.drawable.ic_loading)
          .error(R.drawable.ic_fail)
          .diskCacheStrategy(DiskCacheStrategy.SOURCE)
          .into(mImageView);
      Toast.makeText(mActivity, detail.body.item.title, Toast.LENGTH_SHORT).show();
      mFrom.setText(String.valueOf(detail.body.item.ctime));
      mtitle.setText(detail.body.item.title);
      mBody.setText(StringUtils.removeHtmlTag(detail.body.item.content));
    }
  }
}
