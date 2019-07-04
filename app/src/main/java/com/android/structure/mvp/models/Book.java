package com.android.structure.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.Date;
import java.util.List;

public class Book {

    @SerializedName("title")
    public String title;
    @SerializedName("date")
    public String date;
}
