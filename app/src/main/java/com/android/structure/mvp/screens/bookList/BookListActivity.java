package com.android.structure.mvp.screens.bookList;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;

import com.android.structure.mvp.screens.base.BaseActivity;
import com.android.structure.mvp.datasources.DatasourceFactory;
import com.android.structure.mvp.R;
import com.android.structure.mvp.models.Book;

import java.util.List;

public class BookListActivity extends BaseActivity<BookListContract.Presenter> implements BookListContract.View {

    private BookListAdapter adapter;

    private ProgressBar progressBar;
    private RecyclerView recyclerView;

    @Override
    protected BookListContract.Presenter createPresenter() {
        return new BookListPresenter(DatasourceFactory.bookDatasource());
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_list);

        this.adapter = new BookListAdapter(this.presenter);

        recyclerView = (RecyclerView) findViewById(R.id.book_list_recyclerview);
        progressBar = (ProgressBar) findViewById(R.id.book_list_progressbar);

        recyclerView.setLayoutManager(new LinearLayoutManager(this.getApplicationContext(), LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(this.adapter);

        this.presenter.loadData(
                getIntent().getStringExtra("title"),
                getIntent().getStringExtra("collection"),
                getIntent().getIntExtra("limit",10));
    }

    @Override
    public void setBookList(List<Book> bookList) {
        this.adapter.setItemList(bookList);
    }

    @Override
    public void displayBookClickedSnackbar(Book book) {
        Snackbar.make(this.recyclerView, String.format("%s clicked", book.title), Snackbar.LENGTH_SHORT)
                .show();
    }

    @Override
    public void setProgressBarVisible(boolean visible) {
        this.progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setRecyclerViewVisible(boolean visible) {
        this.recyclerView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }
}
