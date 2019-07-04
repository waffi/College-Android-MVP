package com.android.structure.mvp.screens.main;

import com.android.structure.mvp.screens.base.BaseContract;

interface MainContract {

    interface View extends BaseContract.View {

    }

    interface Presenter extends BaseContract.Presenter<View> {

    }
}
