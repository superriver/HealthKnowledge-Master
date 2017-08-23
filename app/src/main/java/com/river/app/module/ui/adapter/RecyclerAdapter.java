package com.river.app.module.ui.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.river.app.R;
import com.river.app.base.BaseViewHolder;
import com.river.app.callback.OnItemClickListener;
import com.river.app.callback.OnLoadMoreListener;
import com.socks.library.KLog;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/14.
 */

public abstract class RecyclerAdapter<T> extends RecyclerView.Adapter<BaseViewHolder> {
  private Context mContext;
  public static final int TYPE_HEADER = 1;
  public static final int TYPE_ITEM = 2;
  public static final int TYPE_MORE = 3;
  public static final int TYPE_EMPTY = 4;
  private static final int TYPE_MORE_FAIL = 5;

  private LayoutInflater mInflater;

  private List<T> mDatas;
  private OnItemClickListener mItemClickListener;
  private OnLoadMoreListener mOnLoadMoreListener;
  private RecyclerView.LayoutManager mLayoutManager;
  private boolean mEnableLoadMore;
  private boolean mShowLoadMoreView;

  protected RecyclerAdapter(Context context, List<T> data) {
    this(context, data, null);
  }

  private RecyclerAdapter(Context context, List<T> datas,
      RecyclerView.LayoutManager layoutManager) {
    mContext = context;
    mDatas = datas == null ? new ArrayList<T>() : datas;
    mInflater = LayoutInflater.from(mContext);
    mLayoutManager = layoutManager;
  }

  public List<T> getDatas() {
    return mDatas;
  }

  public void setItemClickListener(OnItemClickListener itemClickListener) {
    mItemClickListener = itemClickListener;
  }

  public void setLoadMoreListener(OnLoadMoreListener onLoadMoreListener) {
    mOnLoadMoreListener = onLoadMoreListener;
    mShowLoadMoreView = false;
    mEnableLoadMore = true;
  }

  @Override public BaseViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == TYPE_MORE) {
      KLog.d("huang","TYPE_MORE-");
      return new BaseViewHolder(mInflater.inflate(R.layout.load_more_layout, parent, false),
          mContext);
    }else {
      KLog.d("huang","onCreateViewHolder-");
      final BaseViewHolder baseViewHolder =
          new BaseViewHolder(mInflater.inflate(getLayoutId(), parent, false), mContext);
      if (mItemClickListener != null) {
        baseViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            if (baseViewHolder.getLayoutPosition() != RecyclerView.NO_POSITION) {
              mItemClickListener.onItemClick(v, baseViewHolder.getLayoutPosition());
            }
          }
        });
      }
      return baseViewHolder;
    }
  }

  private void fullSpan(BaseViewHolder holder, final int type) {
    //  KLog.d("TAG","fullSpan-"+mShowLoadMoreView);
    if (mLayoutManager != null) {
      if (mLayoutManager instanceof StaggeredGridLayoutManager) {
        if (((StaggeredGridLayoutManager) mLayoutManager).getSpanCount() != 1) {
          StaggeredGridLayoutManager.LayoutParams params =
              (StaggeredGridLayoutManager.LayoutParams) holder.itemView.getLayoutParams();
          params.setFullSpan(true);
        }
      } else if (mLayoutManager instanceof GridLayoutManager) {
        final GridLayoutManager gridLayoutManager = (GridLayoutManager) mLayoutManager;
        final GridLayoutManager.SpanSizeLookup oldSizeLookup =
            gridLayoutManager.getSpanSizeLookup();
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
          @Override public int getSpanSize(int position) {
            if (getItemViewType(position) == type) {
              return gridLayoutManager.getSpanCount();
            }
            if (oldSizeLookup != null) {
              return oldSizeLookup.getSpanSize(position);
            }
            return 1;
          }
        });
      }
    }
  }

  @Override public int getItemViewType(int position) {
    if (mOnLoadMoreListener != null && position == getItemCount() - 1) {
      return TYPE_MORE;
    }
    return 0;
  }

  @Override public void onBindViewHolder(BaseViewHolder holder, int position) {
    if (getItemId(position) == TYPE_MORE) {
      fullSpan(holder, TYPE_MORE);
    } else {
      KLog.d("huang","getItemCount"+mDatas.get(position));
      bindData(holder, position, mDatas.get(position));
    }
    if (mOnLoadMoreListener != null && mEnableLoadMore &&!mShowLoadMoreView&& position == getItemCount() - 1) {
      mShowLoadMoreView = true;
      holder.itemView.postDelayed(new Runnable() {
        @Override public void run() {
          mOnLoadMoreListener.loadMore();
          notifyItemInserted(getItemCount());
        }
      }, 300);
    }
  }

  @Override public int getItemCount() {
    int i = mOnLoadMoreListener == null? 0 : (mEnableLoadMore&&mShowLoadMoreView)?1:0;
    return mDatas == null ? 0 : mDatas.size() + i;
  }

  public abstract int getLayoutId();

  public abstract void bindData(BaseViewHolder holder, int position, T datas);

  public void setData(List<T> datas) {
    mDatas = datas;
    notifyDataSetChanged();
  }

  public void loadMoreSuccess() {
    notifyItemRemoved(getItemCount());
  }

  public void addMoreData(List<T> datas) {
    int startPos = mDatas.size();
    mDatas.addAll(datas);
    notifyItemRangeChanged(startPos, datas.size());
  }
  /**
   * 设置是否开启底部加载
   *
   * @param enableLoadMore true为开启；false为关闭；null为已经全部加载完毕的关闭
   */
  public void enableLoadMore(Boolean enableLoadMore) {
    mEnableLoadMore = enableLoadMore;
  }
}
