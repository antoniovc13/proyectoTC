package com.tivit.talmatc.feature.traslado_carga.selected;

import android.content.BroadcastReceiver;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.GenericSpinnerAdapter;
import com.tivit.talmatc.base.generic.GenericSpinnerItem;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.base.ui.BaseFragment;
import com.tivit.talmatc.data.local.constant.ParameterEnum;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.data.local.model.User;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.feature.traslado_carga.flight.FlightActivity;
import com.tivit.talmatc.feature.traslado_carga.flight.FlightContract;
import com.tivit.talmatc.feature.traslado_carga.flight.FlightPresenter;
import com.tivit.talmatc.feature.traslado_carga.flight_list.FlightListActivity;
import com.tivit.talmatc.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class ChargeMoveFragment extends BaseFragment implements ChargeMoveContract.ChargeMoveView, View.OnClickListener{

    private ChargeMoveContract.ChargeMovePresenter mPresenter;

    //private BroadcastReceiver receiver;

    // VIEWS
    @BindView(R.id.id_tv_name)      TextView tvName;
    @BindView(R.id.id_tv_charge)    TextView tvCharge;
    @BindView(R.id.id_tv_typevehicle)  TextView tvTypeVehicle;
    @BindView(R.id.id_spi_tractor)  Spinner spiTractor;
    @BindView(R.id.id_btn_subir)    Button btnSubirElems;

    //ADAPTERS
    private GenericSpinnerAdapter adapterTractor;
    //DATA
    private List<Parameter> listTractor;
    private Parameter selectedTractor;

    private User user;

    /* ======= START FRAGMENT ======= */

    public static ChargeMoveFragment newInstance(){
        Bundle args = new Bundle();
        ChargeMoveFragment fragment = new ChargeMoveFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_traslado_carga, container, false);

        mPresenter = new ChargeMovePresenter(this);
        return view;
    }

    @Override
    protected void setUp(View view) {

        loadSpinners();
        btnSubirElems.setOnClickListener(this);

        mPresenter.onViewInitialized();
    }

    private void loadSpinners() {
        // SPINNER SITUACIONES
        listTractor = new ArrayList<>();
        List<GenericSpinnerItem> listSituacionGeneric = GenericSpinnerItem.convertFromList(listTractor);
        adapterTractor  = new GenericSpinnerAdapter(this.getActivity(), android.R.layout.simple_list_item_single_choice, listSituacionGeneric);
        adapterTractor.setHint("Seleccione Unidad");
        spiTractor.setAdapter(adapterTractor);
        spiTractor.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedTractor = (Parameter) adapterTractor.getItem(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            case R.id.id_btn_subir :
                //Util.showMessage(this.getActivity(), "id_btn_subir", Snackbar.LENGTH_LONG, Util.SNACK);
                onClickButtonUploadElements();

                break;
            default:
                break;
        }
    }

    private void onClickButtonUploadElements() {

        if( validateRegister() ) {
            mPresenter.uploadItems(
                    user.getUnidad(),
                    selectedTractor.getCode()
            );
        }
    }

    private boolean validateRegister() {
        boolean isValid = true;

        if (selectedTractor == null) {
            TextView errorText = (TextView) spiTractor.getSelectedView();
            errorText.setError("");
            errorText.setTextColor(ContextCompat.getColor(this.getContext(), R.color.error_border));
            onError("Seleccione Unidad");

            isValid = false;
        }

        return isValid;
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

    }

    @Override
    public void showProgressError(String message) {

    }

    @Override
    public void openSelectFlightActivity() {
        Bundle bundle = new Bundle();
        //bundle.putString(ReconnectRegisterActivity.VEHICLE_TYPE, user.getUnidad());
        //bundle.putString(ReconnectRegisterActivity.VEHICLE_VALUE,selectedTractor.getCode() );
        next(bundle, FlightActivity.class);

    }

    @Override
    public void updateTractorSpinner(List<Parameter> list) {
        Timber.d("lista Spinner:"+list.size());
        listTractor.clear();
        listTractor.addAll(list);

        List<GenericSpinnerItem> listGeneric = GenericSpinnerItem.convertFromList(listTractor);
        adapterTractor.getData().clear();
        adapterTractor.setData(listGeneric);
        adapterTractor.notifyDataSetChanged();
    }

    @Override
    public void updateUser(Authorization authorization) {
        Timber.d("updateUser:"+authorization);
        Timber.d("updateUser:"+authorization!=null?authorization.getUser().toString():"");

        user = authorization.getUser();
        tvTypeVehicle.setText(authorization.getUser().getUnidad());
        tvName.setText( authorization.getUser().getFullName() );
        tvCharge.setText( authorization.getUser().getRole() );
    }
}