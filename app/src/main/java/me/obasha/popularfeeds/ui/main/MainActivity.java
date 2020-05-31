package me.obasha.popularfeeds.ui.main;

import android.os.Bundle;

import me.obasha.popularfeeds.R;
import me.obasha.popularfeeds.base.BaseActivity;
import me.obasha.popularfeeds.ui.list.ListFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected int layoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(savedInstanceState == null)
            getSupportFragmentManager().beginTransaction().add(R.id.screenContainer, new ListFragment()).commit();
    }
}
