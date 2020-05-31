package me.obasha.popularfeeds.ui.list;

import android.arch.lifecycle.LifecycleOwner;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import me.obasha.popularfeeds.R;
import me.obasha.popularfeeds.data.model.Article;

public class RepoListAdapter extends RecyclerView.Adapter<RepoListAdapter.ArticleViewHolder> {

    private RepoSelectedListener articleSelectedListener;
    private final List<Article> articles = new ArrayList<>();
    private Context context;

    RepoListAdapter(Context context, ListViewModel viewModel, LifecycleOwner lifecycleOwner, RepoSelectedListener articleSelectedListener) {
        this.context = context;
        this.articleSelectedListener = articleSelectedListener;
        viewModel.getArticles().observe(lifecycleOwner, articles -> {
            this.articles.clear();
            if (articles != null) {
                this.articles.addAll(articles);
                notifyDataSetChanged();
            }
        });
        setHasStableIds(true);
    }

    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_article_list_item, parent, false);
        return new ArticleViewHolder(view, articleSelectedListener);
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
        holder.bind(articles.get(position));

        final String url = articles.get(position).getSmallThumbUrl();
        Glide.with(context)
                .load(url)
                .asBitmap()
                .centerCrop()
                .placeholder(new ColorDrawable(ContextCompat.getColor(context, android.R.color.darker_gray)))
                .into(holder.articleThumb);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    @Override
    public long getItemId(int position) {
        return articles.get(position).id;
    }

    static final class ArticleViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.tv_article_title)
        TextView articleTitle;
        @BindView(R.id.tv_byline)
        TextView articleByline;
        @BindView(R.id.tv_source)
        TextView articleSource;
        @BindView(R.id.tv_published_date)
        TextView articlePublishedDate;
        @BindView(R.id.img_article_media)
        CircleImageView articleThumb;

        private Article article;

        ArticleViewHolder(View itemView, RepoSelectedListener repoSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(v -> {
                if (article != null) {
                    repoSelectedListener.onArticleSelected(article);
                }
            });
        }

        void bind(Article article) {
            this.article = article;
            articleTitle.setText(article.title);
            articleByline.setText(article.byline);
            articleByline.setVisibility(!article.byline.isEmpty() ? View.VISIBLE : View.GONE);
            articlePublishedDate.setText(article.publishedDate);
            articleSource.setText(article.source);
        }
    }
}
