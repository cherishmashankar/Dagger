package com.example.android.dagger.di.main;

import com.example.android.dagger.network.main.MainApi;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

@Module
public class MainModule {

    @Provides
    static MainApi provideMAinApi(Retrofit retrofit){
        return retrofit.create(MainApi.class);
    }
}
