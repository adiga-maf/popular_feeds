package me.obasha.popularfeeds.data.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ApiResponse {
    @SerializedName("status")
    @Expose
    public String status;
    @SerializedName("copyright")
    @Expose
    public String copyright;
    @SerializedName("num_results")
    @Expose
    public Integer numResults;
    @SerializedName("results")
    @Expose
    public List<Article> articles = null;
}
