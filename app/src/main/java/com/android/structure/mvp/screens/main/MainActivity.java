package com.android.structure.mvp.screens.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.structure.mvp.screens.base.BaseActivity;
import com.android.structure.mvp.R;
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

        findViewById(R.id.btn_case_1).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonCaseClicked();
            }
        });
    }

    @Override
    public void launchBookListActivity() {
        this.startActivity(BookListActivity.getStartingIntent(this.getApplicationContext()));
    }
}
