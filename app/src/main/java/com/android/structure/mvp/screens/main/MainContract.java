package com.android.structure.mvp.screens.main;

import com.android.structure.mvp.models.Collection;
import com.android.structure.mvp.screens.base.BaseContract;

import java.util.List;

interface MainContract {

    interface View extends BaseContract.View {

        void setCollectionList(List<Collection> collectionList);

        void launchBookListActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void loadData();

        void onButtonCaseClicked();
    }
}
