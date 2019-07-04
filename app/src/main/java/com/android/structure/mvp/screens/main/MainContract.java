package com.android.structure.mvp.screens.main;

import com.android.structure.mvp.screens.base.BaseContract;

interface MainContract {

    interface View extends BaseContract.View {

        void launchBookListActivity();
    }

    interface Presenter extends BaseContract.Presenter<View> {

        void onButtonCaseClicked();
    }
}
