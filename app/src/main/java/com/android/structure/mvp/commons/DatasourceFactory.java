package com.android.structure.mvp.commons;

public class DatasourceFactory {

    private DatasourceFactory() {
    }

    public static BookDatasourceInterface bookDatasource() {
        return new BookDatasource();
    }
}
