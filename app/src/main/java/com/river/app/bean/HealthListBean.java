package com.river.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true) public class HealthListBean {
  @JsonProperty("showapi_res_code") public int code;
  @JsonProperty("showapi_res_error") public String error;
  @JsonProperty("showapi_res_body") public HealthList body;

  public static class HealthList {
    @JsonProperty("ret_code") public int ret_code;
    @JsonProperty("pagebean") public PageBean pagebean;

    public static class PageBean {
      @JsonProperty("allNum") public int allNum;
      @JsonProperty("allPages") public int allPages;
      @JsonProperty("contentlist") public List<ContentBean> contentlist;
      @JsonProperty("currentPage") public int currentPage;
      @JsonProperty("maxResult") public int maxResult;

      public static class ContentBean {
        @JsonProperty("id") public String id;//资讯id
        @JsonProperty("title") public String title;//资讯标题
        @JsonProperty("intro") public String intro;//分类
        @JsonProperty("img") public String img;//图片
        @JsonProperty("media_name") public String media_name;//描述
        @JsonProperty("keywords") public String keywords;//关键字
        @JsonProperty("stitle") public String stitle;//关键字
        @JsonProperty("summary") public String summary;//访问次数
        @JsonProperty("tid") public String tid;//收藏数
        @JsonProperty("tname") public String tname;//评论读数
        @JsonProperty("url") public String url;
        @JsonProperty("wapurl") public String wapurl;
        @JsonProperty("ctime") public String ctime;
        @JsonProperty("images") public List<ImageBean> images;

        public static class ImageBean {
          @JsonProperty("w")  public  String w;
          @JsonProperty("t") public String t;
          @JsonProperty("u") public String u;
          @JsonProperty("h") public String h;
        }
      }
    }
  }
}
