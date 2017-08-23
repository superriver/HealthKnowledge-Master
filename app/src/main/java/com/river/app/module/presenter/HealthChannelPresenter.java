package com.river.app.module.presenter;

import android.content.Context;
import com.river.app.bean.ChannelManagerBean;
import com.river.app.bean.HealthChannel;
import com.river.app.callback.RequestCallBack;
import com.river.app.http.Api;
import com.river.app.module.contract.HealthChannelContract;
import com.river.app.module.model.Remote.HealthRemoteDataSource;
import com.river.app.module.model.db.DBHelper;
import com.socks.library.KLog;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

/**
 * Created by Administrator on 2017/4/7.
 */

public class HealthChannelPresenter implements HealthChannelContract.Presenter {
  private HealthRemoteDataSource mDataSource;
  private HealthChannelContract.View mView;
  private Disposable mDisposable;
  private boolean isRefresh = true;
  private DBHelper mDBHelper;
  @Inject Context mContext;
  @Inject public HealthChannelPresenter(HealthRemoteDataSource dataSource,DBHelper dbHelper) {
    mDBHelper = dbHelper;
    mDataSource = dataSource;
  }
  //@Inject
  //void setupListeners() {
  //  mView.setPresenter(this);
  //}
  @Override public void loadTasks(boolean forceUpdate) {

   ChannelManagerBean channel = mDBHelper.getChannelList();
    if(channel!=null){
      //List<HealthChannel.Loreclass> list=channel.getChannels();
      mView.updateTabFromDB(channel.getChannels());
    }else {
      KLog.d("huang","-->");
      mDisposable = mDataSource.requestHealthChannel(new RequestCallBack<HealthChannel>() {
        @Override public void requestBefore() {

        }
        @Override public void requestError(String msg) {
          if(mDisposable.isDisposed()){
            mDisposable.dispose();
          }
        }

        @Override public void requestComplete() {
          if(mDisposable.isDisposed()){
            mDisposable.dispose();
          }

        }
        @Override public void requestSuccess(HealthChannel data) {
          mView.updateTab(data.body.list);
          mDBHelper.saveChannelList(data);
        }
      }, Api.SHOWAPI_APPID,Api.SHOWAPI_TEST_DRAFT,null,Api.SHOWAPI_SIGN);
    }
    //mRepository.getDatas(new DataSource.LoadDataCallback() {
    //  @Override public void onDataLoaded(List datas) {
    //    mView.updateTab(datas);
    //  }
    //
    //  @Override public void onDataNotAvailable() {
    //
    //  }
    //});

  }

  @Override public void start() {
    loadTasks(false);
  }

  @Override public void attachView(HealthChannelContract.View view) {
    mView = view;
  }

  @Override public void detachView() {

  }
}
