package com.example.android.dagger.ui.main.post;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.android.dagger.SessionManager;
import com.example.android.dagger.di.main.MainModule;
import com.example.android.dagger.network.main.MainApi;

import javax.inject.Inject;

public class PostsViewModel extends ViewModel {

    private static final String TAG = "PostsViewModel";

    private final SessionManager sessionManager;
    private final MainApi mainApi;

    @Inject
    public PostsViewModel(SessionManager sessionManager, MainApi mainApi) {
        this.sessionManager = sessionManager;
        this.mainApi = mainApi;
        Log.d(TAG, "PostsViewModel: View model is working... ");
    }
}
