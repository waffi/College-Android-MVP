package com.android.structure.mvp.datasources;

import com.android.structure.mvp.datasources.bookDatasource.BookDatasource;
import com.android.structure.mvp.datasources.bookDatasource.BookDatasourceInterface;

public class DatasourceFactory {

    private DatasourceFactory() {
    }

    public static BookDatasourceInterface bookDatasource() {
        return new BookDatasource();
    }
}
