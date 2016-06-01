package com.tencent.clean.data;

import com.tencent.clean.data.service.SampleService;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by hoollyzhang on 16/5/25.
 * Description :
 */
public class RetrofitHelper {
    static SampleService service;

    public static SampleService getSampleService() {

        if (service == null) {

            synchronized (RetrofitHelper.class) {

                if (service == null) {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://gank.io/")
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    service = retrofit.create(SampleService.class);
                }
            }
        }

        return service;
    }

}
