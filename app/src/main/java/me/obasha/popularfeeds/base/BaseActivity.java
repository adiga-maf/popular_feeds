package me.obasha.popularfeeds.base;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;
import me.obasha.popularfeeds.ui.detail.DetailsViewModel;

public abstract class BaseActivity extends DaggerAppCompatActivity {

    public static DetailsViewModel detailsViewModel;

    @LayoutRes
    protected abstract int layoutRes();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutRes());
        ButterKnife.bind(this);
    }
}