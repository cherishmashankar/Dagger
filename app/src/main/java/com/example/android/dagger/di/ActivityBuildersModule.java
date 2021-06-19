package com.example.android.dagger.di;


import com.example.android.dagger.ui.auth.AuthActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule  {

    @ContributesAndroidInjector
    abstract AuthActivity contributeAuthActivity();


}
