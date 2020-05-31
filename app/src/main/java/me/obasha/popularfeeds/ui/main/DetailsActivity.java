package me.obasha.popularfeeds.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import me.obasha.popularfeeds.R;
import me.obasha.popularfeeds.base.BaseActivity;
import me.obasha.popularfeeds.ui.detail.DetailsFragment;

public class DetailsActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_details;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (null == savedInstanceState)
            getSupportFragmentManager().beginTransaction().add(R.id.item_detail_container, new DetailsFragment())
                    .commit();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            navigateUpTo(new Intent(this, MainActivity.class));
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
