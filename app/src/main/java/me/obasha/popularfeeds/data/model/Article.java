package me.obasha.popularfeeds.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Article implements Serializable {

    @SerializedName("uri")
    @Expose
    public String uri;
    @SerializedName("url")
    @Expose
    public String url;
    @SerializedName("id")
    @Expose
    public Long id;
    @SerializedName("asset_id")
    @Expose
    public Long assetId;
    @SerializedName("source")
    @Expose
    public String source;
    @SerializedName("published_date")
    @Expose
    public String publishedDate;
    @SerializedName("updated")
    @Expose
    public String updated;
    @SerializedName("section")
    @Expose
    public String section;
    @SerializedName("subsection")
    @Expose
    public String subsection;
    @SerializedName("nytdsection")
    @Expose
    public String nytdsection;
    @SerializedName("adx_keywords")
    @Expose
    public String adxKeywords;
    @SerializedName("column")
    @Expose
    public Object column;
    @SerializedName("byline")
    @Expose
    public String byline;
    @SerializedName("type")
    @Expose
    public String type;
    @SerializedName("title")
    @Expose
    public String title;
    @SerializedName("abstract")
    @Expose
    public String _abstract;
    @SerializedName("des_facet")
    @Expose
    public List<String> desFacet = null;
    @SerializedName("org_facet")
    @Expose
    public List<Object> orgFacet = null;
    @SerializedName("per_facet")
    @Expose
    public List<String> perFacet = null;
    @SerializedName("geo_facet")
    @Expose
    public List<String> geoFacet = null;
    @SerializedName("media")
    @Expose
    public List<ArticleMedia> media = null;
    @SerializedName("eta_id")
    @Expose
    public Integer etaId;

    public String getSmallThumbUrl() {
        String url = "";
        if (media.size() > 0)
            url = media.get(0).mediaMetadata.get(0).url;

        return url;
    }

    public String getBigThumbUrl() {
        String url = "";
        if (media.size() > 0) {
            int mediaMetadataSize = media.get(0).mediaMetadata.size();
            url = media.get(0).mediaMetadata.get(mediaMetadataSize - 1).url;
        }

        return url;
    }
}
