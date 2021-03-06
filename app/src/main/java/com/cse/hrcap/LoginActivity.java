package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.cse.hrcap.network.LoginApiClient;
import com.cse.hrcap.network.LoginResponse;
import com.google.android.material.textfield.TextInputEditText;

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

        Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {

                if(response.isSuccessful()){
                    Toast.makeText(LoginActivity.this,"Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {


                            Intent i = new Intent(LoginActivity.this,MainActivity.class);
                            i.putExtra("EmployeeId", loginResponse.getEmployeeId());
                            i.putExtra("CompanyId", loginResponse.getCompanyId());
                            i.putExtra("DesignationId", loginResponse.getDesignationId());
                            i.putExtra("Designation", loginResponse.getDesignation());
                            i.putExtra("FullName", loginResponse.getFullName());
                            i.putExtra("Grade", loginResponse.getGrade());
                            i.putExtra("GradeId", loginResponse.getGradeId());
                            i.putExtra("EmpId", loginResponse.getEmpId());
                            i.putExtra("Department", loginResponse.getDepartment());
                            i.putExtra("DepartmentId", loginResponse.getDepartmentId());
                            i.putExtra("Position", loginResponse.getPosition());
                            i.putExtra("PositionId", loginResponse.getPositionId());
                            i.putExtra("Category", loginResponse.getCategory());
                            i.putExtra("CategoryId", loginResponse.getCategoryId());
                            i.putExtra("FirstName", loginResponse.getFirstName());
                            i.putExtra("MiddleName", loginResponse.getMiddleName());
                            i.putExtra("LastName", loginResponse.getLastName());
                            i.putExtra("Prefix", loginResponse.getPrefix());
                            i.putExtra("Suffix", loginResponse.getSuffix());
                            i.putExtra("PersonalEmail", loginResponse.getPersonalEmail());
                            i.putExtra("MobilenNo", loginResponse.getMobilenNo());
                            i.putExtra("ImageTitle", loginResponse.getImageTitle());
                            i.putExtra("ImagePath", loginResponse.getImagePath());
                            i.putExtra("JoiningDate", loginResponse.getJoiningDate());
                            i.putExtra("CostCenterId", loginResponse.getCostCenterId());
                            i.putExtra("PayCycleId", loginResponse.getPayCycleId());
                            i.putExtra("LocationId", loginResponse.getLocationId());
                            i.putExtra("SupervisorId", loginResponse.getSupervisorId());
                            i.putExtra("SupervisorName", loginResponse.getSupervisorName());
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