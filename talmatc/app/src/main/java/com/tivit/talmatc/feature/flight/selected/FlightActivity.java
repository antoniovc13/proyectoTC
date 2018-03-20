package com.tivit.talmatc.feature.flight.selected;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.feature.flight.list.FlightListActivity;
import com.tivit.talmatc.utils.Util;

import butterknife.BindView;
import timber.log.Timber;

//import com.tivit.talmatc.feature.main.MainActivity;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class FlightActivity extends BaseActivity implements FlightContract.FlightView, View.OnClickListener {

    private FlightContract.FlightPresenter mPresenter;

    // VIEWS
    @BindView(R.id.iv_flight_departure)
    ImageView ivFlightDeparture;
    @BindView(R.id.iv_flight_arrive)
    ImageView ivFlightArrive;
    @BindView(R.id.tv_flight_departure)
    TextView tvFlightDeparture;
    @BindView(R.id.tv_flight_arrive)
    TextView tvFlightArrive;

    private static final String FLIGHT_ARRIVE = "FLIGHT_ARRIVE";
    private static final String FLIGHT_DEPARTURE = "FLIGHT_DEPARTURE";
    /* ======= START ACTIVITY ======= */

    @Override
    protected void createPresenter() {
        mPresenter = new FlightPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_flight);
        setUp();
    }

    @Override
    protected void setUp() {

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ivFlightDeparture.setOnClickListener(this);
        ivFlightArrive.setOnClickListener(this);
        tvFlightDeparture.setOnClickListener(this);
        tvFlightArrive.setOnClickListener(this);

        mPresenter.onViewInitialized();
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
                Util.showMessage(this, "iv_flight_arrive", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(FLIGHT_ARRIVE);
                break;
            case R.id.iv_flight_departure:
                Util.showMessage(this, "iv_flight_departure", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(FLIGHT_DEPARTURE);
                break;
            case R.id.tv_flight_arrive :
                Util.showMessage(this, "tv_flight_arrive", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(FLIGHT_ARRIVE);
                break;
            case R.id.tv_flight_departure :
                Util.showMessage(this, "tv_flight_departure", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(FLIGHT_DEPARTURE);
                break;
            case R.id.ly_flight_departure :
                Util.showMessage(this, "ly_flight_departure", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(FLIGHT_DEPARTURE);
                break;
            case R.id.ly_flight_arrive :
                Util.showMessage(this, "ly_flight_arrive", Snackbar.LENGTH_LONG, Util.SNACK);
                mPresenter.saveTypeFlight(FLIGHT_ARRIVE);
                break;
        }
        openMainActivity();
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }


    @Override
    public void openMainActivity() {

        Bundle bundle = new Bundle();
        next(bundle, FlightListActivity.class, true);

        Timber.d("open FlightListActivity");
    }


    /////////////
    @Override
    public void showFlightView() {

    }

}
