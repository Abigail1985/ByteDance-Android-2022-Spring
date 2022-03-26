package com.bytedance.jstu.homework.homework5.api;

import java.util.List;

public class Webtranslation {

    private String key;
    private List<Trans> trans;

    public void setKey(String key) {
        this.key = key;
    }

    public String getKey() {
        return key;
    }

    public void setTrans(List<Trans> trans) {
        this.trans = trans;
    }

    public List<Trans> getTrans() {
        return trans;
    }

}
