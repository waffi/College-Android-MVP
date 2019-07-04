package com.android.structure.mvp.screens.bookList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.structure.mvp.R;
import com.android.structure.mvp.models.Book;

class BookListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final BookListContract.Presenter presenter;
    private final TextView bookNameTextView;
    private final TextView bookDateTextView;
    private Book currentItem;

    private BookListItemViewHolder(View itemView, BookListContract.Presenter presenter) {
        super(itemView);

        this.presenter = presenter;

        this.bookNameTextView = (TextView) itemView.findViewById(R.id.book_name_textview);
        this.bookDateTextView = (TextView) itemView.findViewById(R.id.book_date_textview);

        this.itemView.setOnClickListener(this);
    }

    static BookListItemViewHolder newInstance(Context context, ViewGroup parent, BookListContract.Presenter presenter) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);

        return new BookListItemViewHolder(itemView, presenter);
    }

    void bindView(Book item) {
        this.currentItem = item;

        Context context = this.itemView.getContext();

        this.bookNameTextView.setText(item.title.toString());
        this.bookDateTextView.setText(item.date.toString());
    }

    @Override
    public void onClick(View v) {
        if (v == this.itemView) {
            if (this.currentItem != null) {
                this.presenter.onBookClicked(this.currentItem);
            }
        }
    }
}
