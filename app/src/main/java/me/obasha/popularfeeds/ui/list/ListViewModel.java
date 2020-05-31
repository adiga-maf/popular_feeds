package me.obasha.popularfeeds.ui.list;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;
import me.obasha.popularfeeds.data.model.ApiResponse;
import me.obasha.popularfeeds.data.model.Article;
import me.obasha.popularfeeds.data.rest.ArticleRepository;

public class ListViewModel extends ViewModel {

    private final ArticleRepository articleRepository;
    private CompositeDisposable disposable;

    private final MutableLiveData<List<Article>> articles = new MutableLiveData<>();
    private final MutableLiveData<Boolean> repoLoadError = new MutableLiveData<>();
    private final MutableLiveData<Boolean> loading = new MutableLiveData<>();

    @Inject
    public ListViewModel(ArticleRepository articleRepository) {
        this.articleRepository = articleRepository;
        disposable = new CompositeDisposable();
        fetchRepos();
    }

    LiveData<List<Article>> getArticles() {
        return articles;
    }
    LiveData<Boolean> getError() {
        return repoLoadError;
    }
    LiveData<Boolean> getLoading() {
        return loading;
    }

    private void fetchRepos() {
        loading.setValue(true);
        disposable.add(articleRepository.getArticles().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread()).subscribeWith(new DisposableSingleObserver<ApiResponse>() {
                    @Override
                    public void onSuccess(ApiResponse response) {
                        repoLoadError.setValue(false);
                        articles.setValue(response.articles);
                        loading.setValue(false);
                    }

                    @Override
                    public void onError(Throwable e) {
                        repoLoadError.setValue(true);
                        loading.setValue(false);
                    }
                }));
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        if (disposable != null) {
            disposable.clear();
            disposable = null;
        }
    }
}
