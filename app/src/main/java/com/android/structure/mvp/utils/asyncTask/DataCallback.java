package com.android.structure.mvp.utils.asyncTask;

import com.android.structure.mvp.datasources.DatasourceError;

public interface DataCallback<T> {

    void onDataLoaded(T data);

    void onDataError(DatasourceError error);
}
