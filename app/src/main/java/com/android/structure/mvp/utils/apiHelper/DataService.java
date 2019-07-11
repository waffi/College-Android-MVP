package com.android.structure.mvp.utils.apiHelper;

import com.android.structure.mvp.models.Collection;
import com.android.structure.mvp.models.Search;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface DataService {

    @GET("/v2/items.dc.json")
    Call<Search> getItems1(@Query("limit") int limit);

    @GET("/v2/items.dc.json")
    Call<Search> getItems1(@Query("setName") String collection, @Query("limit") int limit);

    @GET("/v2/items.dc.json")
    Call<Search> getItems2(@Query("title") String title, @Query("limit") int limit);

    @GET("/v2/items.dc.json")
    Call<Search> getItems2(@Query("title") String title, @Query("setName") String collection, @Query("limit") int limit);

    @GET("/v2/collections.json?limit=100")
    Call<List<Collection>> getCollections();
}
