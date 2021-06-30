package com.example.android.dagger.ui.main.profile;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android.dagger.SessionManager;
import com.example.android.dagger.models.Post;
import com.example.android.dagger.models.Users;
import com.example.android.dagger.ui.Resource;
import com.example.android.dagger.ui.auth.AuthResource;

import javax.inject.Inject;

public class ProfileViewModel extends ViewModel {
    private static final String TAG = "ProfileViewModel";



    private final SessionManager sessionManager;

    @Inject
    public ProfileViewModel(SessionManager sessionManager) {
        this.sessionManager = sessionManager;

    }

    public LiveData<AuthResource<Users>> getAuthenticatedUser(){
        return sessionManager.getAuthUser();
    }
}
