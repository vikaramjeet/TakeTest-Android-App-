package com.example.taketest.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class StudyData {

    @SerializedName("study")
    private List<Study> study = null;

    public List<Study> getStudy() {
        return study;
    }

    public void setStudy(List<Study> study) {
        this.study = study;
    }
}
