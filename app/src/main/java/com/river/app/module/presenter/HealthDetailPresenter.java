package com.river.app.module.presenter;

import android.content.Context;
import com.river.app.bean.HealthDetail;
import com.river.app.callback.RequestCallBack;
import com.river.app.http.Api;
import com.river.app.module.contract.HealthDetailContract;
import com.river.app.module.model.Remote.HealthRemoteDataSource;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Administrator on 2017/6/1.
 */

public class HealthDetailPresenter implements HealthDetailContract.Presenter{
  private HealthRemoteDataSource mDataSource;
  private HealthDetailContract.View mView;
  private Disposable mDisposable;
  private Context mContext;
  @Inject
  public HealthDetailPresenter(HealthRemoteDataSource dataSource){
    mDataSource = dataSource;
  }

  @Override public void start() {

  }

  @Override public void attachView(HealthDetailContract.View view) {
    mView=view;
  }

  @Override public void detachView() {

  }

  @Override public void requestData(String id) {
    mDisposable = mDataSource.requestHealthDetail(new RequestCallBack<HealthDetail>() {
      @Override public void requestBefore() {
      }
      @Override public void requestError(String msg) {
        mView.hideProgress();
        //if(mDisposable.isDisposed()){
        //  mDisposable.dispose();
        //}
      }

      @Override public void requestComplete() {
        mView.hideProgress();
        //if(mDisposable.isDisposed()){
        //  mDisposable.dispose();
        //}
      }

      @Override public void requestSuccess(HealthDetail data) {
        mView.getHealthDetail(data);
      }
    },id, Api.SHOWAPI_APPID,Api.SHOWAPI_TEST_DRAFT,null,Api.SHOWAPI_SIGN);
  }
}
