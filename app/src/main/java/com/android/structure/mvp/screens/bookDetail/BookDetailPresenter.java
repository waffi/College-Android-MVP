package com.android.structure.mvp.screens.bookDetail;

import com.android.structure.mvp.models.book.Book;
import com.android.structure.mvp.screens.base.BasePresenter;
import com.android.structure.mvp.commons.DataCallback;
import com.android.structure.mvp.commons.BookDatasourceInterface;
import com.android.structure.mvp.commons.DatasourceError;

import java.lang.ref.WeakReference;
import java.text.DateFormat;

class BookDetailPresenter extends BasePresenter<BookDetailContract.View> implements BookDetailContract.Presenter {

    private final BookDatasourceInterface bookDatasource;

    private Book book;

    BookDetailPresenter(BookDatasourceInterface bookDatasource) {
        this.bookDatasource = bookDatasource;
    }

    @Override
    public void loadBook(int bookId) {
        this.view.setProgressBarVisible(true);
        this.view.setContentViewVisible(false);

        this.bookDatasource.getBookDetails(bookId, new GetBookDetailsDataCallback(this));
    }

    @Override
    public void onUpdateButtonClicked() {
        if (this.book != null) {
            this.view.displayUpdateBookClickedSnackbar(this.book);
        }
    }

    private void onGetBookDetailsSucceeded(Book book) {
        this.book = book;

        if (this.view != null) {
            this.view.setProgressBarVisible(false);
            this.view.setContentViewVisible(true);

            this.view.setBookNameText(book.bookName());
            this.view.setBookDateText(DateFormat.getDateInstance().format(book.bookDate()));
        }
    }

    private void onGetBookDetailsFailed(DatasourceError error) {
        if (this.view != null) {
            this.view.setProgressBarVisible(false);

            // TODO
        }
    }

    private static class GetBookDetailsDataCallback implements DataCallback<Book> {

        private final WeakReference<BookDetailPresenter> presenterWeak;

        private GetBookDetailsDataCallback(BookDetailPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(Book data) {
            BookDetailPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetBookDetailsSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            BookDetailPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetBookDetailsFailed(error);
            }
        }
    }
}
