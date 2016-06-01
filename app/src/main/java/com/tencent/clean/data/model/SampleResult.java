package com.tencent.clean.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by hoollyzhang on 16/5/30.
 * Description :
 */
public class SampleResult {
    public boolean          error;
    public @SerializedName("results")
    List<SampleModel> beauties;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public List<SampleModel> getBeauties() {
        return beauties;
    }

    public void setBeauties(List<SampleModel> beauties) {
        this.beauties = beauties;
    }

    @Override
    public String toString() {
        return "SampleResult{" +
                "error=" + error +
                ", beauties=" + beauties +
                '}';
    }
}
