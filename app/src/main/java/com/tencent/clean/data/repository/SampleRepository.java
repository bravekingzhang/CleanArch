package com.tencent.clean.data.repository;

import com.tencent.clean.data.model.SampleModel;

import java.util.List;

import rx.Observable;

/**
 * A sample repository with CRUD operations on a model.
 */
public interface SampleRepository {

    Observable<List<SampleModel>> lists(int count,int page);
}
