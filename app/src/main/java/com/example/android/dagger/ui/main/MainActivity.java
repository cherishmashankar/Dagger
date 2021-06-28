package com.example.android.dagger.ui.main;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.android.dagger.BaseActivity;
import com.example.android.dagger.R;
import com.example.android.dagger.ui.main.profile.ProfileFragment;

public class MainActivity extends BaseActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        testFragment();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){
            case R.id.log_out:
                sessionManager.logOut();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void testFragment(){
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.main, new ProfileFragment())
                .commit();
    }
}
