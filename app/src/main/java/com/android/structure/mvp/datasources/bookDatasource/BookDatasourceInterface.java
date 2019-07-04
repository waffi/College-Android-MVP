package com.android.structure.mvp.datasources.bookDatasource;

import com.android.structure.mvp.handlers.asyncTask.DataCallback;
import com.android.structure.mvp.models.book.Book;

import java.util.List;

public interface BookDatasourceInterface {

    void getBookList(DataCallback<List<Book>> callback);

    void getBookDetails(int bookId, DataCallback<Book> callback);
}
