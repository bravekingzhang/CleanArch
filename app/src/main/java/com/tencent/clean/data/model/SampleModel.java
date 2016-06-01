package com.tencent.clean.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * A sample model. Replace this with your own.
 */
public class SampleModel extends RealmObject {


    @SerializedName("_id")
    @PrimaryKey
    @Expose
    private String  id;
    @SerializedName("createdAt")
    @Expose
    private String  createdAt;
    @SerializedName("desc")
    @Expose
    private String  desc;
    @SerializedName("publishedAt")
    @Expose
    private String  publishedAt;
    @SerializedName("source")
    @Expose
    private String  source;
    @SerializedName("type")
    @Expose
    private String  type;
    @SerializedName("url")
    @Expose
    private String  url;
    @SerializedName("used")
    @Expose
    private Boolean used;
    @SerializedName("who")
    @Expose
    private String  who;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getUsed() {
        return used;
    }

    public void setUsed(Boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return "SampleModel{" +
                "id='" + id + '\'' +
                ", createdAt='" + createdAt + '\'' +
                ", desc='" + desc + '\'' +
                ", publishedAt='" + publishedAt + '\'' +
                ", source='" + source + '\'' +
                ", type='" + type + '\'' +
                ", url='" + url + '\'' +
                ", used=" + used +
                ", who='" + who + '\'' +
                '}';
    }
}
