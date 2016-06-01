package com.tencent.clean.data.repository;

import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.data.RetrofitHelper;
import com.tencent.clean.data.model.SampleResult;

import java.util.List;
import rx.Observable;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by hoollyzhang on 16/5/26.
 * Description :
 */
public class SampleRemoveRepositroyImpl implements SampleRepository {


    public SampleRemoveRepositroyImpl() {

    }

    @Override
    public Observable<List<SampleModel>> lists(int count, int page) {
        return RetrofitHelper.getSampleService().listPic(count, page)
                .map(new Func1<SampleResult, List<SampleModel>>() {
                    @Override
                    public List<SampleModel> call(SampleResult sampleResult) {
                        return sampleResult.beauties;
                    }
                }).subscribeOn(Schedulers.io());
    }

}
