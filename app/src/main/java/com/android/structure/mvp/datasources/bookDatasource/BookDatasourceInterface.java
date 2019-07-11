package com.android.structure.mvp.datasources.bookDatasource;

import com.android.structure.mvp.models.Collection;
import com.android.structure.mvp.utils.asyncTask.DataCallback;
import com.android.structure.mvp.models.Book;

import java.util.List;

public interface BookDatasourceInterface {

    void getBookList(DataCallback<List<Book>> callback, String title, String collection, int limit);

    void getCollectionList(DataCallback<List<Collection>> callback);
}
