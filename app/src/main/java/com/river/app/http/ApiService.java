package com.river.app.http;

import com.river.app.bean.HealthListBean;
import com.river.app.bean.HealthChannel;
import com.river.app.bean.HealthDetail;
import io.reactivex.Observable;
import javax.inject.Singleton;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Administrator on 2016/9/9.
 */
@Singleton
public interface ApiService {

  /**
   * 健康知识分类
   */
  @GET("90-86") Observable<HealthChannel> getNewsChannel(@Query("showapi_appid") int appid,
     @Query("showapi_test_draft") boolean draft, @Query("showapi_timestamp") String timestamp, @Query("showapi_sign") String sign);

  /**
   * 健康知识列表
   */
  @GET("90-87") Observable<HealthListBean> getNewsList(@Query("page") String page,
      @Query("key") String key, @Query("tid") String tid,@Query("showapi_appid") int appid,
      @Query("showapi_test_draft") boolean draft, @Query("showapi_timestamp") String timestamp, @Query("showapi_sign") String sign);
  /**
   * 健康知识详情
   */
  @GET("90-88") Observable<HealthDetail> getHealthDetail(@Query("id") String id,@Query("showapi_appid") int appid,
        @Query("showapi_test_draft") boolean draft, @Query("showapi_timestamp") String timestamp, @Query("showapi_sign") String sign);
}
