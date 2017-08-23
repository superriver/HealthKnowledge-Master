package com.river.app.module.presenter;

import android.content.Context;
import com.river.app.bean.HealthListBean;
import com.river.app.callback.RequestCallBack;
import com.river.app.constant.DataType;
import com.river.app.http.Api;
import com.river.app.module.contract.HealthListContract;
import com.river.app.module.model.Remote.HealthRemoteDataSource;
import com.river.app.util.NetUtil;
import com.socks.library.KLog;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/7.
 */

public class HealthListPresenter implements HealthListContract.Presenter {

  private HealthRemoteDataSource mDataSource;
  private HealthListContract.View mView;
  private Disposable mDisposable;
  private int maxResult = 20;
  private int page = 1;
  private int mId;
  private boolean isRefresh = true;
  private boolean mFirstLoad = true;
  @Inject Context mContext;
  @Inject public HealthListPresenter(HealthRemoteDataSource dataSource) {
    mDataSource = dataSource;
  }

  //@Inject void setupListeners() {
  //  mView.setPresenter(this);
  //}

  @Override public void requestList(int id) {
    if(!NetUtil.isConnected(mContext)){
      mView.getHealthList(null, "网络未连接？",isRefresh ? DataType.TYPE_REFRESH_FAILED : DataType.TYPE_LOADMORE_FAILED);
      return;
    }
    mId = id;
    mDisposable = mDataSource.requestHealthList(new RequestCallBack<HealthListBean>() {
      @Override public void requestBefore() {
        mView.showProgress();
      }

      @Override public void requestError(String msg) {
        mView.getHealthList(null, msg,isRefresh ? DataType.TYPE_REFRESH_FAILED : DataType.TYPE_LOADMORE_FAILED);
        mView.hideProgress();
        if(mDisposable.isDisposed()){
          mDisposable.dispose();
        }
      }

      @Override public void requestComplete() {
        mView.hideProgress();
        if(mDisposable.isDisposed()){
          mDisposable.dispose();
        }
      }

      @Override public void requestSuccess(HealthListBean data) {

        mView.getHealthList(data.body.pagebean.contentlist, null,
            isRefresh ? DataType.TYPE_REFRESH_SUCCESS : DataType.TYPE_LOADMORE_SUCCESS);
      }
    }, String.valueOf(page), null,String.valueOf(mId), Api.SHOWAPI_APPID,Api.SHOWAPI_TEST_DRAFT,null,Api.SHOWAPI_SIGN);
  }

  @Override public void refreshData() {
    isRefresh = true;
    page = 1;
    maxResult = 20;
    requestList(mId);
  }

  @Override public void loadMoreData() {
    KLog.d("huang","loadMoreData");
    isRefresh = false;
    page++;
    maxResult = 20;
    requestList(mId);
  }

  @Override public void start() {

  }

  @Override public void attachView(HealthListContract.View view) {
    mView=view;
  }

  @Override public void detachView() {

  }
}
