package com.example.android.dagger.ui.auth;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import com.bumptech.glide.RequestManager;
import com.example.android.dagger.R;
import com.example.android.dagger.models.Users;
import com.example.android.dagger.viewmodels.ViewModelProviderFactory;


import javax.inject.Inject;

import dagger.android.support.DaggerAppCompatActivity;

public class AuthActivity extends DaggerAppCompatActivity implements View.OnClickListener {
    private static final String TAG = "AuthActivity";
    private AuthViewModel viewModel;

    private EditText userId;

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
        findViewById(R.id.login_button).setOnClickListener(this);
        subscribeObservers();

    }
    private  void setLogo() {
        requestManager.load(logo)
                .into((ImageView)findViewById(R.id.login_logo));

    }

    private void subscribeObservers(){
        viewModel.observeUser().observe(this, new Observer<Users>() {
            @Override
            public void onChanged(Users users) {
                if(users != null){
                    Log.e(TAG, "onChanged: e e e e --------------------------" + users.getUsername() );
                }
            }
        });
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
        viewModel.authenticateUser(Integer.parseInt(userId.getText().toString()));
    }
}
