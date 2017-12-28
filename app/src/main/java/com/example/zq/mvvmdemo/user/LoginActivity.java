package com.example.zq.mvvmdemo.user;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.example.zq.mvvmdemo.R;
import com.example.zq.mvvmdemo.SnackbarMessage;
import com.example.zq.mvvmdemo.ViewModelFactory;
import com.example.zq.mvvmdemo.databinding.ActivityLoginBinding;
import com.example.zq.mvvmdemo.utils.SnackbarUtils;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ViewModelFactory factory = ViewModelFactory.getInstance(getApplication());
        final LoginViewModel loginViewModel = ViewModelProviders.of(this, factory).get(LoginViewModel.class);

        final ActivityLoginBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_login);
        binding.setViewmodel(loginViewModel);

        loginViewModel.getmOpenUserList().observe(this, new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                Intent intent = new Intent(LoginActivity.this, UserListActivity.class);
                intent.putExtra("token", s);
                startActivity(intent);
                finish();
            }
        });

        loginViewModel.getSnackbarMessage().observe(this, new SnackbarMessage.SnackbarObserver() {
            @Override
            public void onNewMessage(String message) {
                SnackbarUtils.showSnackbar(binding.getRoot(), message);
            }
        });

        findViewById(R.id.submit).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginViewModel.login();
            }
        });
    }

}
