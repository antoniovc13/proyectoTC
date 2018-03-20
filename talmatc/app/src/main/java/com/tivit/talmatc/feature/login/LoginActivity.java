package com.tivit.talmatc.feature.login;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.tivit.talmatc.R;
import com.tivit.talmatc.base.ui.BaseActivity;
import com.tivit.talmatc.data.remote.model.Login;
//import com.tivit.talmatc.feature.main.MainActivity;
import com.tivit.talmatc.feature.flight.selected.FlightActivity;
import com.tivit.talmatc.utils.KeyboardUtils;

import butterknife.BindView;
import timber.log.Timber;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public class LoginActivity extends BaseActivity implements LoginContract.LoginView, View.OnClickListener {

    private LoginContract.LoginPresenter mPresenter;

    // VIEWS
    @BindView(R.id.btn_auth_login)
    Button btnAuthLogin;
    @BindView(R.id.et_username)
    EditText etUsername;
    @BindView(R.id.et_password)
    EditText etPassword;

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

            Login login = new Login(username, password);
            mPresenter.startLogin(login);
        }
    }

    @Override
    public void openMainActivity() {

        Bundle bundle = new Bundle();
        next(bundle, FlightActivity.class, true);

        Timber.d("open MainActivity");
    }

    @Override
    public void showLoginView() {
        etUsername.setText("admin");
        etPassword.setText("admin");
    }

}
