package com.river.app.http;

import com.river.app.base.BaseSchedulerTransformer;
import com.river.app.bean.HealthListBean;
import com.river.app.bean.HealthChannel;
import com.river.app.bean.HealthDetail;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by Administrator on 2017/4/10.
 */

@Singleton public class RetrofitManager {

  private ApiService mService;
  // private static SparseArray<RetrofitManager> mRMInstance = new SparseArray<>(HostType.TYPE_COUNT);

  /**
   * 健康频道
   */
  @Inject public RetrofitManager(ApiService service) {
    mService = service;
  }

  public Observable<HealthChannel> getHealthChannel( int appid,
      boolean draft, String timestamp, String sign) {
    return mService.getNewsChannel(appid,draft,timestamp,sign).compose(new BaseSchedulerTransformer<HealthChannel>());
  }

  /**
   * 健康知识列表
   */
  public Observable<HealthListBean> getHealthList(String page, String rows, String tid,int appid,
      boolean draft, String timestamp, String sign) {
    return mService.getNewsList(page, rows, tid,appid,draft,timestamp,sign).
        compose(new BaseSchedulerTransformer<HealthListBean>());
  }

  /**
   * 健康知识详情
   */
  public Observable<HealthDetail> getHealthDetail(String id,int appid,
      boolean draft, String timestamp, String sign) {
    return mService.getHealthDetail(id,appid,draft,timestamp,sign).
        compose(new BaseSchedulerTransformer<HealthDetail>());
  }
}
