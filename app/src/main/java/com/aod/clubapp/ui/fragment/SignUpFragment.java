package com.aod.clubapp.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.aod.clubapp.R;
import com.aod.clubapp.api.model.auth.SignRequest;
import com.aod.clubapp.api.model.auth.SignResponse;
import com.aod.clubapp.api.model.auth.SignUpRequest;
import com.aod.clubapp.api.model.auth.SignUpResponse;
import com.aod.clubapp.api.model.errors.ResponseError;
import com.aod.clubapp.api.v1.Api;
import com.aod.clubapp.util.DataUtil;
import com.aod.clubapp.util.MyLog;
import com.aod.clubapp.util.MyToast;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gadfil on 22.02.2015.
 */
public class SignUpFragment extends BaseFragment implements View.OnClickListener{
    private static final String ARG_EMAIL = "arg_email";
    private EditText login;
    private EditText email;
    private EditText password;
    private EditText confirmPassword;
    private Button signUp;
    private SignFragment.SignCallbacks callbacks;

    public static BaseFragment newInstance(String email) {
        SignUpFragment fragment = new SignUpFragment();
        Bundle arg = new Bundle();
        if (email == null) {
            arg.putString(ARG_EMAIL, "");
        } else {
            arg.putString(ARG_EMAIL, email);
        }
        fragment.setArguments(arg);
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_sign_up, container, false);
        login = (EditText) rootView.findViewById(R.id.sign_up_login);
        email = (EditText) rootView.findViewById(R.id.sign_up_email);
        password = (EditText) rootView.findViewById(R.id.sign_up_password);
        confirmPassword = (EditText) rootView.findViewById(R.id.sign_up_confirm_password);
        signUp = (Button)rootView.findViewById(R.id.sign_bt_sign_up);
        signUp.setOnClickListener(this);
        if (getArguments() != null && getArguments().containsKey(ARG_EMAIL)) {
            email.setText(getArguments().getString(ARG_EMAIL));
        }

        return rootView;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sign_bt_sign_up:
                if(!isValidEmail(email.getText())){
                    MyToast.show(getActivity(), R.string.no_valid_email);
                    break;
                }
                if(login.getText().length()>0&&email.getText().length()>0&&password.getText().length()>0 && confirmPassword.getText().length()>0){
                    if(password.getText().toString().equals(confirmPassword.getText().toString())){
                        SignUpRequest request = new SignUpRequest();
                        request.setLogin(login.getText().toString());
                        request.setEmail(email.getText().toString());
                        request.setPassword(password.getText().toString());
                        signUp(request);

                    }else {
                        MyToast.show(getActivity(), R.string.password_not_equals);
                    }
                }else {
                    MyToast.show(getActivity(), R.string.enter_all_edit);
                }
                break;
        }
    }


    private void signUp(SignUpRequest request) {
        signUp.setClickable(false);
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(Api.URL)
                .build();
        Api api = restAdapter.create(Api.class);

        api.signup(request, new Callback<SignUpResponse>() {
            @Override
            public void success(SignUpResponse signResponse, Response response) {
                MyLog.d("api", "" + signResponse.getLogin());
                DataUtil.setToken(getActivity(), signResponse.getAuthToken());
                DataUtil.setId(getActivity(), signResponse.getId());
                DataUtil.setLogin(getActivity(), signResponse.getLogin());
                MyLog.d("api", "" + DataUtil.getToken(getActivity()));
                callbacks.sign();
                signUp.setClickable(true);
            }

            @Override
            public void failure(RetrofitError error) {
                signUp.setClickable(true);
                if (error.isNetworkError()) {
                    Toast.makeText(getActivity(), R.string.network_error, Toast.LENGTH_LONG).show();

                } else {
                    switch (error.getResponse().getStatus()) {
                        case 400:
                            MyToast.show(getActivity(), error.getBodyAs(ResponseError.class).toString());

                            break;
                    }
                }
            }
        });
    }

    private boolean isValidEmail(CharSequence target) {
        if (target == null)
            return false;

        return android.util.Patterns.EMAIL_ADDRESS.matcher(target).matches();
    }
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            callbacks = (SignFragment.SignCallbacks) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException("Activity must implement SignCallbacks.");
        }
    }
}
