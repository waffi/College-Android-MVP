package com.android.structure.mvp.screens.bookList;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.android.structure.mvp.R;
import com.android.structure.mvp.models.Book;
import com.squareup.picasso.Picasso;

import java.util.List;

class BookListItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private final BookListContract.Presenter presenter;
    private final TextView bookNameTextView;
    private final TextView bookDescriptionTextView;
    private final ImageView bookIdentifierImageView;
    private Book currentItem;

    private BookListItemViewHolder(View itemView, BookListContract.Presenter presenter) {
        super(itemView);

        this.presenter = presenter;

        this.bookNameTextView = (TextView) itemView.findViewById(R.id.book_name_textview);
        this.bookDescriptionTextView = (TextView) itemView.findViewById(R.id.book_desc_textview);
        this.bookIdentifierImageView = (ImageView) itemView.findViewById(R.id.book_identifier_img);

        this.itemView.setOnClickListener(this);
    }

    static BookListItemViewHolder newInstance(Context context, ViewGroup parent, BookListContract.Presenter presenter) {
        View itemView = LayoutInflater.from(context).inflate(R.layout.item_book, parent, false);

        return new BookListItemViewHolder(itemView, presenter);
    }

    void bindView(Book item) {
        this.currentItem = item;

        Context context = this.itemView.getContext();

        try {
            this.bookNameTextView.setText(item.title.toString());
            this.bookDescriptionTextView.setText(item.description.toString());
            Picasso.with(context).load(((List<String>) item.identifier).get(0)).into(this.bookIdentifierImageView);
        }
        catch (Exception e){

        }
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
