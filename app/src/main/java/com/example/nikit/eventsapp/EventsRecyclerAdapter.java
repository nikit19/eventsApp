package com.example.nikit.eventsapp;

/**
 * Created by harsimar on 20/05/18.
 */

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.nikit.eventsapp.model.Event;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class EventsRecyclerAdapter extends RecyclerView.Adapter<EventsRecyclerAdapter.EventViewHolder> {

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventNameTv,eventStartsAt;
        ImageView eventImage;

        EventViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.all_events_card);
            eventNameTv = (TextView)itemView.findViewById(R.id.all_events_card_event_name);
            eventStartsAt = (TextView)itemView.findViewById(R.id.all_events_card_starts_at);
            eventImage = (ImageView)itemView.findViewById(R.id.all_events_card_image);
        }
    }
    List<Event> events;

    EventsRecyclerAdapter (){
        events = new ArrayList<>();
    }
    public void addAll(List<Event> eventList){
        this.events.addAll(eventList);
    }

    @NonNull
    @Override
    public EventsRecyclerAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card_events, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(v);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EventsRecyclerAdapter.EventViewHolder holder, int position) {
        String startAt=events.get(position).getAttributes().getStartsAt();

        holder.eventNameTv.setText(events.get(position).getAttributes().getName());
        holder.eventStartsAt.setText(startAt.substring(0,9));

        Picasso.get()
                .load("https://www.google.co.in/url?sa=i&source=images&cd=&ved=&url=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fflower%2F&psig=AOvVaw3o5ZzPNCQdv55Ba6TWG9i7&ust=1526915030812948")
                .resize(100, 100)
                .centerCrop()
                .into(holder.eventImage);


        Log.d("harsimarSingh","Setting "+events.get(position).getAttributes().getOrganizerDescription());
    }

    @Override
    public void onAttachedToRecyclerView(@NonNull RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return events.size();
    }
}