package com.android.structure.mvp.screens.main;

import com.android.structure.mvp.datasources.DatasourceError;
import com.android.structure.mvp.datasources.bookDatasource.BookDatasourceInterface;
import com.android.structure.mvp.models.Book;
import com.android.structure.mvp.models.Collection;
import com.android.structure.mvp.screens.base.BasePresenter;
import com.android.structure.mvp.utils.asyncTask.DataCallback;

import java.lang.ref.WeakReference;
import java.util.List;

class MainPresenter extends BasePresenter<MainContract.View> implements MainContract.Presenter {

    private final BookDatasourceInterface bookDatasource;

    MainPresenter(BookDatasourceInterface bookDatasource) {
        this.bookDatasource = bookDatasource;
    }

    @Override
    public void loadData() {
        this.bookDatasource.getCollectionList(new MainPresenter.GetCollectionListDataCallback(this));
    }

    @Override
    public void onButtonCaseClicked() {
        this.view.launchBookListActivity();
    }


    private void onGetCollectionListSucceeded(List<Collection> collectionList) {
        if (this.view != null) {

            this.view.setCollectionList(collectionList);
        }
    }

    private void onGetCollectionListFailed(DatasourceError error) {
        if (this.view != null) {
            // TODO: Show error view
        }
    }

    private static class GetCollectionListDataCallback implements DataCallback<List<Collection>> {

        private final WeakReference<MainPresenter> presenterWeak;

        private GetCollectionListDataCallback(MainPresenter presenter) {
            this.presenterWeak = new WeakReference<>(presenter);
        }

        @Override
        public void onDataLoaded(List<Collection> data) {
            MainPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetCollectionListSucceeded(data);
            }
        }

        @Override
        public void onDataError(DatasourceError error) {
            MainPresenter presenter = this.presenterWeak.get();

            if (presenter != null) {
                presenter.onGetCollectionListFailed(error);
            }
        }
    }
}
