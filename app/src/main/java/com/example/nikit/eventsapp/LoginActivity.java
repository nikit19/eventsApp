package com.example.nikit.eventsapp;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.nikit.eventsapp.model.Login;
import com.example.nikit.eventsapp.model.LoginResponse;
import com.example.nikit.eventsapp.rest.ApiClient;
import com.example.nikit.eventsapp.rest.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {
    ApiInterface apiService;
    EditText usernameEt,passwordEt;
    Button loginBtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        apiService = ApiClient.getClient().create(ApiInterface.class);
        usernameEt= findViewById(R.id.username_edit_text);
        passwordEt=findViewById(R.id.password_edit_text);
        loginBtn=findViewById(R.id.login_button);
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sendPost(usernameEt.getText().toString(),passwordEt.getText().toString());
                sendPost("hey@hey.hey","heyheyhey");
            }
        });

    }
    public void sendPost(String title, String body) {
        Login login=new Login(title.trim(),body.trim()) ;
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<LoginResponse> call = apiService.login(login);
        call.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse>call, Response<LoginResponse> response) {
               if(response.isSuccessful()) {
                   String accessToken = response.body().getAccessToken();
                    Log.d("harsimarSingh", "success" + accessToken);

                   Toast.makeText(LoginActivity.this, "post submitted to API. " + accessToken, Toast.LENGTH_LONG).show();
               }
               else{
                   Log.d("harsimarSingh", "error");
                   Toast.makeText(LoginActivity.this, "post submitted to API with error. "+response.code()+"    " +response.errorBody(), Toast.LENGTH_LONG).show();

               }
            }

            @Override
            public void onFailure(Call<LoginResponse>call, Throwable t) {
                // Log error here since request failed
               // Log.e(TAG, t.toString());
                Log.d("harsimarSingh", "failure");
                       Toast.makeText(getApplicationContext(),"Unable to submit post to API." ,Toast.LENGTH_LONG).show();

            }
        });
    }
}
