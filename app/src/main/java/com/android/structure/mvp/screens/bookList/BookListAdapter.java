package com.android.structure.mvp.screens.bookList;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;

import com.android.structure.mvp.models.Book;

import java.util.ArrayList;
import java.util.List;

class BookListAdapter extends RecyclerView.Adapter<BookListItemViewHolder> {

    private final BookListContract.Presenter presenter;

    private final List<Book> itemList;

    BookListAdapter(BookListContract.Presenter presenter) {
        this.presenter = presenter;

        this.itemList = new ArrayList<>();
    }

    @Override
    public BookListItemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return BookListItemViewHolder.newInstance(parent.getContext(), parent, this.presenter);
    }

    @Override
    public void onBindViewHolder(BookListItemViewHolder holder, int position) {
        holder.bindView(this.itemList.get(position));
    }

    @Override
    public int getItemCount() {
        return this.itemList.size();
    }

    void setItemList(List<Book> itemList) {
        this.itemList.clear();

        if (itemList != null) {
            this.itemList.addAll(itemList);
        }

        this.notifyDataSetChanged();
    }
}
