package com.android.structure.mvp.screens.main;

import android.os.Bundle;

import com.android.structure.mvp.screens.base.BaseActivity;
import com.android.structure.mvp.R;
import com.android.structure.mvp.screens.bookDetail.BookDetailActivity;
import com.android.structure.mvp.screens.bookList.BookListActivity;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        this.startActivity(BookListActivity.getStartingIntent(this.getApplicationContext()));

    }
}
