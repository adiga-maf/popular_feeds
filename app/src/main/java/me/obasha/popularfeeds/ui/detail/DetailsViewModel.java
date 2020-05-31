package me.obasha.popularfeeds.ui.detail;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.os.Bundle;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;
import me.obasha.popularfeeds.data.model.Article;

public class DetailsViewModel extends ViewModel {

    private CompositeDisposable disposable;

    private final MutableLiveData<Article> selectedArticle = new MutableLiveData<>();

    public LiveData<Article> getSelectedArticle() {
        return selectedArticle;
    }

    @Inject
    public DetailsViewModel() {
        disposable = new CompositeDisposable();
    }

    public void setSelectedArticle(Article article) {
        selectedArticle.setValue(article);
    }

    public void saveToBundle(Bundle outState) {
        if(selectedArticle.getValue() != null) {
            outState.putSerializable("article", selectedArticle.getValue());
        }
    }

    public void restoreFromBundle(Bundle savedInstanceState) {
        if(selectedArticle.getValue() == null) {
            if(savedInstanceState != null && savedInstanceState.containsKey("article")) {
                savedInstanceState.getSerializable("article");
                selectedArticle.setValue((Article) savedInstanceState.getSerializable("article"));
            }
        }
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
