package com.river.app.module.ui.fragment;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.ProgressBar;
import butterknife.BindView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.river.app.R;
import com.river.app.base.BaseFragment;
import com.river.app.bean.HealthListBean;
import com.river.app.callback.OnItemClickAdapter;
import com.river.app.callback.OnLoadMoreListener;
import com.river.app.constant.DataType;
import com.river.app.module.contract.HealthListContract;
import com.river.app.module.presenter.HealthListPresenter;
import com.river.app.module.ui.activity.HealthDetailActivity;
import com.river.app.module.ui.adapter.BaseRecyclerAdapter;
import com.river.app.module.ui.adapter.BaseRecyclerViewHolder;
import java.util.List;

/**
 * Created by Administrator on 2017/4/6.
 */

public class HealthPagerFragment extends BaseFragment<HealthListPresenter> implements HealthListContract.View {
  @BindView(R.id.content) RecyclerView mRecyclerView;
  @BindView(R.id.refresh_layout) SwipeRefreshLayout mRefreshLayout;
  @BindView(R.id.progress) ProgressBar mProgressBar;
  private BaseRecyclerAdapter<HealthListBean.HealthList.PageBean.ContentBean> mAdapter;
  private int id;
  @Override protected void initInject() {
    getFragmentComponent().inject(this);
  }

  @Override protected void initData() {
    mPresenter.requestList(id);
  }

  @Override protected int getLayoutId() {
    return R.layout.content_view;
  }

  public static HealthPagerFragment newsInstance(int id) {
    HealthPagerFragment mainFragment = new HealthPagerFragment();
    Bundle bundle = new Bundle();
    bundle.putInt("channelId", id);
    mainFragment.setArguments(bundle);
    return mainFragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      id = getArguments().getInt("channelId");
    }
  }
  //@Override public void setPresenter(HealthListContract.Presenter presenter) {
  //  //mPresenter=presenter;
  //}

  @Override public void showProgress() {
    mProgressBar.setVisibility(View.VISIBLE);
  }

  @Override public void hideProgress() {
    mProgressBar.setVisibility(View.GONE);
  }

  @Override public void getHealthList(List<HealthListBean.HealthList.PageBean.ContentBean> contents,String errorMsg,int type) {
    if(mAdapter==null){
      initDataList(contents);
    }
    switch (type){
      case DataType.TYPE_REFRESH_SUCCESS:
        mRefreshLayout.setRefreshing(false);
        mAdapter.setData(contents);
        mAdapter.enableLoadMore(true);
        break;
      case DataType.TYPE_LOADMORE_SUCCESS:
        mAdapter.loadMoreSuccess();
        if (contents == null || contents.size() == 0) {
         // mAdapter.enableLoadMore(null);
          //toast("全部加载完毕");
          return;
        }
        mAdapter.addMoreData(contents);
        break;
      case DataType.TYPE_REFRESH_FAILED:
        mAdapter.enableLoadMore(false);
        mAdapter.showEmptyView(true,errorMsg);
        mAdapter.notifyDataSetChanged();
        break;
      case DataType.TYPE_LOADMORE_FAILED:
        mAdapter.loadMoreFailed(errorMsg);
        break;

    }
    //mRefreshLayout.setRefreshing(true);

  }

  public void initDataList(final List<HealthListBean.HealthList.PageBean.ContentBean> contents) {
    mAdapter = new BaseRecyclerAdapter<HealthListBean.HealthList.PageBean.ContentBean>(getActivity(), contents) {
      @Override public int getItemLayoutId(int viewType) {
        return R.layout.item_knowledge;
      }

      //@Override public void bindData(BaseRecyclerViewHolder holder, int position,
      //    HealthListBean.HealthList.PageBean.ContentBean item) {
      //
      //}
      //@Override public int getLayoutId() {
      //  return R.layout.item_knowledge;
      //}


      @Override public void bindData(BaseRecyclerViewHolder holder, int position,  HealthListBean.HealthList.PageBean.ContentBean  data) {
        if (data.img == null) {
          holder.getImageView(R.id.iv_health_summary_photo).setImageResource(R.drawable.ic_loading);
        } else {
          Glide.with(getActivity())
              .load(data.img)
              .placeholder(R.drawable.ic_loading)
              .error(R.drawable.ic_fail)
              .diskCacheStrategy(DiskCacheStrategy.SOURCE)
              .into(holder.getImageView(R.id.iv_health_summary_photo));
        }
        holder.getTextView(R.id.tv_health_summary_title).setText(data.title);
        holder.getTextView(R.id.tv_health_summary_digest).setText(data.intro);
        holder.getTextView(R.id.tv_health_summary_ptime).setText(String.valueOf(data.ctime));
      }
    };
    LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
    linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
    mRecyclerView.setLayoutManager(linearLayoutManager);
    mRecyclerView.setAdapter(mAdapter);
    mAdapter.setOnItemClickListener(new OnItemClickAdapter() {
      @Override public void onItemClick(View view, int position) {
        view = view.findViewById(R.id.iv_health_summary_photo);
        if (!TextUtils.isEmpty(mAdapter.getData().get(position).media_name)) {
          Intent intent = new Intent(getActivity(), HealthDetailActivity.class);
          intent.putExtra("postId", mAdapter.getData().get(position).id);
          if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(getActivity(),
                view.findViewById(R.id.iv_health_summary_photo), "photos");
            getActivity().startActivity(intent, options.toBundle());
          } else {
            ActivityOptions options =
                ActivityOptions.makeScaleUpAnimation(view, view.getWidth(), view.getHeight(), 0, 0);
            getActivity().startActivity(intent, options.toBundle());
          }
        }
      }
    });


    mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
          mPresenter.refreshData();
      }
    });
    mAdapter.setOnLoadMoreListener(10,new OnLoadMoreListener() {
      @Override public void loadMore() {
        mPresenter.loadMoreData();
      }
    });
  }
}
