package com.example.nikit.eventsapp;


import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.nikit.eventsapp.model.Login;
import com.example.nikit.eventsapp.rest.ApiClient;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d("harsimarSingh","harsimar starting");

        ApiClient.getOpenEventAPI().login(new Login("hey@hey.hey","heyheyhey"))
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(loginResponse -> {
                    Log.d("harsimarSingh","saved "+loginResponse.getAccessToken());
                    Timber.d("Saved token and logged in successfully "+ loginResponse.getAccessToken());
                }, throwable -> {
                    Log.d("harsimarSingh","error  "+throwable.toString());
                    Timber.e(throwable.toString());
                });


    }
}
