package com.river.app.module.model.Remote;

import com.river.app.base.BaseSubscriber;
import com.river.app.bean.HealthListBean;
import com.river.app.bean.HealthChannel;
import com.river.app.bean.HealthDetail;
import com.river.app.callback.RequestCallBack;
import com.river.app.http.RetrofitManager;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/7.
 */

public class HealthRemoteDataSource implements HealthDataSource {
  @Inject RetrofitManager mRetrofitManager;

  @Inject
   public HealthRemoteDataSource() {
  }


  @Override
  public Disposable requestHealthChannel(RequestCallBack<HealthChannel> callBack, int appid,
      boolean draft, String timestamp, String sign) {
    return mRetrofitManager.getHealthChannel(appid,draft,timestamp,sign).subscribeWith(new BaseSubscriber<>(callBack));
  }

  @Override
  public Disposable requestHealthList(RequestCallBack<HealthListBean> callBack, String page, String key,
      String tid,int appid, boolean draft, String timestamp, String sign) {
    return mRetrofitManager.getHealthList(page, key, tid,appid,draft,timestamp,sign)
        .subscribeWith(new BaseSubscriber<>(callBack));
  }

  @Override public Disposable requestHealthDetail(RequestCallBack<HealthDetail> callBack, String id,int appid, boolean draft, String timestamp, String sign) {
    return mRetrofitManager.getHealthDetail(id,appid,draft,timestamp,sign).subscribeWith(new BaseSubscriber<>(callBack));
  }
}
