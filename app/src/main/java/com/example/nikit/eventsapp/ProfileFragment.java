package com.example.nikit.eventsapp;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.nikit.eventsapp.model.Event;
import com.example.nikit.eventsapp.model.EventList;
import com.example.nikit.eventsapp.model.Profile;
import com.example.nikit.eventsapp.rest.ApiClient;
import com.example.nikit.eventsapp.rest.ApiInterface;
import com.example.nikit.eventsapp.utils.JWTUtils;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ProfileFragment extends Fragment {

    private static final String app="application/vnd.api+json";
    private List<Event> eventList;
    private RecyclerView recyclerView;
    private EventsRecyclerAdapter eventsRecyclerAdapter;
    private ProgressBar progressBar;
    private String EMAIL = "";
    private String FIRST_NAME = "";
    private String TOKEN = "";



    public ProfileFragment(){

    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            EMAIL = getArguments().getString("email");
            TOKEN = getArguments().getString("TOKEN");
            Log.d("harsimarSingh",TOKEN);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        final TextView email = (TextView)view.findViewById(R.id.email);

        final TextView firstName = (TextView)view.findViewById(R.id.first_name);

        int id  = 0;
        try {
            id = JWTUtils.getIdentity(format(TOKEN));
        } catch (JSONException e) {
            e.printStackTrace();
        }

        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<Profile> call = apiService.getProfileDetails(id);
        call.enqueue(new Callback<Profile>() {
            @Override
            public void onResponse(Call<Profile> call, Response<Profile> response) {
                if(response.isSuccessful()){
                    Log.d("harsimarSingh","Response Success ");

                    email.setText(response.body().getEmail());
                    firstName.setText(response.body().getFirstName());


                    Log.d("harsimarSingh","Fetched Profile Data ");
                }else {
                    Log.d("harsimarSingh","Not Successful "+response.code());
                }
            }

            @Override
            public void onFailure(Call<Profile> call, Throwable t) {
                Log.d("harsimarSingh","Failure "+t.toString());
            }
        });

        return view;
    }

    private String format (String h){
        h = h.replaceAll("JWT ","");
        return h;
    }


}

