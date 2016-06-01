package com.tencent.clean.data.repository;

import android.util.Log;

import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.domain.logic.Converter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by hoollyzhang on 16/5/30.
 * Description :
 */
public class SampleLocalRepositoryImpl implements SampleRepository {

    public SampleLocalRepositoryImpl() {
    }


    //realm maybe have some bugs....,fuck
    @Override
    public Observable<List<SampleModel>> lists(int count, int page) {
        Realm realm = Realm.getDefaultInstance();
        Observable observable = realm.where(SampleModel.class).findAll().asObservable().map(new Func1<RealmResults<SampleModel>, List<SampleModel>>() {
            @Override
            public List<SampleModel> call(RealmResults<SampleModel> sampleModels) {
                return Converter.RealmResultList2SampleModel(sampleModels);
            }
        });
        return observable.first();
    }
//realm maybe have some bugs....,fuck
   /* @Override
    public Observable<List<SampleModel>> lists(int count, int page) {
        Realm realm = Realm.getDefaultInstance();
        RealmResults realmResults = realm.where(SampleModel.class).findAll();
        if (realmResults.size() > 0){
            return realmResults.asObservable().map(new Func1<RealmResults<SampleModel>, List<SampleModel>>() {
                @Override
                public List<SampleModel> call(RealmResults<SampleModel> sampleModels) {
                    return Converter.RealmResultList2SampleModel(sampleModels);
                }
            });
        }else{
            return Observable.empty();

        }

    }*/

}
