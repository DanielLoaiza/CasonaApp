package com.dafelo.co.casona.order_detail.data.entity;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Section {

    @SerializedName("_id")
    @Expose
    private String name;
    @SerializedName("plates")
    @Expose
    private List<Food> food = new ArrayList<>();

    /**
     *
     * @return
     * The name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     * The name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return
     * The plates
     */
    public List<Food> getPlates() {
        return food;
    }

    /**
     *
     * @param food
     * The plates
     */
    public void setFood(List<Food> food) {
        this.food = food;
    }

}