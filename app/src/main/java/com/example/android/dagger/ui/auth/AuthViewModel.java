package com.example.android.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.android.dagger.models.Users;
import com.example.android.dagger.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;

    private MediatorLiveData<Users> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi){
        this.authApi = authApi;

    }

    public void authenticateUser(int userID){
        final LiveData<Users> source = LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userID)
                .subscribeOn(Schedulers.io())
        );

        authUser.addSource(source, new Observer<Users>() {
            @Override
            public void onChanged(Users users) {
                authUser.setValue(users);
                authUser.removeSource(source);
            }
        });

    }

    public LiveData<Users> observeUser(){
        return authUser;
    }
}
