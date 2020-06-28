package com.mysql.bean;

import java.util.ArrayList;
import java.util.List;

public class KamenRider {

    String desc;
    List<HeiseiRider> riders = new ArrayList<>();

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public List<HeiseiRider> getRiders() {
        return riders;
    }

    public void setRiders(List<HeiseiRider> riders) {
        this.riders = riders;
    }

    public void addRider(HeiseiRider rider){
        this.riders.add(rider);
    }

}
