package com.example.android.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.ViewModel;

import com.example.android.dagger.models.Users;
import com.example.android.dagger.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;

    @Inject
    public AuthViewModel(AuthApi authApi){
        Log.d(TAG, "AuthViewModel: viewmodel is working...");
        this.authApi = authApi;

        authApi.getUser(1)
                .toObservable()
                .subscribeOn(Schedulers.io())
                .subscribe(new Observer<Users>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(Users user) {
                        Log.d(TAG, "onNext: " + user.getEmail());
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }
}
