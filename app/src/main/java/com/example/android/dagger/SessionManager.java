package com.example.android.dagger;

import android.provider.MediaStore;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.LiveDataReactiveStreams;
import androidx.lifecycle.MediatorLiveData;
import androidx.lifecycle.Observer;

import com.example.android.dagger.models.Users;
import com.example.android.dagger.ui.auth.AuthResource;

import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class SessionManager {
    private static final String TAG = "SessionManager";
    private MediatorLiveData<AuthResource<Users>> cachedUser = new MediatorLiveData<>();


    @Inject
    public SessionManager() {
    }

    public void authenticateWithUser(final LiveData<AuthResource<Users>> source){
        if(cachedUser != null){
            cachedUser.setValue(AuthResource.loading((Users)null));
            cachedUser.addSource(source, new Observer<AuthResource<Users>>() {
                @Override
                public void onChanged(AuthResource<Users> usersAuthResource) {
                    cachedUser.setValue(usersAuthResource);
                    cachedUser.removeSource(source);
                }
            });
        }else{
            Log.d(TAG, "authenticateWithUser: previous session detected retrieving ");
        }
    }

    public void logOut(){
        Log.d(TAG, "logging out...");
        cachedUser.setValue(AuthResource.<Users>logout());
    }

    public LiveData<AuthResource<Users>> getAuthUser(){
        return cachedUser;
    }
}
