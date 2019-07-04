package com.android.structure.mvp.models.book;

import java.util.Date;

public class Book {

    private final int id;
    private final String bookName;
    private final Date bookDate;

    private Book(int id, String bookName, Date bookDate) {
        this.id = id;
        this.bookName = bookName;
        this.bookDate = bookDate;
    }

    public static Book create(int id, String bookName, Date bookDate) {
        return new Book(id, bookName, bookDate);
    }

    public int id() {
        return id;
    }

    public String bookName() {
        return bookName;
    }

    public Date bookDate() {
        return bookDate;
    }

    @Override
    public int hashCode() {
        int result = bookName != null ? bookName.hashCode() : 0;
        result = 31 * result + (bookDate != null ? bookDate.hashCode() : 0);
        return result;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (bookName != null ? !bookName.equals(book.bookName) : book.bookName != null) return false;
        return bookDate != null ? bookDate.equals(book.bookDate) : book.bookDate == null;

    }
}
