package com.tivit.talmatc.feature.traslado_carga2.flight;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.GenericSpinnerAdapter;
import com.tivit.talmatc.base.generic.GenericSpinnerItem;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.local.model.Parameter;
import com.tivit.talmatc.feature.traslado_carga2.flight_list.FlightListActivity2;
import com.tivit.talmatc.utils.Configuration;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class FlightActivity2 extends BaseActivity implements FlightContract2.FlightView, View.OnClickListener {

    //PRESENTER
    private FlightContract2.FlightPresenter mPresenter;


    // VIEWS
    @BindView(R.id.id_spi_pointini)         Spinner     spiPointinit;
    @BindView(R.id.iv_flight_departure)     ImageView   ivFlightDeparture;
    @BindView(R.id.iv_flight_arrive)        ImageView   ivFlightArrive;
    @BindView(R.id.tv_flight_departure)     TextView    tvFlightDeparture;
    @BindView(R.id.tv_flight_arrive)        TextView    tvFlightArrive;

    //ADAPTERS
    private GenericSpinnerAdapter adapter;
    //DATA
    private List<Parameter> listPointInit;
    private Parameter selectedPointInit;

    /* ======= START ACTIVITY ======= */

    @Override
    protected void createPresenter() {
        mPresenter = new FlightPresenter2(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flight);
        setTitle("Selecciona Tipo de Vuelo");
        setUp();
    }


    @Override
    protected void setUp() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        loadSpinners();

        ivFlightDeparture.setOnClickListener(this);
        ivFlightArrive.setOnClickListener(this);
        tvFlightDeparture.setOnClickListener(this);
        tvFlightArrive.setOnClickListener(this);

        mPresenter.onViewInitialized();
    }

    private void loadSpinners() {
        // SPINNER
        listPointInit = new ArrayList<>();
        List<GenericSpinnerItem> listSituacionGeneric = GenericSpinnerItem.convertFromList(listPointInit);
        adapter  = new GenericSpinnerAdapter(this, android.R.layout.simple_list_item_single_choice, listSituacionGeneric);
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_flight, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
                /*
            case R.id.action_dowload:
                Archivo file = new Archivo();
                file.setPath(order.getCode());
                file.setName(order.getCode() + ".pdf");

                AdjuntoFragment adjuntoFragment = AdjuntoFragment.newInstance(file);
                adjuntoFragment.show(getSupportFragmentManager(), "Dialog Fragment");
                return true;
            case R.id.action_refresh:
                swipeRefreshLayout.setRefreshing(true);
                callApiGetorderByCode();
                return true;
                */
            default:
                return super.onOptionsItemSelected(item);
        }
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
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }

    @Override
    public void openNextActivity(String flight) {
        Timber.d("openNextActivity - "+flight);
        Bundle bundle = new Bundle();
        bundle.putString(Configuration.CODE_FLIGHT,     flight);
        next(bundle, FlightListActivity2.class, false);
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
