package com.river.app.module.contract;

import com.river.app.base.BasePresenter;
import com.river.app.base.BaseView;
import com.river.app.bean.HealthListBean;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public interface HealthListContract {
  interface View extends BaseView {
    void getHealthList(List<HealthListBean.HealthList.PageBean.ContentBean> contents,String msg,int type);
  }
  interface Presenter extends BasePresenter<View>{
    void requestList(int id);
    void refreshData();
    void loadMoreData();
  }
}
