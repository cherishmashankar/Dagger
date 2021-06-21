package com.example.android.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.android.dagger.network.auth.AuthApi;

import javax.inject.Inject;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
        this.authApi = authApi;
        
        if(authApi == null)
            Log.d(TAG, "AuthViewModel: NUll");
        else
            Log.d(TAG, "AuthViewModel: Not NULL");

    }
}
