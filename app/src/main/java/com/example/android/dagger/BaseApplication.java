package com.example.android.dagger;

//import com.example.android.dagger.di.DaggerAppComponent;

//import com.example.android.dagger.di.DaggerAppComponent;

import com.example.android.dagger.di.DaggerAppComponent;

import dagger.android.AndroidInjector;
import dagger.android.support.DaggerApplication;

public class BaseApplication extends DaggerApplication {

    @Override
    protected AndroidInjector<? extends DaggerApplication> applicationInjector() {
    return DaggerAppComponent.builder().application(this).build();
       // return null;
    }
}
