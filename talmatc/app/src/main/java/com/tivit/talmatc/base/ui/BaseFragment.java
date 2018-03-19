package com.tivit.talmatc.base.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.tivit.talmatc.R;
import com.tivit.talmatc.TivitApplication;
import com.tivit.talmatc.base.mvp.MvpView;
import com.tivit.talmatc.data.AppDataManager;
import com.tivit.talmatc.feature.login.LoginActivity;
import com.tivit.talmatc.utils.ViewUtils;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by Alexzander Guillermo on 29/08/2017.
 */

public abstract class BaseFragment extends Fragment implements MvpView {

    private BaseActivity mActivity;
    private Unbinder mUnBinder;
    private ProgressDialog mProgressDialog;

    protected Drawable emptyDrawable = ContextCompat.getDrawable(TivitApplication.getAppContext(), R.mipmap.ic_empty);
    protected Drawable errorDrawable = ContextCompat.getDrawable(TivitApplication.getAppContext(), R.mipmap.ic_error);

    protected abstract void setUp(View view);

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mUnBinder = ButterKnife.bind(this, view);
        setUp(view);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof BaseActivity) {
            BaseActivity activity = (BaseActivity) context;
            this.mActivity = activity;
            activity.onFragmentAttached();
        }
    }

    @Override
    public void onDetach() {
        mActivity = null;
        super.onDetach();
    }

    @Override
    public void onDestroy() {
        if (mUnBinder != null) {
            mUnBinder.unbind();
        }
        super.onDestroy();
    }

    @Override
    public void showLoading(String message) {
        hideLoading();
        mProgressDialog = ViewUtils.showProgressDialog(this.getContext(), message);
    }

    @Override
    public void hideLoading() {
        if (mProgressDialog != null && mProgressDialog.isShowing()) {
            mProgressDialog.cancel();
        }
    }

    @Override
    public void showMessage(String message) {
        mActivity.showMessage(message);
    }

    @Override
    public void showMessage(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.showMessage(resId);
        }
    }

    @Override
    public void onError(String message) {
        if (mActivity != null) {
            mActivity.onError(message);
        }
    }

    @Override
    public void onError(@StringRes int resId) {
        if (mActivity != null) {
            mActivity.onError(resId);
        }
    }

    @Override
    public boolean isNetworkConnected() {
        if (mActivity != null) {
            return mActivity.isNetworkConnected();
        }
        return false;
    }

    @Override
    public void hideKeyboard() {
        if (mActivity != null) {
            mActivity.hideKeyboard();
        }
    }

    protected void next(Bundle bundle, Class<?> activity) {
        Intent intent= new Intent(getActivity(), activity);
        if(bundle!=null){
            intent.putExtras(bundle);
        }
        startActivity(intent);
        getActivity().overridePendingTransition(R.animator.right_slide_in, R.animator.left_slide_out);
    }

    public void logout() {
        AppDataManager appDataManager = AppDataManager.getInstance(TivitApplication.getAppContext());
        appDataManager.getAppPreferencesData().deleteAuthorization();
        Intent logoutintent = new Intent(getActivity(), LoginActivity.class);
        logoutintent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(logoutintent);
        getActivity().finish();
    }

    public BaseActivity getBaseActivity() {
        return mActivity;
    }

    public interface Callback {

        void onFragmentAttached();

        void onFragmentDetached(String tag);
    }

}
