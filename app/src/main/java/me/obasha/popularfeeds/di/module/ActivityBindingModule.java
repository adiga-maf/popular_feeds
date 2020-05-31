package me.obasha.popularfeeds.di.module;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;
import me.obasha.popularfeeds.ui.main.DetailsActivity;
import me.obasha.popularfeeds.ui.main.MainActivity;
import me.obasha.popularfeeds.ui.main.MainFragmentBindingModule;

@Module
public abstract class ActivityBindingModule {

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract MainActivity bindMainActivity();

    @ContributesAndroidInjector(modules = {MainFragmentBindingModule.class})
    abstract DetailsActivity bindDetailsActivity();
}
