package com.tivit.talmatc.feature.traslado_carga3.paso3;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetBehavior;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.InputMethodManager;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.GenericSpinnerItem;
import com.tivit.talmatc.base.ui.BaseFragment;
import com.tivit.talmatc.data.local.model.Flight;
import com.tivit.talmatc.feature.dialog.DialogComponentNumeric1_4;
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

public class FlightListFragment extends BaseFragment
        implements FlightListContract.FlightListView, FlightListAdapter.Callback , View.OnClickListener,
                DialogComponentNumeric1_4.OnSimpleDialogListener{

    //presenter
    private FlightListContract.FlightListPresenter mPresenter;
    //private OrderFragment orderFragment;
    private OnChangeTab onChangeTab;

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
    //private BottomSheetBehavior bottomSheetBehavior;
    private BottomSheetBehavior behavior;

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

/*
    public void setFragment(OrderFragment orderFragment){
        this.orderFragment =  orderFragment;
    }
    */
    /* ======= START FRAGMENT ======= */
    public static FlightListFragment newInstance(int tabSelected){
        Bundle args = new Bundle();
        args.putInt(Configuration.TAB_SELECTED, tabSelected);
        FlightListFragment fragment = new FlightListFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.frg_flight_list, container, false);

        mPresenter = new FlightListPresenter(this);
        return view;
    }

    @Override
    protected void setUp(View view) {
/*
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
*/
/*
        Bundle extras = this.getActivity().getIntent().getExtras();
        setCodeFlight(extras.getString(Configuration.CODE_FLIGHT));
        Timber.d("setUp - "+ getCodeFlight());
*/
        adaptador = new ArrayAdapter<String>(this.getContext(), android.R.layout.simple_dropdown_item_1line, new ArrayList<>());

        actvVuelo.setAdapter(adaptador);

        //ibFindVuelo.setOnClickListener(this);
        btnAgregarVuelo.setOnClickListener(this);

        listFlight = new ArrayList<Flight>();
        //Timber.d("***codeFlight: "+codeFlight);
        mFlightListAdapter = new FlightListAdapter(listFlight, codeFlight);
        mFlightListAdapter.setCallback(this);

        mLayoutManager = new LinearLayoutManager(this.getContext());
        mLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());
        mRecyclerView.setAdapter(mFlightListAdapter);


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


        initButtonSheet();
        initListeners();
        mPresenter.onViewInitialized();
    }

    private void initButtonSheet() {

        // To handle FAB animation upon entrance and exit
        final Animation growAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.simple_grow);
        final Animation shrinkAnimation = AnimationUtils.loadAnimation(this.getContext(), R.anim.simple_shrink);


        final FloatingActionButton fab = (FloatingActionButton) this.getActivity().findViewById(R.id.gmail_fab);
        //final LinearLayout lySearch = (LinearLayout) this.getActivity().findViewById(R.id.id_ly_search);

        fab.setVisibility(View.VISIBLE);



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


        CoordinatorLayout coordinatorLayout = (CoordinatorLayout) this.getActivity().findViewById(R.id.gmail_coordinator);
        View bottomSheet = coordinatorLayout.findViewById(R.id.gmail_bottom_sheet);

        behavior = BottomSheetBehavior.from(bottomSheet);

        behavior.setBottomSheetCallback(new BottomSheetBehavior.BottomSheetCallback() {
            @Override
            public void onStateChanged(@NonNull View bottomSheet, int newState) {

                switch (newState) {

                    case BottomSheetBehavior.STATE_DRAGGING:
                        //Timber.d("BottomSheets STATE_DRAGGING");
                        if (showFAB) {
                            fab.startAnimation(shrinkAnimation);
                        }
                        break;

                    case BottomSheetBehavior.STATE_COLLAPSED:
                        //Timber.d("BottomSheets STATE_COLLAPSED");
                        showFAB = true;
                        fab.setVisibility(View.VISIBLE);
                        fab.startAnimation(growAnimation);
                        break;

                    case BottomSheetBehavior.STATE_EXPANDED:
                        //Timber.d("BottomSheets STATE_EXPANDED");
                        showFAB = false;

                        break;
                    case BottomSheetBehavior.STATE_SETTLING:
                        //Timber.d("BottomSheets STATE_SETTLING");
                        //showFAB = false;
                        break;
                    case BottomSheetBehavior.STATE_HIDDEN:
                        //Timber.d("BottomSheets STATE_HIDDEN");

                        AlertDialog.Builder builder = new AlertDialog.Builder(FlightListFragment.this.getContext())
                                .setTitle("¿Estas seguro que deseas iniciar viaje?")
                                .setMessage("Con esto ya no podras realizar adición/eliminación de vuelos.")
                                .setCancelable(false)
                                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        behavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                                        showFAB = false;
                                        onChangeTab.startTravel();
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
                //Timber.d("BottomSheets "+ "Offset: " + slideOffset);
            }
        });

    }

    private void initListeners() {

    }

    @Override
    public void onClick(View v) {

        int id = v.getId();

        switch (id) {
            //case R.id.ib_findvuelo :
            case R.id.id_cam_btn_agregarvuelo :

                //addFlight();
                Timber.d("***onClick code: "+codeFlight);
                mFlightListAdapter.setCodeFlight(codeFlight);
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
    public void addFlightToList(int position, Flight flight) {
        //ocultar teclado
        View view = this.getActivity().getCurrentFocus();
        view.clearFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)this.getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
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

        behavior.setState(BottomSheetBehavior.STATE_COLLAPSED);

    }

    @Override
    public void onItemRowClicked(Flight data, int position) {
        //Util.showMessage(this, data.getCode(), Snackbar.LENGTH_LONG, Util.SNACK);

        positionFlight = position;
        dialogComponentNumeric1_4.setValue(data.getCountElements(),position);
        dialogComponentNumeric1_4.show(this.getActivity().getFragmentManager(), "");
    }

    @Override
    public void onCloseDialog() {
        //((Flight)listFlight.get(positionFlight)).setEnabled(true);
        mFlightListAdapter.updateItem(positionFlight, dialogComponentNumeric1_4.getValue());
        dialogComponentNumeric1_4.dismiss();
    }


    public String getCodeFlight() {
        return codeFlight;
    }

    public void setCodeFlight(String codeFlight) {
        Timber.d("setCodeFlight:"+codeFlight);
        this.codeFlight = codeFlight;

        //mFlightListAdapter.notifyDataSetChanged();
    }
}
