package me.obasha.popularfeeds.ui.main;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.obasha.popularfeeds.ui.detail.DetailsFragment;
import me.obasha.popularfeeds.ui.list.ListFragment;

@Module
public abstract class MainFragmentBindingModule {

    @ContributesAndroidInjector
    abstract ListFragment provideListFragment();

    @ContributesAndroidInjector
    abstract DetailsFragment provideDetailsFragment();
}
