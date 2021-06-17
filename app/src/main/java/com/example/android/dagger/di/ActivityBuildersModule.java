package com.example.android.dagger.di;


import com.example.android.dagger.AuthActivity;

import dagger.Module;
import dagger.Provides;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule  {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();


}