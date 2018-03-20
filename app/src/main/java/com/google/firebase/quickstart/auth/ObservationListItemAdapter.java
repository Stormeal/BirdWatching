package com.google.firebase.quickstart.auth;

/**
 * Created by Alex on 13-03-2018.
 */

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.quickstart.auth.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

public class ObservationListItemAdapter extends ArrayAdapter<Observation> {
    private final int resource;

    public ObservationListItemAdapter(Context context, int resource, List<Observation> objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    public ObservationListItemAdapter(Context context, int resource, Observation[] objects) {
        super(context, resource, objects);
        this.resource = resource;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, @NonNull ViewGroup parent) {
        Observation observation = getItem(position);
        String nameEnglish = observation.getNameEnglish();
        String created = observation.getCreated();
        LinearLayout observationView;
        if (convertView == null) {
            observationView = new LinearLayout(getContext());
            String inflater = Context.LAYOUT_INFLATER_SERVICE;
            LayoutInflater li = (LayoutInflater) getContext().getSystemService(inflater);
            li.inflate(resource, observationView, true);
        } else {
            observationView = (LinearLayout) convertView;
        }
        TextView titleView = observationView.findViewById(R.id.observationlist_item_NameEnglish);
        TextView authorView = observationView.findViewById(R.id.observationlist_item_created);
        titleView.setText(nameEnglish);
        authorView.setText(" d. " + created);
        return observationView;
    }
}