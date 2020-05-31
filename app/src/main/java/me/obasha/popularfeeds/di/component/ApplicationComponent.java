package me.obasha.popularfeeds.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;
import dagger.android.AndroidInjector;
import dagger.android.support.AndroidSupportInjectionModule;
import dagger.android.support.DaggerApplication;
import me.obasha.popularfeeds.base.BaseApplication;
import me.obasha.popularfeeds.di.module.ActivityBindingModule;
import me.obasha.popularfeeds.di.module.ApplicationModule;
import me.obasha.popularfeeds.di.module.ContextModule;

@Singleton
@Component(modules = {ContextModule.class, ApplicationModule.class, AndroidSupportInjectionModule.class, ActivityBindingModule.class})
public interface ApplicationComponent extends AndroidInjector<DaggerApplication> {

    void inject(BaseApplication application);

    @Component.Builder
    interface Builder {
        @BindsInstance
        Builder application(Application application);
        ApplicationComponent build();
    }
}