package com.example.nikit.eventsapp.allevents;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.nikit.eventsapp.R;
import com.example.nikit.eventsapp.model.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by harsimar on 19/05/18.
 */

public class AllEventRecyclerAdapter extends RecyclerView.Adapter<AllEventRecyclerAdapter.EventViewHolder> {

    public static class EventViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextView eventNameTv;

        EventViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.all_events_card);
            eventNameTv = (TextView)itemView.findViewById(R.id.all_events_card_event_name);
        }
    }
    List<Event> events;

    AllEventRecyclerAdapter (){
        events = new ArrayList<>();
    }
    public void addAll(List<Event> eventList){
        this.events.addAll(eventList);
    }

    @NonNull
    @Override
    public AllEventRecyclerAdapter.EventViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.content_all_event, parent, false);
        EventViewHolder eventViewHolder = new EventViewHolder(v);
        return eventViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull AllEventRecyclerAdapter.EventViewHolder holder, int position) {
        holder.eventNameTv.setText(events.get(position).getName());
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
