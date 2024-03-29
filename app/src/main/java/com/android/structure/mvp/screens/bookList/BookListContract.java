package com.android.structure.mvp.screens.bookList;

import com.android.structure.mvp.models.Book;
import com.android.structure.mvp.screens.base.BaseContract;

import java.util.List;

interface BookListContract {

    interface View extends BaseContract.View {

        void setBookList(List<Book> bookList);

        void displayBookClickedSnackbar(Book book);

        void setProgressBarVisible(boolean visible);

        void setRecyclerViewVisible(boolean visible);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadData(String title, String collection, int limit);

        void onBookClicked(Book book);
    }
}
