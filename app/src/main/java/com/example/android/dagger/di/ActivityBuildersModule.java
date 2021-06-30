package com.example.android.dagger.di;


import com.example.android.dagger.di.auth.AuthModule;
import com.example.android.dagger.di.auth.AuthViewModelsModule;
import com.example.android.dagger.di.main.MainFragmentBuildersModule;
import com.example.android.dagger.di.main.MainModule;
import com.example.android.dagger.di.main.MainViewModelModule;
import com.example.android.dagger.network.main.MainApi;
import com.example.android.dagger.ui.auth.AuthActivity;
import com.example.android.dagger.ui.main.MainActivity;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class ActivityBuildersModule  {

    @ContributesAndroidInjector(
            modules = {AuthViewModelsModule.class , AuthModule.class})
    abstract AuthActivity contributeAuthActivity();

    @ContributesAndroidInjector(
            modules = {MainFragmentBuildersModule.class, MainViewModelModule.class, MainModule.class}
    )
    abstract MainActivity contributeMainActivity();


}
