package com.android.structure.mvp.screens.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.android.structure.mvp.datasources.DatasourceFactory;
import com.android.structure.mvp.models.Collection;
import com.android.structure.mvp.screens.base.BaseActivity;
import com.android.structure.mvp.R;
import com.android.structure.mvp.screens.bookList.BookListActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View {

    @Override
    protected MainContract.Presenter createPresenter() {
        return new MainPresenter(DatasourceFactory.bookDatasource());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_search).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.onButtonCaseClicked();
            }
        });

        this.presenter.loadData();
    }

    @Override
    public void setCollectionList(List<Collection> collectionList) {

        List<String> arraySpinner = new ArrayList<>();

        arraySpinner.add("Select collection");
        for (Collection collection : collectionList) {
            if (collection.setName.matches("[a-zA-Z0-9\\s]+")){
                arraySpinner.add(collection.setName);
            }
        }
;
        ArrayAdapter<String> adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        Spinner spinner = (Spinner) findViewById(R.id.spinner_collection);
        spinner.setAdapter(adapter);
    }

    @Override
    public void launchBookListActivity() {

        EditText txt_query = (EditText)findViewById(R.id.txt_title);
        EditText txt_limit = (EditText)findViewById(R.id.txt_limit);
        Spinner spinner_collection = (Spinner)findViewById(R.id.spinner_collection);

        String title = txt_query.getText().toString().replace(' ','+');
        int limit = txt_limit.getText().toString().equals("") ? 10 : Integer.parseInt(txt_limit.getText().toString());
        String collection = spinner_collection.getSelectedItem().toString().equals("Select collection") ? "" : spinner_collection.getSelectedItem().toString();

        Intent intent = new Intent(this.getApplicationContext(), BookListActivity.class);
        intent.putExtra("title", title);
        intent.putExtra("collection", collection);
        intent.putExtra("limit", limit);

        this.startActivity(intent);
    }
}
