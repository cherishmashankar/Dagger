package com.example.android.dagger.ui.main.profile;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import com.example.android.dagger.R;
import com.example.android.dagger.models.Users;
import com.example.android.dagger.ui.auth.AuthResource;
import com.example.android.dagger.viewmodels.ViewModelProviderFactory;

import javax.inject.Inject;

import dagger.android.support.DaggerFragment;

public class ProfileFragment extends DaggerFragment {
    private static final String TAG = "ProfileFragment";
    private TextView email, username, website;

    private ProfileViewModel viewModel;

    @Inject
    ViewModelProviderFactory viewModelProviderFactory;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Toast.makeText(getActivity(), "Profile", Toast.LENGTH_LONG).show();
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d(TAG, "onViewCreated: ProfileFragment. " + this);
        email = view.findViewById(R.id.email);
        username = view.findViewById(R.id.username);
        website = view.findViewById(R.id.website);

       viewModel = ViewModelProviders.of(this, viewModelProviderFactory).get(ProfileViewModel.class);
       subscribeObservers();
    }


    private void subscribeObservers(){
        viewModel.getAuthenticatedUser().removeObservers(getViewLifecycleOwner());
        viewModel.getAuthenticatedUser().observe(getViewLifecycleOwner(), new Observer<AuthResource<Users>>() {
            @Override
            public void onChanged(AuthResource<Users> userAuthResource) {
                if(userAuthResource != null){
                    switch (userAuthResource.status){


                        case AUTHENTICATED:{
                            Log.d(TAG, "onChanged: ProfileFragment: AUTHENTICATED... " +
                                    "Authenticated as: " + userAuthResource.data.getEmail());
                            setUserDetails(userAuthResource.data);
                            break;
                        }

                        case ERROR:{
                            Log.d(TAG, "onChanged: ProfileFragment: ERROR...");
                            setErrorDetails(userAuthResource.message);
                            break;
                        }
                    }
                }
            }
        });
    }

    private void setErrorDetails(String message){
        email.setText(message);
        username.setText("error");
        website.setText("error");
    }

    private void setUserDetails(Users user){
        email.setText(user.getEmail());
        username.setText(user.getUsername());
        website.setText(user.getWebsite());
    }
}
