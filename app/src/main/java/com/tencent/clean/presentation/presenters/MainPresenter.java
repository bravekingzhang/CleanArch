package com.tencent.clean.presentation.presenters;

import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.presentation.BasePresenter;
import com.tencent.clean.presentation.BaseView;

import java.util.List;


/**
 * 放在一起，一个Presenter 对应一个view
 */
public interface MainPresenter extends BasePresenter {

    interface View extends BaseView {
        // TODO: Add your view methods
        void showSampleData(List<SampleModel> sampleModels);
}

    // TODO: Add your presenter methods,for example unsubscribe
    void unsubscribe();
}
