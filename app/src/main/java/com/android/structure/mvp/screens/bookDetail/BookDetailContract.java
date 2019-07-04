package com.android.structure.mvp.screens.bookDetail;

import com.android.structure.mvp.screens.base.BaseContract;
import com.android.structure.mvp.models.Book;

interface BookDetailContract {

    interface View extends BaseContract.View {

        void setContentViewVisible(boolean visible);

        void setProgressBarVisible(boolean visible);

        void setBookNameText(String bookNameText);

        void setBookDateText(String bookDateText);

        void displayUpdateBookClickedSnackbar(Book book);
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadBook(String title);

        void onUpdateButtonClicked();
    }
}
