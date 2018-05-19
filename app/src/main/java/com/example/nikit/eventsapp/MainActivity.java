package com.example.nikit.eventsapp;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.nikit.eventsapp.allevents.Events;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        Log.d("harsimarSingh","harsimar starting");
//
//        ApiClient.getOpenEventAPI().login(new Login("hey@hey.hey","heyheyhey"))
//                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
//                .subscribe(loginResponse -> {
//                    Log.d("harsimarSingh","saved "+loginResponse.getAccessToken());
//                    Timber.d("Saved token and logged in successfully "+ loginResponse.getAccessToken());
//                }, throwable -> {
//                    Log.d("harsimarSingh","error  "+throwable.toString());
//                    Timber.e(throwable.toString());
//                });

        Intent i = new Intent(MainActivity.this, Events.class);
        startActivity(i);

    }
}
