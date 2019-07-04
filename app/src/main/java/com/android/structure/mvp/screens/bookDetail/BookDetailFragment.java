package com.android.structure.mvp.screens.bookDetail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.android.structure.mvp.models.book.Book;
import com.android.structure.mvp.screens.base.BaseFragment;
import com.android.structure.mvp.datasources.DatasourceFactory;
import com.android.structure.mvp.R;

public class BookDetailFragment extends BaseFragment<BookDetailContract.Presenter> implements BookDetailContract.View {

    private static final String EVENT_ID_BUNDLE_KEY = "com.android.structure.mvp.ui.bookDetails.BookDetailFragment.bookId";

    private View containerView;
    private TextView bookNameTextView;
    private TextView bookDateTextView;
    private View progressBar;

    public static BookDetailFragment newInstance(int bookId) {
        Bundle args = new Bundle();
        args.putInt(EVENT_ID_BUNDLE_KEY, bookId);

        BookDetailFragment fragment = new BookDetailFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected BookDetailContract.Presenter createPresenter() {
        return new BookDetailPresenter(DatasourceFactory.bookDatasource());
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Bundle arguments = this.getArguments();
        if (!arguments.containsKey(EVENT_ID_BUNDLE_KEY)) {
            throw new IllegalArgumentException("Impossible to use BookDetailFragment without an book id");
        }

        this.presenter.loadBook(arguments.getInt(EVENT_ID_BUNDLE_KEY));
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_book_detail, container, false);

        containerView = view.findViewById(R.id.book_details_content_container);
        progressBar = view.findViewById(R.id.book_details_progressbar);

        bookNameTextView = (TextView) view.findViewById(R.id.book_details_name_textview);
        bookDateTextView = (TextView) view.findViewById(R.id.book_details_date_textview);

        view.findViewById(R.id.book_details_update_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onUpdateButtonClicked();
            }
        });

        return view;
    }

    @Override
    public void setContentViewVisible(boolean visible) {
        this.containerView.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setProgressBarVisible(boolean visible) {
        this.progressBar.setVisibility(visible ? View.VISIBLE : View.GONE);
    }

    @Override
    public void setBookNameText(String bookNameText) {
        this.bookNameTextView.setText(bookNameText);
    }

    @Override
    public void setBookDateText(String bookDateText) {
        this.bookDateTextView.setText(bookDateText);
    }

    @Override
    public void displayUpdateBookClickedSnackbar(Book book) {
        Snackbar.make(this.containerView, String.format("Button Update %s", book.bookName()), Snackbar.LENGTH_SHORT)
                .show();
    }

    private void onUpdateButtonClicked() {
        this.presenter.onUpdateButtonClicked();
    }
}
