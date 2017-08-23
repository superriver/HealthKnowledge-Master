package com.river.app.module.model.Remote;

import com.river.app.bean.HealthListBean;
import com.river.app.bean.HealthChannel;
import com.river.app.bean.HealthDetail;
import com.river.app.callback.RequestCallBack;
import io.reactivex.disposables.Disposable;

/**
 * Created by Administrator on 2017/4/7.
 */

public interface HealthDataSource {
  Disposable requestHealthChannel(RequestCallBack<HealthChannel> callBack,int appid,boolean draft,String timestamp,String sign);
  Disposable requestHealthList(RequestCallBack<HealthListBean> callBack,String page, String key,
      String tid,int appid, boolean draft, String timestamp, String sign);
  Disposable requestHealthDetail(RequestCallBack<HealthDetail> callBack,String id,int appid, boolean draft, String timestamp, String sign);
}
