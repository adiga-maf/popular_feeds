package me.obasha.popularfeeds.data.rest;

import io.reactivex.Single;
import me.obasha.popularfeeds.data.model.ApiResponse;
import retrofit2.http.GET;

public interface ArticleService {

    @GET("mostpopular/v2/viewed/1.json?api-key=f7w4EERRdGs9mo5d6cEnu7Jn8vt8fgJU")
    Single<ApiResponse> getArticles();
}
