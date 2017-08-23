package com.river.app.bean;

import io.realm.RealmObject;

/**
 * Created by Administrator on 2017/7/12.
 */

public class ChannelBean extends RealmObject{
  private int id;
  private String name;
  public ChannelBean(){}
  public ChannelBean(int id,String name){
    this.id=id;
    this.name=name;
  }
  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }
}
