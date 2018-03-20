package com.tivit.talmatc.feature.flight.list;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.RoundedImageView;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.feature.flight.list.FlightListContract;
import com.tivit.talmatc.feature.flight.list.FlightListPresenter;
import com.tivit.talmatc.utils.Util;
import com.vlonjatg.progressactivity.ProgressRelativeLayout;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class FlightListActivity extends BaseActivity implements FlightListContract.FlightListView, FlightListAdapter.Callback , View.OnClickListener {

    private FlightListContract.FlightListPresenter mPresenter;
    public static String[] VUELOS ={
            "LA 2071",
            "LA 2073",
            "LA 2074",
            "LA 2081",
            "LA 2086",
            "LA 2087",
            "LA 3011",
            "LA 3012"
    };
    // VIEWS
    @BindView(R.id.actv_vuelo)
    AutoCompleteTextView actvVuelo;
    @BindView(R.id.ib_findvuelo)
    ImageButton ibFindVuelo;

    @BindView(R.id.rv_list_flight)
    RecyclerView mRecyclerView;

    //ADAPTERS
    private FlightListAdapter mFlightListAdapter;
    private LinearLayoutManager mLayoutManager;
    //@BindView(R.id.progress_loading) ProgressRelativeLayout progressRelative;

    private static final String FlightList_ARRIVE = "FlightList_ARRIVE";
    private static final String FlightList_DEPARTURE = "FlightList_DEPARTURE";
    /* ======= START ACTIVITY ======= */

    @Override
    protected void createPresenter() {
        mPresenter = new FlightListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flight_list);
        createPresenter();
        setUp();
    }

    @Override
    protected void setUp() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        ArrayAdapter<String> adaptador =
                new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, VUELOS);
        actvVuelo.setAdapter(adaptador);

        List l = new ArrayList();
        Flight f = new Flight();
        f.setDescripcion("LA 2071");
        f.setDrawable(R.mipmap.ic_flight_land_black_24dp);
        l.add(f);
        f = new Flight();
        f.setDescripcion("LA 2072");
        f.setDrawable(R.mipmap.ic_flight_takeoff_black_24dp);
        l.add(f);
        f = new Flight();
        f.setDescripcion("LA 2073");
        f.setDrawable(R.mipmap.ic_flight_takeoff_black_24dp);
        l.add(f);

        f = new Flight();
        f.setDescripcion("LA 3012");
        f.setDrawable(R.mipmap.ic_flight_land_black_24dp);
        l.add(f);

        f = new Flight();
        f.setDescripcion("LA 3013");
        f.setDrawable(R.mipmap.ic_flight_land_black_24dp);
        l.add(f);

        ibFindVuelo.setOnClickListener(this);


        mFlightListAdapter = new FlightListAdapter(new ArrayList<Flight>());
        mFlightListAdapter.setCallback(this);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFlightListAdapter);


        mPresenter.onViewInitialized();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_view_flight_list, menu);
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
            case R.id.ib_findvuelo :
                //addFlight();
                mPresenter.addFlight(actvVuelo.getText().toString());
                break;
        }
        //refreshActivity();
    }


    @Override
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }


    @Override
    public void showProgressContent() {
        //progressRelative.showContent();
        if( mFlightListAdapter.getItemCount() == 0 ) {
            showProgressEmpty();
        }
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
    public void updateFlightList(List<Flight> flightList) {
        mFlightListAdapter.addItems(flightList);
    }

    @Override
    public void addFlightToList(int position, Flight flight) {
        //ocultar teclado
        View view = this.getCurrentFocus();
        view.clearFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
        //agregar a lista
        actvVuelo.setText("");
        Timber.d("activity - addFlightToList - "+flight);
        //progressRelative.showContent();
        Timber.d("activity - addFlightToList - paso1");
        mFlightListAdapter.addItem(position,flight);
        Timber.d("activity - addFlightToList - paso2");
    }

    @Override
    public void onItemRowClicked(Flight data) {
        Util.showMessage(this, data.getCode(), Snackbar.LENGTH_LONG, Util.SNACK);
    }
}
