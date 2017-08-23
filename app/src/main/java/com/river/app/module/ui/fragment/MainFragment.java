package com.river.app.module.ui.fragment;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import butterknife.BindView;
import com.river.app.R;
import com.river.app.base.BaseAdapter;
import com.river.app.base.BaseFragment;
import com.river.app.bean.ChannelBean;
import com.river.app.bean.HealthChannel;
import com.river.app.module.contract.HealthChannelContract;
import com.river.app.module.presenter.HealthChannelPresenter;
import com.river.app.widget.HorizontalScrollMenu;
import com.socks.library.KLog;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public class MainFragment extends BaseFragment<HealthChannelPresenter> implements HealthChannelContract.View {
  @BindView(R.id.hsm_container) HorizontalScrollMenu hsm_container;
  private List<String> channelNames = new ArrayList<>();
  private List<Integer> channelId = new ArrayList<>();
  public static MainFragment newInstance() {
    return new MainFragment();
  }

  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }
  @Override protected void initData() {
    KLog.a("huang","start");
    mPresenter.start();
  }

  @Override protected int getLayoutId() {
    return R.layout.fragment_health_main;
  }


  //@Override public void setPresenter(HealthChannelContract.Presenter presenter) {
  //   // mPresenter=presenter;
  //}

  @Override public void updateTab(List<HealthChannel.ChannelBody.ChannelList> channels) {

    for (HealthChannel.ChannelBody.ChannelList channel : channels) {
      channelNames.add(channel.name);
      channelId.add(channel.id);
    }
    hsm_container.setAdapter(new MenuAdapter(getChildFragmentManager(), channelNames,channelId));
  }

  @Override public void updateTabFromDB(List<ChannelBean> channels) {
    for (ChannelBean channel : channels) {
      channelNames.add(channel.getName());
      channelId.add(channel.getId());
    }
    hsm_container.setAdapter(new MenuAdapter(getChildFragmentManager(), channelNames,channelId));
  }

  public class MenuAdapter extends BaseAdapter {
    List<String> channelNames;
    List<Integer> channelId;
    private FragmentManager fm;

    public MenuAdapter(FragmentManager fm, List<String> channelNames,List<Integer> channelId) {
      this.fm = fm;
      this.channelNames = channelNames;
      this.channelId=channelId;
    }

    public FragmentManager getFm() {
      return fm;
    }

    @Override public List<String> getMenuItems() {
      return channelNames;
    }

    @Override public List<Fragment> getContentViews() {
      List<Fragment> fragment = new ArrayList<>();
      for (Integer id : channelId) {
        fragment.add(HealthPagerFragment.newsInstance(id));
      }
      return fragment;
    }

    @Override public void onPageChanged(int position, boolean visitStatus) {
      // notifyDataSetChanged();
    }
  }
}
