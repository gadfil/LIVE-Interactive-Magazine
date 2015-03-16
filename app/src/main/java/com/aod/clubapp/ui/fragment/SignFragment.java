package com.aod.clubapp.ui.fragment;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.aod.clubapp.R;
import com.aod.clubapp.api.model.auth.SignRequest;
import com.aod.clubapp.api.model.auth.SignResponse;
import com.aod.clubapp.api.model.errors.ResponseError;
import com.aod.clubapp.api.v1.Api;
import com.aod.clubapp.ui.activity.AuthActivity;
import com.aod.clubapp.util.DataUtil;
import com.aod.clubapp.util.MyLog;
import com.aod.clubapp.util.MyToast;

import java.util.regex.Pattern;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


/**
 * Created by gadfil on 15.02.2015.
 */
public class SignFragment extends BaseFragment implements View.OnClickListener {

    private SignCallbacks callbacks;

    private EditText login;
    private EditText password;
    private Button sign;

    public static SignFragment newInstance() {
        return new SignFragment();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign, container, false);

        login = (EditText) rootView.findViewById(R.id.sign_login);
        password = (EditText) rootView.findViewById(R.id.sign_password);
        sign =(Button)rootView.findViewById(R.id.sign_bt_sign);

        sign.setOnClickListener(this);
        rootView.findViewById(R.id.sign_bt_signUp).setOnClickListener(this);
        rootView.findViewById(R.id.sign_user_agreement).setOnClickListener(this);
        rootView.findViewById(R.id.sign_bt_facebook).setOnClickListener(this);
        rootView.findViewById(R.id.sign_bt_instagram).setOnClickListener(this);
            return rootView;
    }


    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (SignCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement SignCallbacks.");
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.sign_user_agreement:
                callbacks.userAgreement();
                break;
            case R.id.sign_bt_signUp:
                callbacks.signUp(null);
                break;
            case R.id.sign_bt_facebook:
                callbacks.signUp(getEmail());
                break;
            case R.id.sign_bt_instagram:
                callbacks.signUp(getEmail());
                break;
            case R.id.sign_bt_forget_password:
                callbacks.forgetPassword();
                break;
            case R.id.sign_bt_sign:
                if(login.getText().length()>0&&password.getText().length()>0){
                    sign(login.getText().toString(),password.getText().toString() );
                }else {
                    MyToast.show(getActivity(), R.string.enter_all_edit);
                }
                break;
        }
    }

    private void sign(String login, String password) {
        sign.setClickable(false);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Api.URL)
                .build();
        Api api = restAdapter.create(Api.class);
        SignRequest request= new SignRequest();
        request.setLogin(login);
        request.setPassword(password);
        api.sign(request, new Callback<SignResponse>() {
            @Override
            public void success(SignResponse signResponse, Response response) {
                MyLog.d("api", "" + signResponse.getLogin());
                DataUtil.setToken(getActivity(), signResponse.getAuthToken());
                DataUtil.setId(getActivity(), signResponse.getId());
                DataUtil.setLogin(getActivity(), signResponse.getLogin());
                MyLog.d("api", "" + DataUtil.getToken(getActivity()));
                callbacks.sign();
                sign.setClickable(true);
            }

            @Override
            public void failure(RetrofitError error) {
                sign.setClickable(true);
                if(error.isNetworkError()){
                    Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();

                }else{
                    switch (error.getResponse().getStatus()) {
                        case 400:
                            MyToast.show(getActivity(),error.getBodyAs(ResponseError.class).toString() );

                            break;
                    }
                }
            }
        });
    }

    private String  getEmail() {
        String email = "";
        Pattern emailPattern = Patterns.EMAIL_ADDRESS; // API level 8+
        Account[] accounts = AccountManager.get(getActivity()).getAccounts();
        for (Account account : accounts) {
            if (emailPattern.matcher(account.name).matches()) {
                String possibleEmail = account.name;
                email = possibleEmail;
                continue;
            }
        }
        return email;
    }

    public static interface SignCallbacks {
        void userAgreement();

        void signUp(String email);

        void sign();

        void forgetPassword();
    }


}
