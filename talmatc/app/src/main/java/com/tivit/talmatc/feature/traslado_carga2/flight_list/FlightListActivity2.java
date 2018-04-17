package com.tivit.talmatc.feature.traslado_carga2.flight_list;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
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

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class FlightListActivity2 extends BaseActivity
        implements FlightListContract2.FlightListView, FlightListAdapter2.Callback , View.OnClickListener,
                DialogComponentNumeric1_4.OnSimpleDialogListener{

    //presenter
    private FlightListContract2.FlightListPresenter mPresenter;

    // VIEWS
    @BindView(R.id.id_cam_actv_vuelo)              AutoCompleteTextView    actvVuelo;
    @BindView(R.id.id_cam_btn_agregarvuelo)         Button                  btnAgregarVuelo;
    @BindView(R.id.id_cam_tv_msj)                  TextView                tvMensaje;
    @BindView(R.id.id_cam_rv_list_flight)          RecyclerView            mRecyclerView;

    //@BindView(R.id.bottomSheetLayout)          LinearLayout            bottomSheet;




    //@BindView(R.id.swpRefresh)            SwipeRefreshLayout swipeRefreshLayout;

    //sheet

    boolean showFAB = true;
    // BottomSheetBehavior variable
    private BottomSheetBehavior bottomSheetBehavior;

    // TextView variable
    private TextView bottomSheetHeading;
    // Button variables
    private Button expandBottomSheetButton;
    private Button collapseBottomSheetButton;
    private Button hideBottomSheetButton;
    private Button showBottomSheetDialogButton;
    //private LinearLayout bottomSheet;

    //FlightListSwipeController swipeController;
    ItemTouchHelper.Callback callback;

    //ADAPTERS
    private FlightListAdapter2 mFlightListAdapter;
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
        mPresenter = new FlightListPresenter2(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flight_list2);
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
        mFlightListAdapter = new FlightListAdapter2(listFlight, codeFlight );
        mFlightListAdapter.setCallback(this);

        mLayoutManager = new LinearLayoutManager(this);
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFlightListAdapter);


        callback = new SimpleItemTouchHelperCallback2(mFlightListAdapter);
        ItemTouchHelper touchHelper = new ItemTouchHelper(callback);
        touchHelper.attachToRecyclerView(mRecyclerView);
/*
        swipeRefreshLayout = findViewById(R.id.swpRefresh);
        swipeRefreshLayout.setColorSchemeResources(R.color.google_blue, R.color.google_green, R.color.google_red, R.color.google_yellow);
        swipeRefreshLayout.setOnRefreshListener(this);
*/
        //dialogo
        dialogComponentNumeric1_4 = new DialogComponentNumeric1_4();

        //sheet
        //bottomSheet = (LinearLayout)findViewById(R.id.bottomSheet);
        /*
        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.bottomSheetLayout));
        bottomSheetHeading = (TextView) findViewById(R.id.bottomSheetHeading);
        expandBottomSheetButton = (Button) findViewById(R.id.expand_bottom_sheet_button);
        collapseBottomSheetButton = (Button) findViewById(R.id.collapse_bottom_sheet_button);
        hideBottomSheetButton = (Button) findViewById(R.id.hide_bottom_sheet_button);
        showBottomSheetDialogButton = (Button) findViewById(R.id.show_bottom_sheet_dialog_button);
        */
        /*
        final BottomSheetBehavior bsb = BottomSheetBehavior.from(bottomSheet);

        btnExpBottomSheet = (Button)findViewById(R.id.btnExpBottomSheet);
        btnExpBottomSheet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                if(bsb.getState() != BottomSheetBehavior.STATE_EXPANDED) {
                    bsb.setState(BottomSheetBehavior.STATE_EXPANDED);
                    btnExpBottomSheet.setText("collapse button");
                }
                else {
                    bsb.setState(BottomSheetBehavior.STATE_COLLAPSED);
                    btnExpBottomSheet.setText("Expand button");
                }
            }
        });

        bsb.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                String nuevoEstado = "";

                switch(newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        nuevoEstado = "STATE_COLLAPSED";
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        nuevoEstado = "STATE_EXPANDED";
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        nuevoEstado = "STATE_HIDDEN";
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        nuevoEstado = "STATE_DRAGGING";
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        nuevoEstado = "STATE_SETTLING";
                        break;
                }

                Timber.d("BottomSheets "+ "Nuevo estado: " + nuevoEstado);
            }

            @Override
            public void onSlide(@NonNull View bottomSheet, float slideOffset) {
                Timber.d("BottomSheets "+ "Offset: " + slideOffset);
            }
        });
        */
        initButtonSheet();
        initListeners();
        mPresenter.onViewInitialized();
    }

    private void initButtonSheet() {

        // To handle FAB animation upon entrance and exit
        final Animation growAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_grow);
        final Animation shrinkAnimation = AnimationUtils.loadAnimation(this, R.anim.simple_shrink);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.gmail_fab);

        fab.setVisibility(View.VISIBLE);
        fab.startAnimation(growAnimation);


        shrinkAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                fab.setVisibility(View.GONE);
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });


        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) findViewById(R.id.gmail_coordinator);
        View bottomSheet = coordinatorLayout.findViewById(R.id.gmail_bottom_sheet);

        BottomSheetBehavior behavior = BottomSheetBehavior.from(bottomSheet);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {

                    case BottomSheetBehavior.STATE_DRAGGING:
                        Timber.d("BottomSheets STATE_DRAGGING");
                        if (showFAB)
                            fab.startAnimation(shrinkAnimation);
                        break;

                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Timber.d("BottomSheets STATE_COLLAPSED");
                        showFAB = true;
                        fab.setVisibility(View.VISIBLE);
                        fab.startAnimation(growAnimation);
                        break;

                    case BottomSheetBehavior.STATE_EXPANDED:
                        Timber.d("BottomSheets STATE_EXPANDED");
                        showFAB = false;
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Timber.d("BottomSheets STATE_SETTLING");
                        //showFAB = false;
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Timber.d("BottomSheets STATE_HIDDEN");

                        AlertDialog.Builder builder = new AlertDialog.Builder(FlightListActivity2.this)
                                .setTitle("¿Estas seguro que deseas iniciar viaje?")
                                .setMessage("Con esto ya no podras realizar adición/eliminación de vuelos.")
                                .setCancelable(false)
                                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        showFAB = false;
                                        dialog.dismiss();
                                    }
                                })
                                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                                        dialog.dismiss();
                                    }
                                });

                        AlertDialog dialog = builder.create();
                        dialog.show();
                        //behavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                        break;
                }

            }

            @Override
            public void onSlide(View bottomSheet, float slideOffset) {
                Timber.d("BottomSheets "+ "Offset: " + slideOffset);
            }
        });

    }

    private void initListeners() {
        /*
        // register the listener for button click
        expandBottomSheetButton.setOnClickListener(this);
        collapseBottomSheetButton.setOnClickListener(this);
        hideBottomSheetButton.setOnClickListener(this);
        showBottomSheetDialogButton.setOnClickListener(this);

        // Capturing the callbacks for bottom sheet
        bottomSheetBehavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(View bottomSheet, int newState) {

                if (newState == BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetHeading.setText(getString(R.string.text_collapse_me));
                } else {
                    bottomSheetHeading.setText(getString(R.string.text_expand_me));
                }

                // Check Logs to see how bottom sheets behaves
                switch (newState) {
                    case BottomSheetBehavior.STATE_COLLAPSED:
                        Timber.d("Bottom Sheet Behaviour "+ "STATE_COLLAPSED");
                        break;
                    case BottomSheetBehavior.STATE_DRAGGING:
                        Timber.d("Bottom Sheet Behaviour "+ "STATE_DRAGGING");
                        break;
                    case BottomSheetBehavior.STATE_EXPANDED:
                        Timber.d("Bottom Sheet Behaviour "+ "STATE_EXPANDED");
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        Timber.d("Bottom Sheet Behaviour "+ "STATE_HIDDEN");
                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        Timber.d("Bottom Sheet Behaviour "+ "STATE_SETTLING");
                        break;
                }
            }


            @Override
            public void onSlide(View bottomSheet, float slideOffset) {

            }
        });

*/
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
            case R.id.collapse_bottom_sheet_button:
                // Collapsing the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                return true;
            case R.id.expand_bottom_sheet_button:
                // Expanding the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_EXPANDED);
                return true;
            case R.id.hide_bottom_sheet_button:
                // Hiding the bottom sheet
                bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                return true;

            case R.id.show_bottom_sheet_dialog_button:

                return true;
                */
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
            case R.id.id_cam_btn_agregarvuelo :

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
