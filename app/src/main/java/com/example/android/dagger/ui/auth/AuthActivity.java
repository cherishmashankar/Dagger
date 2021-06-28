package com.example.android.dagger.ui.auth;

import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.RequestManager;
import com.example.android.dagger.R;
import com.example.android.dagger.models.Users;
import com.example.android.dagger.ui.main.MainActivity;
import com.example.android.dagger.viewmodels.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel;

    private EditText userId;
    private ProgressBar progressBar;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;

    @Inject
    RequestManager requestManager;

    @Inject
    Drawable logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        viewModel = ViewModelProviders.of(this,viewModelProviderFactory).get(AuthViewModel.class);
        setLogo();
        userId = findViewById(R.id.user_id_input);
        progressBar = findViewById(R.id.progress_bar);
        findViewById(R.id.login_button).setOnClickListener(this);
        subscribeObservers();

    }
    private  void setLogo() {
        requestManager.load(logo)
                .into((ImageView)findViewById(R.id.login_logo));

    }
    private void onLogInSuccess(){
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void subscribeObservers(){
        viewModel.observeAuthState().observe(this, new Observer<AuthResource<Users>>() {
            @Override
            public void onChanged(AuthResource<Users> usersAuthResource) {
                if(usersAuthResource != null){
                    switch (usersAuthResource.status){
                        case LOADING:
                            showProgressbar(true);
                            break;
                        case ERROR:
                            showProgressbar(false);
                            Toast.makeText(AuthActivity.this, usersAuthResource.message
                                    + "\nDid you enter a number between 1 and 10?", Toast.LENGTH_SHORT).show();
                            break;
                        case AUTHENTICATED:
                            showProgressbar(false);
                            Log.d(TAG, "onChanged: LOGIN SUCCESS: " + usersAuthResource.data.getEmail());
                            onLogInSuccess();
                            break;
                        case NOT_AUTHENTICATED:
                            showProgressbar(false);
                            break;
                    }
                }

            }
        });

    }

    private void showProgressbar(Boolean isVisible){
        if(isVisible){
            progressBar.setVisibility(View.VISIBLE);
        }else{
            progressBar.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login_button:
                attemptLogin();
                break;
        }

    }

    private void attemptLogin() {
        if(TextUtils.isEmpty(userId.getText().toString())){
            return;
        }
        viewModel.authenticateWithId(Integer.parseInt(userId.getText().toString()));
    }
}
