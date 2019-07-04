package com.android.structure.mvp.screens.bookList;

import com.android.structure.mvp.models.Book;
import com.android.structure.mvp.screens.base.BasePresenter;
import com.android.structure.mvp.utils.asyncTask.DataCallback;
import com.android.structure.mvp.datasources.bookDatasource.BookDatasourceInterface;
import com.android.structure.mvp.datasources.DatasourceError;

import java.lang.ref.WeakReference;
import java.util.List;

class BookListPresenter extends BasePresenter<BookListContract.View> implements BookListContract.Presenter {

    private final BookDatasourceInterface bookDatasource;

    BookListPresenter(BookDatasourceInterface bookDatasource) {
        this.bookDatasource = bookDatasource;
    }

    @Override
    public void loadData() {
        this.view.setProgressBarVisible(true);
        this.view.setRecyclerViewVisible(false);

        this.bookDatasource.getBookList(new GetBookListDataCallback(this));
    }

    @Override
    public void onBookClicked(Book book) {
        this.view.displayBookClickedSnackbar(book);
    }

    private void onGetBookListSucceeded(List<Book> bookList) {
        if (this.view != null) {
            this.view.setProgressBarVisible(false);
            this.view.setRecyclerViewVisible(true);

            this.view.setBookList(bookList);
        }
    }

    private void onGetBookListFailed(DatasourceError error) {
        if (this.view != null) {
            this.view.setProgressBarVisible(false);

            // TODO: Show error view
        }
    }

    private static class GetBookListDataCallback implements DataCallback<List<Book>> {

        private final WeakReference<BookListPresenter> presenterWeak;

        private GetBookListDataCallback(BookListPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Book> data) {
            BookListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetBookListSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            BookListPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetBookListFailed(error);
            }
        }
    }
}
