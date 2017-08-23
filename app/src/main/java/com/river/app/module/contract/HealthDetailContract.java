package com.river.app.module.contract;

import com.river.app.base.BasePresenter;
import com.river.app.base.BaseView;
import com.river.app.bean.HealthDetail;

/**
 * Created by Administrator on 2017/6/1.
 */

public interface HealthDetailContract {
  interface View extends BaseView {
    void getHealthDetail(HealthDetail detail);
  }
  interface Presenter extends BasePresenter<View> {
    void requestData(String id);
  }
}
