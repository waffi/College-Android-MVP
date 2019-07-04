package com.android.structure.mvp.datasources.bookDatasource;

import com.android.structure.mvp.models.Collection;
import com.android.structure.mvp.models.Search;
import com.android.structure.mvp.utils.apiHelper.ApiClientInstance;
import com.android.structure.mvp.utils.apiHelper.DataService;
import com.android.structure.mvp.utils.asyncTask.DataAsyncTask;
import com.android.structure.mvp.utils.asyncTask.DataCallback;
import com.android.structure.mvp.models.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BookDatasource implements BookDatasourceInterface {

    @Override
    public void getBookList(final DataCallback<List<Book>> callback, int limit) {
        DataService service = ApiClientInstance.getRetrofitInstance().create(DataService.class);

        Call<Search> call = service.getItems(limit);
        call.enqueue(new Callback<Search>() {
            @Override
            public void onResponse(Call<Search> call, Response<Search> response) {
                callback.onDataLoaded(response.body().items.dc);
                System.out.println(response.body().items.dc.size());
            }

            @Override
            public void onFailure(Call<Search> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
