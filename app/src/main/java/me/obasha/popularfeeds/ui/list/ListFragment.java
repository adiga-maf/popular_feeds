package me.obasha.popularfeeds.ui.list;

import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import javax.inject.Inject;

import butterknife.BindView;
import me.obasha.popularfeeds.R;
import me.obasha.popularfeeds.base.BaseActivity;
import me.obasha.popularfeeds.base.BaseFragment;
import me.obasha.popularfeeds.data.model.Article;
import me.obasha.popularfeeds.ui.detail.DetailsViewModel;
import me.obasha.popularfeeds.ui.main.DetailsActivity;
import me.obasha.popularfeeds.util.ViewModelFactory;
import me.obasha.popularfeeds.ui.detail.DetailsFragment;

public class ListFragment extends BaseFragment implements RepoSelectedListener {

    @BindView(R.id.recyclerView)
    RecyclerView listView;
    @BindView(R.id.tv_error)
    TextView errorTextView;
    @BindView(R.id.loading_view)
    View loadingView;

    /**
     * Whether or not the activity is in two-pane mode, i.e. running on a tablet
     * device.
     */
    private boolean mTwoPane;

    @Inject
    ViewModelFactory viewModelFactory;
    private ListViewModel viewModel;

    @Override
    protected int layoutRes() {
        return R.layout.screen_list;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(ListViewModel.class);

        listView.addItemDecoration(new DividerItemDecoration(getBaseActivity(), DividerItemDecoration.VERTICAL));
        listView.setAdapter(new RepoListAdapter(getBaseActivity(), viewModel, this, this));
        listView.setLayoutManager(new LinearLayoutManager(getContext()));

        if (getBaseActivity().findViewById(R.id.item_detail_container) != null) {
            // The detail container view will be present only in the
            // large-screen layouts (res/values-w900dp).
            // If this view is present, then the
            // activity should be in two-pane mode.
            mTwoPane = true;
        }

        observableViewModel();
    }

    @Override
    public void onArticleSelected(Article article) {
        BaseActivity.detailsViewModel = ViewModelProviders.of(getBaseActivity(), viewModelFactory).get(DetailsViewModel.class);
        BaseActivity.detailsViewModel.setSelectedArticle(article);

        if (mTwoPane)
            getBaseActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.item_detail_container, new DetailsFragment())
                    .addToBackStack(null)
                    .commit();
        else
            startActivity(new Intent(getBaseActivity(), DetailsActivity.class));

    }

    private void observableViewModel() {
        viewModel.getArticles().observe(this, repos -> {
            if (repos != null) listView.setVisibility(View.VISIBLE);
        });

        viewModel.getError().observe(this, isError -> {
            if (isError != null) if (isError) {
                errorTextView.setVisibility(View.VISIBLE);
                listView.setVisibility(View.GONE);
                errorTextView.setText("An Error Occurred While Loading Data!");
            } else {
                errorTextView.setVisibility(View.GONE);
                errorTextView.setText(null);
            }
        });

        viewModel.getLoading().observe(this, isLoading -> {
            if (isLoading != null) {
                loadingView.setVisibility(isLoading ? View.VISIBLE : View.GONE);
                if (isLoading) {
                    errorTextView.setVisibility(View.GONE);
                    listView.setVisibility(View.GONE);
                }
            }
        });
    }
}
