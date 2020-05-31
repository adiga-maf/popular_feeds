package me.obasha.popularfeeds.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArticleMediaMetadata {

    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("format")
    @Expose
    public String format;
    @SerializedName("height")
    @Expose
    public Integer height;
    @SerializedName("width")
    @Expose
    public Integer width;
}
