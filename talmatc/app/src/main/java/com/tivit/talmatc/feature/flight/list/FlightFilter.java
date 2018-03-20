package com.tivit.talmatc.feature.flight.list;

import android.widget.Filter;

import com.tivit.talmatc.data.local.model.Flight;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Antonio.Valdivieso on 08/03/2018.
 */

public class FlightFilter extends Filter {

    AutoCompleteFlightAdapter adapter;
    List<Flight> originalList;
    List<Flight> filteredList;

    public FlightFilter(AutoCompleteFlightAdapter adapter, List<Flight> originalList) {
        super();
        this.adapter = adapter;
        this.originalList = originalList;
        this.filteredList = new ArrayList<>();
    }

    @Override
    protected FilterResults performFiltering(CharSequence constraint) {
        filteredList.clear();
        final FilterResults results = new FilterResults();

        if (constraint == null || constraint.length() == 0) {
            filteredList.addAll(originalList);
        } else {
            final String filterPattern = constraint.toString().toLowerCase().trim();

            // Your filtering logic goes in here
            for (final Flight flight : originalList) {
                if (flight.getDescripcion().toLowerCase().contains(filterPattern)) {
                    filteredList.add(flight);
                }
            }
        }
        results.values = filteredList;
        results.count = filteredList.size();
        return results;
    }

    @Override
    protected void publishResults(CharSequence constraint, FilterResults results) {
        adapter.filteredFlight.clear();
        adapter.filteredFlight.addAll((List) results.values);
        adapter.notifyDataSetChanged();
    }
}
