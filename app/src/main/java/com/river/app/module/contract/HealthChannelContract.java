package com.river.app.module.contract;

import com.river.app.base.BasePresenter;
import com.river.app.base.BaseView;
import com.river.app.bean.ChannelBean;
import com.river.app.bean.HealthChannel;
import java.util.List;

/**
 * Created by Administrator on 2017/5/18.
 */

public interface HealthChannelContract {
  interface View extends BaseView {
    void updateTab(List<HealthChannel.ChannelBody.ChannelList> channels);
    void updateTabFromDB(List<ChannelBean>  channels);
  }
  interface Presenter extends BasePresenter<View>{
    void loadTasks(boolean forceUpdate);
  }
}
