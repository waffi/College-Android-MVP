package com.android.structure.mvp.screens.bookDetail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.android.structure.mvp.R;

public class BookDetailActivity extends AppCompatActivity {

    private static final String EVENT_ID_EXTRA_KEY = "com.android.structure.mvp.ui.bookDetails.BookDetailActivity.bookId";

    public static Intent getStartingIntent(Context context, int bookId) {
        Intent intent = new Intent(context, BookDetailActivity.class);

        intent.putExtra(EVENT_ID_EXTRA_KEY, bookId);

        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_book_detail);

        if (savedInstanceState == null) {
            if (!this.getIntent().hasExtra(EVENT_ID_EXTRA_KEY)) {
                throw new IllegalArgumentException("Impossible to create BookDetailActivity without an book id");
            }

            this.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.book_details_fragment_container,
                            BookDetailFragment.newInstance(this.getIntent().getIntExtra(EVENT_ID_EXTRA_KEY, -1)))
                    .commit();
        }
    }
}
