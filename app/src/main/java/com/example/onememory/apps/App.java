package com.example.onememory.apps;

import java.io.Serializable;

public class App implements Serializable {
    private int id;
    private String name;
    private int iconId;
    private String description;
    private float money;
    private String bgColor;
    private String textColor;
    private String subTime;
    private String subPeriod;
    private String payMethod;
    private int expired;
    private int added_state;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getMoney() {
        return money;
    }

    public void setMoney(float money) {
        this.money = money;
    }

    public String getBgColor() {
        return bgColor;
    }

    public void setBgColor(String bgColor) {
        this.bgColor = bgColor;
    }

    public String getTextColor() {
        return textColor;
    }

    public void setTextColor(String textColor) {
        this.textColor = textColor;
    }

    public String getSubTime() {
        return subTime;
    }

    public void setSubTime(String subTime) {
        this.subTime = subTime;
    }

    public String getSubPeriod() {
        return subPeriod;
    }

    public void setSubPeriod(String subPeriod) {
        this.subPeriod = subPeriod;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public void setPayMethod(String payMethod) {
        this.payMethod = payMethod;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAdded_state() {
        return added_state;
    }

    public void setAdded_state(int added_state) {
        this.added_state = added_state;
    }
}
