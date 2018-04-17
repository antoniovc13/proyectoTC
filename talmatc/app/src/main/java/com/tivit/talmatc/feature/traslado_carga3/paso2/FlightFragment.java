package com.tivit.talmatc.feature.traslado_carga3.paso2;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Spinner;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.GenericSpinnerAdapter;
import com.tivit.talmatc.base.generic.GenericSpinnerItem;
import com.tivit.talmatc.base.ui.BaseFragment;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.feature.main.MainActivity;
import com.tivit.talmatc.feature.traslado_carga3.OnChangeTab;
import com.tivit.talmatc.utils.Configuration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class FlightFragment extends BaseFragment implements FlightContract.FlightView, View.OnClickListener {

    //PRESENTER
    private FlightContract.FlightPresenter mPresenter;
    private OnChangeTab onChangeTab;

    // VIEWS
    @BindView(R.id.id_spi_pointini)         Spinner     spiPointinit;
    /*
    @BindView(R.id.iv_flight_departure)     ImageView   ivFlightDeparture;
    @BindView(R.id.iv_flight_arrive)        ImageView   ivFlightArrive;
    @BindView(R.id.tv_flight_departure)     TextView    tvFlightDeparture;
    @BindView(R.id.tv_flight_arrive)        TextView    tvFlightArrive;
    */
    @BindView(R.id.ly_flight_arrive)        CardView cvFlightArrive;
    @BindView(R.id.ly_flight_departure)     CardView cvFlightDeparture;
    //ADAPTERS
    private GenericSpinnerAdapter adapter;
    //DATA
    private List<Parameter> listPointInit;
    private Parameter selectedPointInit;


    /* ======= START FRAGMENT ======= */
    public static FlightFragment newInstance(int tabSelected){
        Bundle args = new Bundle();
        args.putInt(Configuration.TAB_SELECTED, tabSelected);
        FlightFragment fragment = new FlightFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_flight, container, false);

        mPresenter = new FlightPresenter(this);
        return view;
    }

    @Override
    protected void setUp(View view) {

/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
        loadSpinners();

        /*
        ivFlightDeparture.setOnClickListener(this);
        ivFlightArrive.setOnClickListener(this);
        tvFlightDeparture.setOnClickListener(this);
        tvFlightArrive.setOnClickListener(this);
        */
        cvFlightArrive.setOnClickListener(this);
        cvFlightDeparture.setOnClickListener(this);

        mPresenter.onViewInitialized();
    }

    private void loadSpinners() {
        // SPINNER
        listPointInit = new ArrayList<>();
        List<GenericSpinnerItem> listSituacionGeneric = GenericSpinnerItem.convertFromList(listPointInit);
        adapter  = new GenericSpinnerAdapter(this.getContext(), android.R.layout.simple_list_item_single_choice, listSituacionGeneric);
        adapter.setHint("Seleccione Punto de Inicio");
        spiPointinit.setAdapter(adapter);
        spiPointinit.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedPointInit = (Parameter) adapter.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }


    @Override
    public void onAttach(Context context){
        super.onAttach(context);
        MainActivity mainActivity;
        Timber.d("onAttach: "+context);
        if  (context instanceof OnChangeTab){
            onChangeTab = (OnChangeTab) context;
            Timber.d("onAttach onChangeTab: "+onChangeTab);
        }else{
            throw new RuntimeException(context.toString()+"must implement OrderContract");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        onChangeTab = null;
    }
    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.iv_flight_arrive :
                //Util.showMessage(this, "iv_flight_arrive", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(Configuration.CODE_ARRIVE);
                break;
            case R.id.iv_flight_departure:
                //Util.showMessage(this, "iv_flight_departure", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(Configuration.CODE_DEPARTURE);
                break;
            case R.id.tv_flight_arrive :
                //Util.showMessage(this, "tv_flight_arrive", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(Configuration.CODE_ARRIVE);
                break;
            case R.id.tv_flight_departure :
                //Util.showMessage(this, "tv_flight_departure", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(Configuration.CODE_DEPARTURE);
                break;
            case R.id.ly_flight_departure :
                //Util.showMessage(this, "ly_flight_departure", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(Configuration.CODE_DEPARTURE);
                break;
            case R.id.ly_flight_arrive :
                //Util.showMessage(this, "ly_flight_arrive", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(Configuration.CODE_ARRIVE);
                break;
        }
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetachView();
        super.onDestroyView();
    }

    @Override
    public void openNextActivity(String flight) {
        Timber.d("openNextActivity - "+flight);
/*
        Bundle bundle = new Bundle();
        bundle.putString(Configuration.CODE_FLIGHT,     flight);
        next(bundle, FlightListFragment.class);
*/
        onChangeTab.goToFlightList(flight);
    }

    @Override
    public void updatePointInitSpinner(List<Parameter> list) {
        Timber.d("lista Spinner:"+list.size());
        listPointInit.clear();
        listPointInit.addAll(list);

        List<GenericSpinnerItem> listGeneric = GenericSpinnerItem.convertFromList(listPointInit);
        adapter.getData().clear();
        adapter.setData(listGeneric);
        adapter.notifyDataSetChanged();
    }


}
