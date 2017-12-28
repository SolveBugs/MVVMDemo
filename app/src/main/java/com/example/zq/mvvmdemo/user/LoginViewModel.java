package com.example.zq.mvvmdemo.user;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.content.Context;
import android.databinding.ObservableField;
import android.text.TextUtils;

import com.example.zq.mvvmdemo.R;
import com.example.zq.mvvmdemo.SingleLiveEvent;
import com.example.zq.mvvmdemo.SnackbarMessage;


/**
 * Created by zhenqiang on 2017/12/27.
 */

public class LoginViewModel extends AndroidViewModel {
    private static final String TAG = "LoginViewModel";

    private final SingleLiveEvent<String> mOpenUserList = new SingleLiveEvent<>();
    private final SnackbarMessage mSnackbarText = new SnackbarMessage();
    private final Context mContext; // To avoid leaks, this must be an Application Context.

    public final ObservableField<String> loginName = new ObservableField<>();
    public final ObservableField<String> loginPass = new ObservableField<>();

    public LoginViewModel(Application mContext) {
        super(mContext);
        this.mContext = mContext.getApplicationContext();
    }

    public SingleLiveEvent<String> getmOpenUserList() {
        return mOpenUserList;
    }

    SnackbarMessage getSnackbarMessage() {
        return mSnackbarText;
    }

    private void login(String loginName, String loginPass) {

        if (TextUtils.isEmpty(loginName)) {
            mSnackbarText.setValue(mContext.getString(R.string.login_name_not_input));
            return;
        }
        if (TextUtils.isEmpty(loginPass)) {
            mSnackbarText.setValue(mContext.getString(R.string.login_pass_not_input));
            return;
        }


        mOpenUserList.setValue("123456");

    }


    public void login() {
        login(loginName.get(), loginPass.get());
    }
}
