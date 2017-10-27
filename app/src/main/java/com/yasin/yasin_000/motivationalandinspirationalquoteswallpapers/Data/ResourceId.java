
package com.yasin.yasin_000.motivationalandinspirationalquoteswallpapers.Data;

import com.google.gson.annotations.SerializedName;

import javax.annotation.Generated;

@Generated("net.hexar.json2pojo")
@SuppressWarnings("unused")
public class ResourceId {

    @SerializedName("kind")
    private String mKind;
    @SerializedName("videoId")
    private String mVideoId;

    public String getKind() {
        return mKind;
    }

    public void setKind(String kind) {
        mKind = kind;
    }

    public String getVideoId() {
        return mVideoId;
    }

    public void setVideoId(String videoId) {
        mVideoId = videoId;
    }

}
