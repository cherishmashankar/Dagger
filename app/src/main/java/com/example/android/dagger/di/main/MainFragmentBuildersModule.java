package com.example.android.dagger.di.main;

import com.example.android.dagger.ui.main.profile.ProfileFragment;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

@Module
public abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract ProfileFragment contributeProfileFragment();

//    @ContributesAndroidInjector
//    abstract PostsFragment contributePostsFragment();
}