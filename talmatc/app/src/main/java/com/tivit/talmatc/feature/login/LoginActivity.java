package com.tivit.talmatc.feature.login;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.remote.model.Login;
//import com.tivit.talmatc.feature.main.MainActivity;
import com.tivit.talmatc.feature.main.MainActivity;
import com.tivit.talmatc.services.FcmInstanceService;
import com.tivit.talmatc.utils.KeyboardUtils;
import com.tivit.talmatc.utils.Util;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView, View.OnClickListener {

    private LoginContract.LoginPresenter mPresenter;

    // VIEWS

    @BindView(R.id.pnlSplash)           LinearLayout pnlSplash;
    @BindView(R.id.pnlLogin)            ScrollView pnlLogin;
    @BindView(R.id.et_username)         EditText etUsername;
    //@BindView(R.id.et_token)         EditText etToken;
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
        Timber.d("*****FcmInstanceService:"+ FcmInstanceService.getToken());//se muestra el token firebase
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
        //etToken.setText(FcmInstanceService.getToken());
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
        /*
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
    }
}
