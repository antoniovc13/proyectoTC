package com.tivit.talmatc.feature.flight.list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio.Valdivieso on 08/03/2018.
 */

public class AutoCompleteFlightAdapter extends ArrayAdapter<Flight> {


    private final List<Flight> flights;
    public List<Flight> filteredFlight = new ArrayList<>();

    public AutoCompleteFlightAdapter(Context context, List<Flight> list) {
        super(context, 0, list);
        this.flights = list;
    }

    @Override
    public int getCount() {
        return filteredFlight.size();
    }

    @Override
    public Filter getFilter() {
        return new FlightFilter(this, flights);
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item from filtered list.
        Flight flight = filteredFlight.get(position);

        // Inflate your custom row layout as usual.
        LayoutInflater inflater = LayoutInflater.from(getContext());
        convertView = inflater.inflate(R.layout.row_flight, parent, false);

        TextView tvName = (TextView) convertView.findViewById(R.id.row_description);
        ImageView ivIcon = (ImageView) convertView.findViewById(R.id.row_icon);
        tvName.setText(flight.getDescripcion());
        ivIcon.setImageResource(flight.getDrawable());

        return convertView;
    }
}
