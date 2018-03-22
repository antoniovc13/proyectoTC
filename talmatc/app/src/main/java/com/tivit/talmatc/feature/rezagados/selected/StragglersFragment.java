package com.tivit.talmatc.feature.rezagados.selected;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.GenericSpinnerItem;
import com.tivit.talmatc.base.ui.BaseFragment;
import com.tivit.talmatc.data.local.model.Flight;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Antonio.Valdivieso on 19/03/2018.
 */

public class StragglersFragment extends BaseFragment implements StragglersContract.StragglersView, View.OnClickListener{
    //presenter
    private StragglersContract.StragglersPresenter mPresenter;

    // VIEWS
    @BindView(R.id.actv_vuelo)              AutoCompleteTextView actvVuelo;
    @BindView(R.id.id_btn_agregarvuelo)     Button btnAgregarVuelo;
    @BindView(R.id.tv_msj)                  TextView tvMensaje;

    //ADAPTERS
    //private FlightListAdapter2 mFlightListAdapter;
    private LinearLayoutManager mLayoutManager;
    ArrayAdapter<String> adaptador;
    //@BindView(R.id.progress_loading) ProgressRelativeLayout progressRelative;

    //DATA
    //private List<Flight> listFlight;
    private int positionFlight;
    private String codeFlight;


     /* ======= START FRAGMENT ======= */

    public static StragglersFragment newInstance(){
        Bundle args = new Bundle();
        StragglersFragment fragment = new StragglersFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_rezagados, container, false);

        mPresenter = new StragglersPresenter(this);
        return view;
    }

    @Override
    protected void setUp(View view) {

        adaptador = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        actvVuelo.setAdapter(adaptador);
        //btnSubirElems.setOnClickListener(this);
        mPresenter.onViewInitialized();
    }



    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            //case R.id.ib_findvuelo :
            case R.id.id_btn_agregarvuelo :

                //addFlight();
                mPresenter.addFlight(actvVuelo.getText().toString());
                break;
        }
        //refreshActivity();
    }


    @Override
    public void onDestroyView() {
        mPresenter.onDetachView();
        super.onDestroyView();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
        super.onPause();
    }


    @Override
    public void showProgressContent() {
        //progressRelative.showContent();
        /*
        if( mFlightListAdapter.getItemCount() == 0 ) {
            showProgressEmpty();
        }
        */
    }

    @Override
    public void showProgressLoading() {

        //progressRelative.showLoading();
    }

    @Override
    public void showProgressEmpty() {
        //progressRelative.showEmpty(emptyDrawable, "Sin Resultados", "No se encontraron Vuelos");
    }

    @Override
    public void showProgressError(String message) {
        //progressRelative.showError(errorDrawable, "Error", "Se produjo un error "+ message, "Reintentar", null);
    }


    @Override
    public void updateListAutocomplete(List<Flight> flightList) {
        Timber.d("activity - updateListAutocomplete - ini");
        adaptador.addAll(GenericSpinnerItem.convertFromFlight(flightList));
        actvVuelo.setAdapter(adaptador);
        Timber.d("activity - updateListAutocomplete - fin");
    }




}
