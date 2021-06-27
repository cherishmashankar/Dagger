package com.example.android.dagger.network.auth;

import com.example.android.dagger.models.Users;

import io.reactivex.Flowable;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface AuthApi {

    @GET("users/{id}")
    Flowable<Users> getUser(
      @Path("id") int id
    );

}
