package com.example.android.dagger.di.auth;

import androidx.lifecycle.ViewModel;

import com.example.android.dagger.di.ViewModelKey;
import com.example.android.dagger.ui.auth.AuthViewModel;
import com.example.android.dagger.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class AuthViewModelsModule {

    @Binds
    @IntoMap
    @ViewModelKey( AuthViewModel.class)
    public abstract ViewModel bindAuthViewModel( AuthViewModel viewModel);
}