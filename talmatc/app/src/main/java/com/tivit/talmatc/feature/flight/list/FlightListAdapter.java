package com.tivit.talmatc.feature.flight.list;

import android.content.BroadcastReceiver;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.base.ui.BaseViewHolder;
import com.tivit.talmatc.data.local.constant.OrderEnum;
import com.tivit.talmatc.data.local.model.Order;
import com.tivit.talmatc.utils.CommonUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by Alexzander Guillermo on 31/08/2017.
 */

public class FlightListAdapter extends RecyclerView.Adapter<FlightListAdapter.FlightListViewHolder> {

    private Callback mCallback;
    private List<Flight> mFlightResponseList;

    public FlightListAdapter(List<Flight> mOrderResponseList) {
        this.mFlightResponseList = mFlightResponseList;
    }

    public void setCallback(Callback callback) {
        mCallback = callback;
    }

    public interface Callback {
        void onItemRowClicked(Flight data);
    }

    @Override
    public FlightListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new FlightListViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_view_flight, parent, false));
    }

    public static class FlightListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.tv_flight_code) TextView tvFlightCode;
        @BindView(R.id.tv_pea) TextView tvPEA;
        @BindView(R.id.tv_date_assignation) TextView tvDateAssignation;
        @BindView(R.id.tv_date_work) TextView tvDateWork;
        @BindView(R.id.tv_count_elements) TextView tvCountElements;
        @BindView(R.id.linear_status) View linearStatus;


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
        holder.tvDateAssignation.setText(data.getEta());
        holder.tvDateWork.setText(data.getEtd());
        holder.tvCountElements.setText(data.getCountElements());
        //holder.linearStatus.setBackgroundColor(!TextUtils.equals(data.getStatusCode(), OrderEnum.STATE_CARGADO.getValue()) ? ContextCompat.getColor(TivitApplication.getAppContext(), R.color.green_primary) : ContextCompat.getColor(TivitApplication.getAppContext(), R.color.red_primary));


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallback.onItemRowClicked(data);
            }
        });
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

}
