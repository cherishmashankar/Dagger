package com.example.android.dagger.ui.auth;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;

import com.example.android.dagger.SessionManager;
import com.example.android.dagger.models.Users;
import com.example.android.dagger.network.auth.AuthApi;

import javax.inject.Inject;

import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

public class AuthViewModel extends ViewModel {
    private static final String TAG = "AuthViewModel";
    private AuthApi authApi;
    private final SessionManager sessionManager; // @Singleton scoped dependency

    private MediatorLiveData<AuthResource<Users>> authUser = new MediatorLiveData<>();

    @Inject
    public AuthViewModel(AuthApi authApi, SessionManager sessionManager){
        this.authApi = authApi;
        this.sessionManager = sessionManager;

    }
    public void authenticateWithId(int userId) {
        Log.d(TAG, "attemptLogin: attempting to login.");
        sessionManager.authenticateWithUser(queryUserId(userId));
    }

    private LiveData<AuthResource<Users>> queryUserId(int userId) {
        return LiveDataReactiveStreams.fromPublisher(
                authApi.getUser(userId)
                        .onErrorReturn(new Function<Throwable, Users>() {
                            @Override
                            public Users apply(Throwable throwable) throws Exception {
                                Users errorUser = new Users();
                                errorUser.setId(-1);
                                return errorUser;
                            }
                        })
                        .map(new Function<Users, AuthResource<Users>>() {
                            @Override
                            public AuthResource<Users> apply(Users users) throws Exception {
                                if(users.getId() == -1){
                                    return AuthResource.error("Could not Authenticate", (Users) null);
                                }
                                return AuthResource.authenticated(users);
                            }
                        })
                        .subscribeOn(Schedulers.io())
        );

    }



    public LiveData<AuthResource<Users>> observeAuthState(){
        return sessionManager.getAuthUser();
    }
}
