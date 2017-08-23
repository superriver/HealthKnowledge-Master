package com.river.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Administrator on 2017/5/31.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthDetail {

  /**
   * showapi_res_code : 0
   * showapi_res_error :
   * showapi_res_body : {"item":{"content":"　　清晨是剃须的最佳时间，这时的皮肤比较放松","ctime":"2015-07-14 08:22:41.000","id":"gsps:434-20-174218","img":"","intro":"清晨是剃须的最佳时间，这时的皮肤比较放松，剃须也可减少被刮伤的几率。忌讳从不同方向刮胡须，否则会形成倒须。 不要在洗澡前剃须...","keywords":"男人,刮胡子,运动,洗澡,出汗,毛孔","media_name":"重庆晚报","stitle":"两个时间最不该刮胡子","summary":"清晨是剃须的最佳时间，","tid":"570","title":"两个时间最不该刮胡子","tname":"保健","wapurl":"http://health.sina.cn/healthcare/2015-07-14/detail-ifxewnih2259265.d.html"},"ret_code":0}
   */
  @JsonProperty("showapi_res_code")
  public int res;
  @JsonProperty("showapi_res_error")
  public String error;
  @JsonProperty("showapi_res_body")
  public ShowapiResBodyBean body;

  public static class ShowapiResBodyBean {
    /**
     * item : {"content":"　　清晨是剃须的最佳时间，这时的皮肤比较放松","ctime":"2015-07-14 08:22:41.000","id":"gsps:434-20-174218","img":"","intro":"清晨是剃须的最佳时间，这时的皮肤比较放松，剃须也可减少被刮伤的几率。忌讳从不同方向刮胡须，否则会形成倒须。 不要在洗澡前剃须...","keywords":"男人,刮胡子,运动,洗澡,出汗,毛孔","media_name":"重庆晚报","stitle":"两个时间最不该刮胡子","summary":"清晨是剃须的最佳时间，","tid":"570","title":"两个时间最不该刮胡子","tname":"保健","wapurl":"http://health.sina.cn/healthcare/2015-07-14/detail-ifxewnih2259265.d.html"}
     * ret_code : 0
     */
    @JsonProperty("item")
    public ItemBean item;
    @JsonProperty("ret_code")
    public int ret_code;
    public static class ItemBean {
      /**
       * content : 　　清晨是剃须的最佳时间，这时的皮肤比较放松
       * ctime : 2015-07-14 08:22:41.000
       * id : gsps:434-20-174218
       * img :
       * intro : 清晨是剃须的最佳时间，这时的皮肤比较放松，剃须也可减少被刮伤的几率。忌讳从不同方向刮胡须，否则会形成倒须。 不要在洗澡前剃须...
       * keywords : 男人,刮胡子,运动,洗澡,出汗,毛孔
       * media_name : 重庆晚报
       * stitle : 两个时间最不该刮胡子
       * summary : 清晨是剃须的最佳时间，
       * tid : 570
       * title : 两个时间最不该刮胡子
       * tname : 保健
       * wapurl : http://health.sina.cn/healthcare/2015-07-14/detail-ifxewnih2259265.d.html
       */
      @JsonProperty("content")
      public String content;
      @JsonProperty("ctime")
      public String ctime;
      @JsonProperty("id")
      public String id;
      @JsonProperty("img")
      public String img;
      @JsonProperty("intro")
      public String intro;
      @JsonProperty("keywords")
      public String keywords;
      @JsonProperty("media_name")
      public String media_name;
      @JsonProperty("stitle")
      public String stitle;
      @JsonProperty("summary")
      public String summary;
      @JsonProperty("tid")
      public String tid;
      @JsonProperty("title")
      public String title;
      @JsonProperty("tname")
      public String tname;
      @JsonProperty("wapurl")
      public String wapurl;

    }
  }
}
