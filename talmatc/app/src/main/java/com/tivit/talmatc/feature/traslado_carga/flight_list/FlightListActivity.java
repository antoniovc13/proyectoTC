package com.tivit.talmatc.feature.traslado_carga.flight_list;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.GenericSpinnerItem;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.feature.dialog.DialogComponentNumeric1_4;
import com.tivit.talmatc.utils.Configuration;
import com.tivit.talmatc.utils.Util;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class FlightListActivity extends BaseActivity
        implements FlightListContract.FlightListView, FlightListAdapter.Callback , View.OnClickListener,
                DialogComponentNumeric1_4.OnSimpleDialogListener{

    //presenter
    private FlightListContract.FlightListPresenter mPresenter;

    // VIEWS
    @BindView(R.id.actv_vuelo)              AutoCompleteTextView    actvVuelo;
    @BindView(R.id.id_btn_agregarvuelo)     Button                  btnAgregarVuelo;
    @BindView(R.id.tv_msj)                  TextView                tvMensaje;
    @BindView(R.id.rv_list_flight)          RecyclerView            mRecyclerView;
    /*
    @BindView(R.id.swpRefresh)
    SwipeRefreshLayout swipeRefreshLayout;
    */

    //FlightListSwipeController swipeController;
    ItemTouchHelper.Callback callback;

    //ADAPTERS
    private FlightListAdapter mFlightListAdapter;
    private LinearLayoutManager mLayoutManager;
    ArrayAdapter<String> adaptador;
    //@BindView(R.id.progress_loading) ProgressRelativeLayout progressRelative;

    //DIALOG
    private DialogComponentNumeric1_4 dialogComponentNumeric1_4;

    //DATA
    private List<Flight> listFlight;
    private int positionFlight;
    private String codeFlight;


    /* ======= START ACTIVITY ======= */

    @Override
    protected void createPresenter() {
        mPresenter = new FlightListPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flight_list);
        setTitle("Seleccionar Vuelos");
        //createPresenter();
        setUp();
    }

    @Override
    protected void setUp() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        Bundle extras = getIntent().getExtras();
        codeFlight = extras.getString(Configuration.CODE_FLIGHT);
        Timber.d("setUp - "+codeFlight);

        adaptador = new ArrayAdapter<String>(this, android.R.layout.simple_dropdown_item_1line, new ArrayList<>());
        actvVuelo.setAdapter(adaptador);

        //ibFindVuelo.setOnClickListener(this);
        btnAgregarVuelo.setOnClickListener(this);

        listFlight = new ArrayList<Flight>();
        mFlightListAdapter = new FlightListAdapter(listFlight, codeFlight );
        mFlightListAdapter.setCallback(this);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFlightListAdapter);


        //se agrega el control de desplazamiento a la lista
        /*
        swipeController = new FlightListSwipeController();
        ItemTouchHelper itemTouchhelper = new ItemTouchHelper(swipeController);
        itemTouchhelper.attachToRecyclerView(mRecyclerView);
*/
        callback = new SimpleItemTouchHelperCallback(mFlightListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
/*
        swipeRefreshLayout = findViewById(R.id.swpRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(this);
*/
        //dialogo
        dialogComponentNumeric1_4 = new DialogComponentNumeric1_4();

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
            //case R.id.ib_findvuelo :
            case R.id.id_btn_agregarvuelo :

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

        if(flightList.isEmpty()){
            tvMensaje.setVisibility(View.VISIBLE);
            tvMensaje.setText("No se encontraron elementos");
        }else{
            tvMensaje.setVisibility(View.GONE);
            tvMensaje.setText("");
        }
        mFlightListAdapter.addItems(flightList);
    }

    @Override
    public void updateListAutocomplete(List<Flight> flightList) {
        Timber.d("activity - updateListAutocomplete - ini");
        adaptador.addAll(GenericSpinnerItem.convertFromFlight(flightList));
        actvVuelo.setAdapter(adaptador);
        Timber.d("activity - updateListAutocomplete - fin");
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
        //Timber.d("activity - addFlightToList - "+flight);
        //progressRelative.showContent();
        //Timber.d("activity - addFlightToList - paso1");
        mFlightListAdapter.addItem(position,flight);
        //Timber.d("activity - addFlightToList - paso2");
        mLayoutManager.scrollToPosition(position);

        tvMensaje.setVisibility(View.GONE);
        tvMensaje.setText("");


    }

    @Override
    public void onItemRowClicked(Flight data, int position) {
        //Util.showMessage(this, data.getCode(), Snackbar.LENGTH_LONG, Util.SNACK);

        positionFlight = position;
        dialogComponentNumeric1_4.setValue(data.getCountElements(),position);
        dialogComponentNumeric1_4.show(this.getFragmentManager(), "");
    }

    @Override
    public void onCloseDialog() {
        //((Flight)listFlight.get(positionFlight)).setEnabled(true);
        mFlightListAdapter.updateItem(positionFlight, dialogComponentNumeric1_4.getValue());
        dialogComponentNumeric1_4.dismiss();
    }
/*
    @Override
    public void onRefresh() {
        dataOption(TOP_REFRESH);
    }

    private void dataOption(int option){

        switch (option) {
            case TOP_REFRESH:
                listTicket.clear();
                callApi();
                break;
            case BOTTOM_REFRESH:
                pages++;
                callApi();
                break;
        }
       // swipeRefreshLayout.setRefreshing(true);
    }
    */
/*
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v,
                                    ContextMenu.ContextMenuInfo menuInfo)
    {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater inflater = getMenuInflater();

        if(v.getId() == R.id.rv_list_flight)
        {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)menuInfo;

            menu.setHeaderTitle(
                    ((Flight)mFlightListAdapter.getItem(info.position)).getCode()
            );

            inflater.inflate(R.menu.menu_contextual, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.menuEliminar:
                Util.showMessage(this, "eliminando seleccion", Snackbar.LENGTH_LONG, Util.SNACK);
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }
    */
}
