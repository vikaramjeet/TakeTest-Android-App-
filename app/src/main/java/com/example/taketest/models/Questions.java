package com.example.taketest.models;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Questions implements Serializable {

    @SerializedName("question")
    private List<Question> question = null;

    public List<Question> getQuestion() {
        return question;
    }

    public void setQuestion(List<Question> question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Questions{" +
                "question=" + question +
                '}';
    }
}
