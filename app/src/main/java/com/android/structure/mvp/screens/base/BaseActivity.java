package com.android.structure.mvp.screens.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Base class to extend if you're creating an Activity as a View.
 *
 * It takes care of storing the view's presenter and handling activity's lifecycle to attach or detach the view from the presenter.
 *
 */

public abstract class BaseActivity<T extends BaseContract.Presenter> extends AppCompatActivity implements BaseContract.View {

    /**
     * The Presenter attached to this View
     */
    protected T presenter;

    /**
     * Must be overriden to define the Presenter used by this activity.
     *
     * @return The presenter that will be used by this View.
     */
    protected abstract T createPresenter();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.presenter = this.createPresenter();
        if (this.presenter != null) {
            this.presenter.attachView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (this.presenter != null) {
            this.presenter.detachView();
        }
        this.presenter = null;
    }
}
