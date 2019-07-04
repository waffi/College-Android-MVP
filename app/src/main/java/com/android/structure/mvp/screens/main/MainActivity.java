package com.android.structure.mvp.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

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

        findViewById(R.id.btn_limit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonCaseClicked();
            }
        });
    }

    @Override
    public void launchBookListActivity() {

        EditText txt_limit = (EditText)findViewById(R.id.txt_limit);

        Intent intent = new Intent(this.getApplicationContext(), BookListActivity.class);
        intent.putExtra("limit", Integer.parseInt(txt_limit.getText().toString()));

        this.startActivity(intent);
    }
}
