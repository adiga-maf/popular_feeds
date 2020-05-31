package me.obasha.popularfeeds.data.rest;

import javax.inject.Inject;

import io.reactivex.Single;
import me.obasha.popularfeeds.data.model.ApiResponse;

public class ArticleRepository {

    private final ArticleService articleService;

    @Inject
    public ArticleRepository(ArticleService articleService) {
        this.articleService = articleService;
    }

    public Single<ApiResponse> getArticles() {
        return articleService.getArticles();
    }
}
