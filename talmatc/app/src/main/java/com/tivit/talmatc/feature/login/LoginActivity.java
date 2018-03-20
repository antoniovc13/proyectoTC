package com.tivit.talmatc.feature.login;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.remote.model.Authorization;
import com.tivit.talmatc.data.remote.model.Login;
//import com.tivit.talmatc.feature.main.MainActivity;
import com.tivit.talmatc.feature.main.MainActivity;
import com.tivit.talmatc.feature.traslado_carga.flight.FlightActivity;
import com.tivit.talmatc.utils.KeyboardUtils;
import com.tivit.talmatc.utils.Util;
import com.tivit.talmatc.utils.ViewUtils;

import butterknife.BindView;
import timber.log.Timber;

import static com.tivit.talmatc.utils.ViewUtils.showProgressDialog;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView, View.OnClickListener {

    private LoginContract.LoginPresenter mPresenter;

    // VIEWS

    @BindView(R.id.pnlSplash)           LinearLayout pnlSplash;
    @BindView(R.id.pnlLogin)            ScrollView pnlLogin;
    @BindView(R.id.et_username)         EditText etUsername;
    @BindView(R.id.et_password)         EditText etPassword;
    @BindView(R.id.btn_auth_login)      Button btnAuthLogin;

    //
    //private ProgressDialog progressDialog;

    /* ======= START ACTIVITY ======= */

    @Override
    protected void createPresenter() {
        mPresenter = new LoginPresenter(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.act_login);
        setUp();
    }

    @Override
    protected void setUp() {
        showPanelSplash();
        btnAuthLogin.setOnClickListener(this);
        mPresenter.onViewInitialized();
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        switch (id) {
            case R.id.btn_auth_login :
                authLogin();
                break;
        }
    }

    @Override
    protected void onDestroy() {
        mPresenter.onDetachView();
        super.onDestroy();
    }

    private void authLogin() {
        boolean isValid = true;
        View focusView = null;

        String username = etUsername.getText().toString().trim();
        String password = etPassword.getText().toString();

        if(TextUtils.isEmpty(username) && TextUtils.isEmpty(password)) {
            isValid = false;
            focusView = etPassword;
        }

        if (TextUtils.isEmpty(password)) {
            etPassword.setError("Contraseña requerida");
            focusView = etPassword;
            isValid = false;
        }

        if (TextUtils.isEmpty(username)) {
            etUsername.setError("Usuario requerido");
            focusView = etUsername;
            isValid = false;
        }

        if(!isValid) {
            focusView.requestFocus();

        } else {
            KeyboardUtils.hideSoftInput(this);

            showLoadingActivity("Validando Credenciales");
            Util.closeKeyboard(etPassword, this);
            Login login = new Login(username, password);

            mPresenter.startLogin(login);
        }
    }

    @Override
    public void openMainActivity() {
        //hideLoading();
        Bundle bundle = new Bundle();
        next(bundle, MainActivity.class, true);
    }

    @Override
    public void showLoginView() {
        etUsername.setText("admin");
        etPassword.setText("admin");
        showPanelLogin();
    }

    public void showLoadingActivity(String msj){
        showLoading(msj);
    }

/*
    @Override
    public void showLoading(String message) {
        Timber.d("showLoading: "+message+"-"+mActivity);
//        hideLoading();
        mProgressDialog = ViewUtils.showProgressDialog(mActivity, message);
    }

    @Override
    public void hideLoading() {
        Timber.d("hideLoading: ");
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }
    */

    private void checkOpenSession() {
        /*
        Authorization auth = PreferencesManager.getAuthorization(this);

        if(auth != null && !TextUtils.isEmpty(auth.getToken())){
            Intent i = new Intent(LoginActivity.this, ActMainActivity.class);
            startActivity(i);
            finish();
        } else {
            showPanelLogin();
        }
        */
    }

    private void showPanelLogin() {
        pnlLogin.setVisibility(View.VISIBLE);
        pnlSplash.setVisibility(View.GONE);
    }

    private void showPanelSplash() {
        pnlLogin.setVisibility(View.GONE);
        pnlSplash.setVisibility(View.VISIBLE);
    }
}
