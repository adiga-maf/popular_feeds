package me.obasha.popularfeeds.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ArticleMedia {

    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("subtype")
    @Expose
    public String subtype;
    @SerializedName("caption")
    @Expose
    public String caption;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("approved_for_syndication")
    @Expose
    public Integer approvedForSyndication;
    @SerializedName("media-metadata")
    @Expose
    public List<ArticleMediaMetadata> mediaMetadata = null;
}
