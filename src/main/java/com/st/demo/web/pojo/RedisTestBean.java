package com.st.demo.web.pojo;

import java.io.Serializable;

/**
 * Created by xuewei on 2017/9/30.
 */
public class RedisTestBean implements Serializable{

    public RedisTestBean(String str){

    }

    String name;
    byte old;
    short seliry;
    String[] manbers;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public byte getOld() {
        return old;
    }

    public void setOld(byte old) {
        this.old = old;
    }

    public short getSeliry() {
        return seliry;
    }

    public void setSeliry(short seliry) {
        this.seliry = seliry;
    }

    public String[] getManbers() {
        return manbers;
    }

    public void setManbers(String[] manbers) {
        this.manbers = manbers;
    }
}
