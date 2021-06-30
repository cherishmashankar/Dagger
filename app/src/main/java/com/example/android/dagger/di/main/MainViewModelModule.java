package com.example.android.dagger.di.main;

import androidx.lifecycle.ViewModel;

import com.example.android.dagger.di.ViewModelKey;
import com.example.android.dagger.models.Post;
import com.example.android.dagger.ui.auth.AuthViewModel;
import com.example.android.dagger.ui.main.post.PostsViewModel;
import com.example.android.dagger.ui.main.profile.ProfileViewModel;

import dagger.Binds;
import dagger.Module;
import dagger.multibindings.IntoMap;

@Module
public abstract class MainViewModelModule {

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel.class)
    public abstract ViewModel bindProfileViewModel(ProfileViewModel viewModel);

    @Binds
    @IntoMap
    @ViewModelKey(PostsViewModel.class)
    public abstract ViewModel bindPostsViewModel(PostsViewModel viewModel);
}