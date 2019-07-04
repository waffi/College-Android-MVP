package com.android.structure.mvp.screens.base;

/**
 * Base class to extend if you're creating a Presenter.
 *
 * It takes care of attaching and detaching the view.
 *
 */

public abstract class BasePresenter<T extends BaseContract.View> implements BaseContract.Presenter<T> {

    /**
     * The View linked to this Presenter
     */
    protected T view;

    @Override
    public void attachView(T view) {
        this.view = view;
    }

    @Override
    public void detachView() {
        this.view = null;
    }
}
