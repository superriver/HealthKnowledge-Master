package com.river.app.bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 * Created by Administrator on 2016/8/25.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class HealthChannel {
  @JsonProperty("showapi_res_code") public int code;
  @JsonProperty("showapi_res_error") public String error;
  @JsonProperty("showapi_res_body") public ChannelBody body;
  public static class ChannelBody {
    @JsonProperty("ret_code")
    public int ret_code;
    @JsonProperty("list")
    public List<ChannelList> list;
    public static class ChannelList{
      @JsonProperty("id") public int id;
      @JsonProperty("name") public String name;
    }
  }
}