package com.android.structure.mvp.commons;

import com.android.structure.mvp.models.book.Book;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class BookDatasource implements BookDatasourceInterface {

    private static List<Book> createBookList() {
        List<Book> bookList = new ArrayList<>();

        Calendar calendar = Calendar.getInstance();
        for (int i = 0; i < 20; i++) {
            String bookName = "Book " + (i + 1);
            calendar.add(Calendar.DAY_OF_YEAR, 1);
            bookList.add(Book.create((i + 1),bookName, calendar.getTime()));
        }

        return bookList;
    }

    @Override
    public void getBookList(DataCallback<List<Book>> callback) {
        new WaitAsyncTask<>(callback, createBookList()).execute(2);
    }

    @Override
    public void getBookDetails(int bookId, DataCallback<Book> callback) {
        new WaitAsyncTask<>(callback, Book.create(bookId,"Book with id " + bookId, new Date()))
                .execute(1);
    }
}
