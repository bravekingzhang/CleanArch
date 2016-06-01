package com.tencent.clean.domain.usercase;

import android.content.Context;

import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.data.repository.SampleLocalRepositoryImpl;
import com.tencent.clean.data.repository.SampleRemoveRepositroyImpl;
import com.tencent.clean.data.repository.SampleRepository;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmConfiguration;
import rx.Observable;
import rx.functions.Action1;
import rx.functions.Func1;
import timber.log.Timber;

/**
 * Created by hoollyzhang on 16/5/26.
 * Description :
 */
public class SampleUserCase {
    SampleRepository sampleRepositoryRemote ;
    SampleRepository sampleRepositoryLocal ;

    public SampleUserCase(Context context) {
        RealmConfiguration config = new RealmConfiguration.Builder(context)
                .name("sample.realm")
                .deleteRealmIfMigrationNeeded()
                .schemaVersion(1)
                .build();
        Realm.setDefaultConfiguration(config);
        sampleRepositoryRemote = new SampleRemoveRepositroyImpl();
        sampleRepositoryLocal = new SampleLocalRepositoryImpl();
    }

    //get data from mutil data source
    public Observable<List<SampleModel>> sample() {
        return Observable.concat(
                sampleRepositoryLocal.lists(100, 1),
                sampleRepositoryRemote.lists(100, 1)
        ).first(new Func1<List<SampleModel>, Boolean>() {
            @Override
            public Boolean call(List<SampleModel> sampleModels) {// TODO: 16/5/31 这里应该加入缓存过期判断的策略
                return sampleModels != null && sampleModels.size() > 0;
            }
        }).doOnNext(new Action1<List<SampleModel>>() {
            @Override
            public void call(List<SampleModel> sampleModels) {
                Realm realm = Realm.getDefaultInstance();
                realm.beginTransaction();
                realm.copyToRealmOrUpdate(sampleModels);
                realm.commitTransaction();
            }
        });
    }
}
