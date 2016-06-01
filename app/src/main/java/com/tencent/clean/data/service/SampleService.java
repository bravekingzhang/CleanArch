package com.tencent.clean.data.service;

import com.tencent.clean.data.model.SampleModel;
import com.tencent.clean.data.model.SampleResult;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * Created by hoollyzhang on 16/5/25.
 * Description :
 */
public interface SampleService {

    @GET("api/data/福利/{count}/{page}")
    Observable<SampleResult> listPic(@Path("count") int count, @Path("page") int page);
}
