package com.android.structure.mvp.commons;

import com.android.structure.mvp.models.book.Book;

import java.util.List;

public interface BookDatasourceInterface {

    void getBookList(DataCallback<List<Book>> callback);

    void getBookDetails(int bookId, DataCallback<Book> callback);
}
