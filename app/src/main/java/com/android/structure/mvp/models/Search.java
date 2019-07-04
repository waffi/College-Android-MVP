package com.android.structure.mvp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Search {

    @SerializedName("pagination")
    public Pagination pagination;
    @SerializedName("items")
    public Result items;

    public class Result {

        @SerializedName("dc")
        public List<Book> dc;
    }
}


