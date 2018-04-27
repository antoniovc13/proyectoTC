package com.tivit.talmatc.feature.main;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tivit.talmatc.BuildConfig;
import com.tivit.talmatc.R;
import com.tivit.talmatc.base.generic.RoundedImageView;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.local.constant.RoleEnum;
import com.tivit.talmatc.data.local.model.User;
import com.tivit.talmatc.feature.dialog.DialogComponentNumeric1_4;
import com.tivit.talmatc.feature.login.LoginActivity;
import com.tivit.talmatc.feature.traslado_carga.selected.ChargeMoveFragment;
import com.tivit.talmatc.feature.traslado_carga2.selected.ChargeMoveFragment2;
import com.tivit.talmatc.feature.traslado_carga3.OnChangeTab;
import com.tivit.talmatc.feature.traslado_carga3.OrderFragment;

import butterknife.BindView;
import timber.log.Timber;

public class MainActivity extends BaseActivity
        implements MainContract.MainView,
                    NavigationView.OnNavigationItemSelectedListener,
                    OnChangeTab,
                    DialogComponentNumeric1_4.OnSimpleDialogListener,
                    View.OnClickListener {

    private MainContract.MainPresenter mPresenter;

    @BindView(R.id.toolbar)             Toolbar mToolbar;
    @BindView(R.id.drawer_view)         DrawerLayout mDrawer;
    @BindView(R.id.navigation_view)     NavigationView mNavigationView;
    @BindView(R.id.tv_app_version)      TextView mAppVersionTextView;

    private TextView mNameTextView;
    private TextView mEmailTextView;
    private RoundedImageView mProfileImageView;
    private ActionBarDrawerToggle mDrawerToggle;


/*
    private CoordinatorLayout coordinatorLayout;
    private FloatingActionButton fab;
    private static DrawerItem drawerItem = null;
*/
    private String currentVersionName;
    private int currentVersionCode;;

    //qr code scanner object
    //private IntentIntegrator qrScan;

    OrderFragment orderFragment;

    Menu menuToolbar;


    @Override
    protected void createPresenter() {
        mPresenter = new MainPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_main);
        setUp();
    }

    @Override
    protected void setUp() {
        setSupportActionBar(mToolbar);
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawer, mToolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                hideKeyboard();
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        mDrawer.addDrawerListener(mDrawerToggle);
        mDrawerToggle.syncState();
        setupNavMenu();
        mPresenter.onNavMenuCreated();
        //mPresenter.onViewInitialized();

        homeFragment3(mNavigationView.getMenu().getItem(0));
    }




    void setupNavMenu() {
        View headerLayout = mNavigationView.getHeaderView(0);
        mProfileImageView = (RoundedImageView) headerLayout.findViewById(R.id.iv_profile_pic);
        mNameTextView = (TextView) headerLayout.findViewById(R.id.tv_name);
        mEmailTextView = (TextView) headerLayout.findViewById(R.id.tv_email);


        mNavigationView.setNavigationItemSelectedListener(this);


    }

    @Override
    public void loadMenu(User user) {

        if(user.getRole().equals(RoleEnum.ROL1.getValue())){

            //if(user.getUnidad().equals(ParameterEnum.TRACTOR.getValue())){
                mNavigationView.getMenu().findItem(R.id.menuTrasladoCarga1).setVisible(true);

//            }else if(user.getUnidad().equals(ParameterEnum.TRACTOR.getValue())){
                mNavigationView.getMenu().findItem(R.id.menuTrasladoCarga2).setVisible(true);

                mNavigationView.getMenu().findItem(R.id.menuTrasladoCarga3).setVisible(true);
  //          }

        }else if(user.getRole().equals(RoleEnum.ROL2.getValue())){
            mNavigationView.getMenu().findItem(R.id.menuRezagados1).setVisible(true);

        }else if(user.getRole().equals(RoleEnum.ROL3.getValue())){
            mNavigationView.getMenu().findItem(R.id.menuRezagados2).setVisible(true);

        }

    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        switch (id){
            case R.id.menuTrasladoCarga1:
                homeFragment(item);
                break;
            case R.id.menuTrasladoCarga2:
                homeFragment2(item);
                break;
            case R.id.menuTrasladoCarga3:
                homeFragment3(item);
                break;
            case R.id.menuRezagados1:
                //homeFragment(item);
                break;
            case R.id.menuRezagados2:
                //homeFragment(item);
                break;
            case R.id.nav_about:
                aboutUs();
                break;
            case R.id.nav_logout:
                logout();
                break;
            /*default:
                return false;*/
        }

        // Highlight the selected item has been done by NavigationView
        //item.setChecked(true);
        // Set action bar title
        //setTitle(item.getTitle());
        mDrawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void homeFragment(MenuItem item) {

        try {
            Bundle args =  new Bundle();
            Class fragmentClass = ChargeMoveFragment.class;
            args.putString("MESSAGE", "Welcome");
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
            changeFragment(fragment, item);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void homeFragment2(MenuItem item) {

        try {
            Bundle args =  new Bundle();
            Class fragmentClass = ChargeMoveFragment2.class;
            args.putString("MESSAGE", "Welcome");
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
            changeFragment(fragment, item);

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    private void homeFragment3(MenuItem item) {
/*
        try {
            Bundle args =  new Bundle();
            Class fragmentClass = OrderFragment.class;
            args.putString("MESSAGE", "Welcome");
            Fragment fragment = (Fragment) fragmentClass.newInstance();
            fragment.setArguments(args);
            changeFragment(fragment, item);


        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        */
        Bundle args =  new Bundle();
        args.putString("MESSAGE", "Welcome");
        orderFragment = new OrderFragment();
        orderFragment = orderFragment.newInstance();
        orderFragment.setArguments(args);
        changeFragment(orderFragment, item);

    }

    private void changeFragment(Fragment fragment, MenuItem item){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_view);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else if(getFragmentManager().getBackStackEntryCount()>0){
            getFragmentManager().popBackStack();
        }else{
            super.onBackPressed();
        }
    }

    @Override
    public void updateAppVersion() {
        String version = getString(R.string.version) + " " + BuildConfig.VERSION_NAME;
        mAppVersionTextView.setText(version);
    }

    @Override
    public void closeNavigationDrawer() {
        if (mDrawer != null) {
            mDrawer.closeDrawer(Gravity.START);
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main, menu);
        this.menuToolbar = menu;
        showMenuToolbar(false, false, false);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {


        switch (item.getItemId()) {
            case R.id.id_action_init2:
                //loadFromApiRemoteService();
                return true;
            case R.id.id_action_marker:
                //loadFromApiRemoteService();
                return true;
            case R.id.id_action_trasler:
                //saveFromDbToRemoteService();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }


    public void showMenuToolbar(boolean menuInitVisible, boolean menuMarkerVisible, boolean menuTraslerVisible) {

        this.menuToolbar.findItem(R.id.id_action_init2).setVisible(menuInitVisible);

        this.menuToolbar.findItem(R.id.id_action_marker).setVisible(menuMarkerVisible);
        this.menuToolbar.findItem(R.id.id_action_marker).setTitle("En camino");

        this.menuToolbar.findItem(R.id.id_action_trasler).setVisible(menuTraslerVisible);
    }

    public void showMenuMarkerEdit(boolean blnIconStop, String textoValue){

        this.menuToolbar.findItem(R.id.id_action_marker).setIcon( blnIconStop? R.mipmap.ic_marker_red:R.mipmap.ic_marker_green);
        this.menuToolbar.findItem(R.id.id_action_marker).setTitle(textoValue);
    }

    public void showMenuInitVisible(boolean menuInitVisible){
        this.menuToolbar.findItem(R.id.id_action_init2).setVisible(menuInitVisible);
    }


    private void loadFromApiRemoteService() {
        /*
        if(CommonUtils.isInternetOnline(this)) {
            showMessage("Buscando ordenes asignadas del proveedor");
            Intent intent = new Intent(this, DownloadService.class);
            intent.putExtra(DownloadService.PARAM_SOURCE, Configuration.SOURCE_BUTTOM);
            startService(intent);
        }
        */
    }

    private void saveFromDbToRemoteService() {
        /*
        if(CommonUtils.isInternetOnline(this)) {
            showMessage("Sincronizando ordenes ejecutadas con el proveedor");
            Intent intent = new Intent(this, UploadService.class);
            startService(intent);
        }
        */
    }

    private void logout() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this)
                .setTitle("Advertencia")
                .setMessage("¿Estas seguro que deseas cerrar sesión?")
                .setCancelable(false)
                .setPositiveButton("SI", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.callPreferencesRemoveAuth();
                    }
                })
                .setNegativeButton("NO", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    @Override
    public void openLoginActivity() {
        Intent logoutintent = new Intent(this, LoginActivity.class);
        logoutintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logoutintent);
        finish();
        overridePendingTransitionExit();
    }




    private void aboutUs() {
        try {
            currentVersionCode = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
            currentVersionName = getPackageManager().getPackageInfo(getPackageName(),0).versionName;
            //Log.d(TAG, "VERSION Code: " + currentVersionCode + " Name: " + currentVersionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        String version = "Version " + currentVersionName + "\n Revision " + currentVersionCode + "";

        final Dialog fullscreenDialog = new Dialog(this, R.style.DialogFullscreen);
        fullscreenDialog.setContentView(R.layout.dlg_fullscreen);
        TextView tvVersion = fullscreenDialog.findViewById(R.id.tvVersion);
        tvVersion.setText(version);
        ImageView img_dialog_fullscreen_close = (ImageView) fullscreenDialog.findViewById(R.id.img_dialog_fullscreen_close);
        img_dialog_fullscreen_close.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fullscreenDialog.dismiss();
            }
        });
        fullscreenDialog.setCanceledOnTouchOutside(true);
        fullscreenDialog.show();
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void goToFlight() {
        Timber.d("goToFlight");
        orderFragment.goToFlight();
    }

    @Override
    public void backFlight() {

    }

    @Override
    public void goToFlightList(String codeFlight) {
        orderFragment.goToFlightList(codeFlight);
        showMenuToolbar(false, true, true);
    }

    @Override
    public void startTravel() {
        orderFragment.startTravel();
        //this.onPrepareOptionsMenu(menuToolbar);
        showMenuMarkerEdit(false, "En camino");
        showMenuInitVisible(true);
    }


    @Override
    public void onCloseDialog() {
        orderFragment.onCloseDialog();
    }

}
