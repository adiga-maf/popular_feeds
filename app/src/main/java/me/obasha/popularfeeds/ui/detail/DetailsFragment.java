package me.obasha.popularfeeds.ui.detail;

import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import javax.inject.Inject;

import butterknife.BindView;
import de.hdodenhof.circleimageview.CircleImageView;
import me.obasha.popularfeeds.R;
import me.obasha.popularfeeds.base.BaseActivity;
import me.obasha.popularfeeds.base.BaseFragment;
import me.obasha.popularfeeds.util.ViewModelFactory;

public class DetailsFragment extends BaseFragment {

    @BindView(R.id.img_article_media)
    CircleImageView articleThumb;
    @BindView(R.id.tv_article_title)
    TextView articleTitle;
    @BindView(R.id.tv_byline)
    TextView articleByline;
    @BindView(R.id.tv_source)
    TextView articleSource;
    @BindView(R.id.tv_published_date)
    TextView articlePublishedDate;

    @Inject
    ViewModelFactory viewModelFactory;

    @Override
    protected int layoutRes() {
        return R.layout.screen_details;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        BaseActivity.detailsViewModel.restoreFromBundle(savedInstanceState);
        displayArticle();
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        BaseActivity.detailsViewModel.saveToBundle(outState);
    }

    private void displayArticle() {
        BaseActivity.detailsViewModel.getSelectedArticle().observe(this, article -> {
            if (article != null) {
                articleTitle.setText(article.title);
                articleByline.setText(article.byline);
                articleSource.setText(article.source);
                articlePublishedDate.setText(article.publishedDate);

                final String url = article.getBigThumbUrl();
                Glide.with(getBaseActivity())
                        .load(url)
                        .asBitmap()
                        .centerCrop()
                        .placeholder(new ColorDrawable(ContextCompat.getColor(getBaseActivity(), android.R.color.darker_gray)))
                        .into(articleThumb);
            }
        });
    }
}
