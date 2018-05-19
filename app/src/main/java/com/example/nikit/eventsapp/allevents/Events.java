package com.example.nikit.eventsapp.allevents;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.nikit.eventsapp.R;
import com.example.nikit.eventsapp.model.Event;
import com.example.nikit.eventsapp.rest.ApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Events extends AppCompatActivity {

    private List<Event> eventList;
    public static String TAG = "harsimar";
    private RecyclerView recyclerView;
    private AllEventRecyclerAdapter allEventRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        eventList = new ArrayList<>();

        recyclerView = findViewById(R.id.recycler_view);
        setUpRecycler();
        allEventRecyclerAdapter = new AllEventRecyclerAdapter();
        recyclerView.setAdapter(allEventRecyclerAdapter);

        Call<List<Event>> call = ApiClient.getOpenEventAPI().getEvents();
        call.enqueue(new Callback<List<Event>>() {
            @Override
            public void onResponse(Call<List<Event>> call, Response<List<Event>> response) {
                if(response.isSuccessful()){
                    eventList.addAll(response.body());
                    allEventRecyclerAdapter.addAll(eventList);
                    allEventRecyclerAdapter.notifyDataSetChanged();
                    Log.d(TAG,"Got all events!" + eventList.toString());
                }else{
                    Log.d(TAG,"Error fetching events! "+response.code());
                }
            }

            @Override
            public void onFailure(Call<List<Event>> call, Throwable t) {
                Log.d(TAG,"Failure! "+t.toString());

            }
        });
    }

    private void setUpRecycler() {
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(llm);

    }

}
