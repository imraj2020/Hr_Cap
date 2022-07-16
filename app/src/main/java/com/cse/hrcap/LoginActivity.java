package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.cse.hrcap.ui.home.HomeFragment;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.HttpResponse;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.NameValuePair;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.HttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.entity.UrlEncodedFormEntity;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.client.methods.HttpPost;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.impl.client.DefaultHttpClient;
import com.google.firebase.crashlytics.buildtools.reloc.org.apache.http.message.BasicNameValuePair;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {

    TextInputEditText username;
    TextInputEditText userPassword;
    TextView tvRegisterHere;
    Button btnLogin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etLoginEmail);
        userPassword = findViewById(R.id.etLoginPass);
        tvRegisterHere = findViewById(R.id.tvRegisterHere);
        btnLogin = findViewById(R.id.btnLogin);


        btnLogin.setOnClickListener(view -> {
            if(TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(userPassword.getText().toString())){
                Toast.makeText(LoginActivity.this,"Username / Password Required", Toast.LENGTH_LONG).show();
            }else{
                //proceed to login
                login();
            }
        });
//        tvRegisterHere.setOnClickListener(view ->{
//            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//        });
    }
    public void login(){
//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername(username.getText().toString());
//        loginRequest.setUserPassword(userPassword.getText().toString());
        String userid = username.getText().toString();
        String password = userPassword.getText().toString();

        Call<LoginResponse> loginResponseCall = ApiClient.getUserService().userLogin(userid,password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    Toast.makeText(LoginActivity.this,"Login Successful"+loginResponse, Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            i.putExtra("EmployeeId", loginResponse.getEmployeeId());
                            i.putExtra("DesignationId", loginResponse.getDesignationId());
                            startActivity(i);
//                           startActivity(new Intent(LoginActivity.this,MainActivity.class).
//                                   putExtra("data",loginResponse.getEmployeeId()));
                        }
                    },700);

                }else{
                    Toast.makeText(LoginActivity.this,"Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(LoginActivity.this,"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });


    }

}