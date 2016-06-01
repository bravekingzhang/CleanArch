package com.tencent.clean.domain.logic;

import com.tencent.clean.data.model.SampleModel;

import java.util.LinkedList;
import java.util.List;

import io.realm.RealmResults;

/**
 * Created by hoollyzhang on 16/5/31.
 * Description :
 */
public class Converter {
    public static List<SampleModel> RealmResultList2SampleModel(RealmResults<SampleModel> sampleModels){

        List<SampleModel> list = new LinkedList<>();

        for (SampleModel sampleModelRealmObject : sampleModels){
            list.add(sampleModelRealmObject);
        }
        return list;
    }
}
