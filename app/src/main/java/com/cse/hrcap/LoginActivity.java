package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.os.Handler;

import com.cse.hrcap.network.LoginResponse;
import com.cse.hrcap.network.MyApiClient;
import com.google.android.material.textfield.TextInputEditText;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LoginActivity extends AppCompatActivity {
    public static SQLiteDatabase sqLiteDatabase;
    ProgressDialog myprogressDialog;
    TextInputEditText username;
    TextInputEditText userPassword;
    TextView tvRegisterHere;
    Button btnLogin;
    LoginDbHelper dbs;
    ProgressBar progressBar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        username = findViewById(R.id.etLoginEmail);
        userPassword = findViewById(R.id.etLoginPass);
        btnLogin = findViewById(R.id.btnLogin);
        dbs = new LoginDbHelper(this);
        progressBar= (ProgressBar) findViewById(R.id.simpleProgressBar);
        progressBar.setVisibility(View.INVISIBLE);




        btnLogin.setOnClickListener(view -> {


            if (TextUtils.isEmpty(username.getText().toString()) || TextUtils.isEmpty(userPassword.getText().toString())) {
                Toast.makeText(LoginActivity.this, "Username / Password Required", Toast.LENGTH_LONG).show();
            } else {
                //proceed to login
                login();

            }
        });
//        tvRegisterHere.setOnClickListener(view ->{
//            startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
//        });
    }

    public void login() {


//        LoginRequest loginRequest = new LoginRequest();
//        loginRequest.setUsername(username.getText().toString());
//        loginRequest.setUserPassword(userPassword.getText().toString());
        String userid = username.getText().toString();
        String password = userPassword.getText().toString();
        progressBar.setVisibility(View.VISIBLE);





        Call<LoginResponse> loginResponseCall = MyApiClient.getUserService().userLogin(userid, password);
        loginResponseCall.enqueue(new Callback<LoginResponse>() {
            @Override
            public void onResponse(Call<LoginResponse> call, Response<LoginResponse> response) {
                if (response.isSuccessful()) {

                    //database cursor for store login
                    dbs = new LoginDbHelper(getApplicationContext());
                    Cursor cursor = dbs.alldata();
                    if (cursor.getCount() == 0) {
                        LoginDbHelper LoginDbHelper = new LoginDbHelper(getApplicationContext());
                        LoginDbHelper.insertRecord(userid, password);
                    } else {
                       //  Toast.makeText(getApplicationContext(), "Data Already Exist", Toast.LENGTH_SHORT).show();
                    }
                    //Progress dilogue start

                    Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_LONG).show();
                    LoginResponse loginResponse = response.body();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                            //Here define all your sharedpreferences code with key and value
                            SharedPreferences prefs = getSharedPreferences("my_prefs", MODE_PRIVATE);
                            SharedPreferences.Editor edit = prefs.edit();
                            edit.putString("Employee", userid );
                            edit.putString("CompanyId", loginResponse.getCompanyId());
                            edit.commit();


                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.putExtra("Employee", userid);
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
                            progressBar.setVisibility(View.INVISIBLE);
                            startActivity(i);
                            LoginActivity.this.finish();
//                           startActivity(new Intent(LoginActivity.this,MainActivity.class).
//                                   putExtra("data",loginResponse.getEmployeeId()));
                        }
                    }, 700);

                } else {
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Login Failed", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<LoginResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

                //
                String userid = username.getText().toString();
                String password = userPassword.getText().toString();
                progressBar.setVisibility(View.VISIBLE);

                if(userid.equals("")||password.equals("")){
                    progressBar.setVisibility(View.INVISIBLE);
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                }
                else {
                    Boolean checkuserpass = dbs.checkusernamepassword(userid, password);
                    if (checkuserpass == true) {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Sign in successful", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        intent.putExtra("Employee", userid);
                        startActivity(intent);
                    } else {
                        progressBar.setVisibility(View.INVISIBLE);
                        Toast.makeText(LoginActivity.this, "Invalid Username Or Password", Toast.LENGTH_SHORT).show();
                    }
                }

                //
            }
        });

    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to exit?")
                .setCancelable(false)
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        LoginActivity.this.finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();
                    }
                });
        AlertDialog alert = builder.create();
        alert.show();

    }
}