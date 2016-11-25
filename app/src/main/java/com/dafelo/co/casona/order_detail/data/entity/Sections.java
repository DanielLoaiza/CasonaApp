package com.dafelo.co.casona.order_detail.data.entity;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class Sections {

    @SerializedName("sections")
    @Expose
    private List<Section> sections = new ArrayList<>();

    /**
     *
     * @return
     * The sections
     */
    public List<Section> getSections() {
        return sections;
    }

    /**
     *
     * @param sections
     * The sections
     */
    public void setSections(List<Section> sections) {
        this.sections = sections;
    }

}
