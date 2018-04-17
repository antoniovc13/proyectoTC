package com.tivit.talmatc.feature.traslado_carga.flight_list;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.utils.Configuration;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexzander Guillermo on 31/08/2017.
 */

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.FlightListViewHolder>
        implements ItemTouchHelperAdapter{

    private Callback mCallback;
    private List<Flight> mFlightResponseList;
    private String codeFlight;

    public FlightListAdapter(List<Flight> mOrderResponseList, String codeFlight) {
        this.mFlightResponseList = mFlightResponseList;
        this.codeFlight = codeFlight;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    @Override
    public void onItemDismiss(int position) {
        mFlightResponseList.remove(position);
        notifyItemRemoved(position);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        if (fromPosition < toPosition) {
            for (int i = fromPosition; i < toPosition; i++) {
                Collections.swap(mFlightResponseList, i, i + 1);
            }
        } else {
            for (int i = fromPosition; i > toPosition; i--) {
                Collections.swap(mFlightResponseList, i, i - 1);
            }
        }
        notifyItemMoved(fromPosition, toPosition);
    }

    public interface Callback {
        void onItemRowClicked(Flight data, int position);
    }


    @Override
    public FlightListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FlightListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_flight, parent, false));
    }

    public static class FlightListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_flight_code)      TextView tvFlightCode;
        @BindView(R.id.tv_pea)              TextView tvPEA;
        @BindView(R.id.tv_date_assignation) TextView tvDateAssignation;
        //@BindView(R.id.tv_date_work)      TextView tvDateWork;
        @BindView(R.id.tv_count_elements)   TextView tvCountElements;
        @BindView(R.id.linear_status)       View linearStatus;
        @BindView(R.id.iv_code_flight)      ImageView ivCodeFlight;


        public FlightListViewHolder(View v) {
            super(v);
            ButterKnife.bind(this, v);
        }
    }

    @Override
    public void onBindViewHolder(FlightListViewHolder holder, int position) {
        final Flight data = mFlightResponseList.get(position);

        holder.tvFlightCode.setText(data.getCode());
        holder.tvPEA.setText(data.getPea());
        holder.tvDateAssignation.setText( data.getEta() +" / "+ data.getEtd());
        //holder.tvDateWork.setText(data.getEtd());
        holder.tvCountElements.setText(data.getCountElements());
        holder.linearStatus.setBackgroundColor((data.getCountElements()!=null&&!data.getCountElements().equals(""))?ContextCompat.getColor(TivitApplication.getAppContext(), R.color.green_primary) : ContextCompat.getColor(TivitApplication.getAppContext(), R.color.red_primary));
        holder.ivCodeFlight.setImageResource(codeFlight.equals(Configuration.CODE_DEPARTURE)?R.mipmap.ic_flight_takeoff_white_24dp:R.mipmap.ic_flight_land_white_24dp);
        holder.ivCodeFlight.setBackgroundColor((data.getCountElements()!=null&&!data.getCountElements().equals(""))?ContextCompat.getColor(TivitApplication.getAppContext(), R.color.green_primary) : ContextCompat.getColor(TivitApplication.getAppContext(), R.color.red_primary));

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemRowClicked(data, position);
            }
        });

        //holder.itemView.setOn
    }

    @Override
    public int getItemCount() {
        return mFlightResponseList==null?0:mFlightResponseList.size();
    }

    public void addItems(List<Flight> repoList) {
        if(mFlightResponseList == null){
            mFlightResponseList = new ArrayList<>();
        }
        mFlightResponseList.clear();
        mFlightResponseList.addAll(repoList);
        notifyDataSetChanged();
    }

    public void addItem(int position, Flight f) {

        if(mFlightResponseList == null){
            mFlightResponseList = new ArrayList<>();
        }
        mFlightResponseList.add(position,f);
        //this.notifyItemInserted(position);
        notifyDataSetChanged();
    }

    public void updateItem(int position, String value) {

        ((Flight)mFlightResponseList.get(position)).setCountElements(value);
        notifyDataSetChanged();
    }

    public Flight getItem(int position){
        return ((Flight)mFlightResponseList.get(position));
    }

}
