package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.LeaveAprSumAdapter;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumRoomDB;
import com.cse.hrcap.network.MyApiClient;
import com.cse.hrcap.network.LeaveApprovalRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApproval extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView CompanyId, FullName, EmpCode, Employee, LeaveId, EntryDate, LeaveTypeName, TvLeaveId, FromDate, ToDate,
            FromTime, ToTime, TotalHours, LeaveStatusName;
    EditText MyNotes;
    Button BtnSubmit, BtnCancel;
    public static String MStatus;
    ProgressDialog progressDialog;

    public static int position;

    List<LeaveAprSumInfo> arrayList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leave_approval);


        radioGroup = findViewById(R.id.myradio);
        CompanyId = findViewById(R.id.TV_companyid);
        Employee = findViewById(R.id.TV_employee);
        LeaveId = findViewById(R.id.TV_leaveid);
        MyNotes = findViewById(R.id.Mnotes);
        BtnSubmit = findViewById(R.id.btnSubmits);
        BtnCancel = findViewById(R.id.btnCancels);


        FullName = findViewById(R.id.iFullname);
        EmpCode = findViewById(R.id.iEmpCode);
        EntryDate = findViewById(R.id.iEntryDate);
        LeaveTypeName = findViewById(R.id.iLeaveTypeName);
        TvLeaveId = findViewById(R.id.iLeaveId);
        FromDate = findViewById(R.id.iFromDate);
        ToDate = findViewById(R.id.iToDate);
        FromTime = findViewById(R.id.iFromTime);
        ToTime = findViewById(R.id.iToTime);
        TotalHours = findViewById(R.id.iTotalHours);
        LeaveStatusName = findViewById(R.id.iLeaveStatusName);


        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String employee = bb.getString("Employee", "");


        Intent intent = getIntent();
        String companyId, fullname, empCode, entryDate, leaveTypeName, leaveId, fromDate, toDate, fromTime, toTime,
                totalHours, leaveStatusName;

        position = intent.getIntExtra("position", 0);
        companyId = intent.getStringExtra("MCompanyId");
        fullname = intent.getStringExtra("MFullname");
        empCode = intent.getStringExtra("MEmpCode");
        entryDate = intent.getStringExtra("MEntryDate");
        leaveTypeName = intent.getStringExtra("MLeaveTypeName");
        leaveId = intent.getStringExtra("MLeaveId");
        fromDate = intent.getStringExtra("MFromDate");
        toDate = intent.getStringExtra("MToDate");
        fromTime = intent.getStringExtra("MFromTime");
        toTime = intent.getStringExtra("MToTime");
        totalHours = intent.getStringExtra("MTotalHours");
        leaveStatusName = intent.getStringExtra("MLeaveStatusName");

        CompanyId.setText(companyId);
        Employee.setText(employee);
        LeaveId.setText(leaveId);

        FullName.setText(fullname);
        EmpCode.setText("(" + empCode + ")");

        EntryDate.setText(entryDate);
        LeaveTypeName.setText(leaveTypeName);
        TvLeaveId.setText(leaveId);
        FromDate.setText(fromDate);
        ToDate.setText(toDate);
        FromTime.setText(fromTime != null ? fromTime : "");
        ToTime.setText(toTime != null ? toTime : "");
        TotalHours.setText(totalHours);
        LeaveStatusName.setText(leaveStatusName);


        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.approve) {

                    Toast.makeText(getApplicationContext(), "Approve Selected", Toast.LENGTH_SHORT).show();
                    MStatus = "Approved";

                    //some code
                } else if (checkedId == R.id.reject) {

                    Toast.makeText(getApplicationContext(), "Reject Selected", Toast.LENGTH_SHORT).show();
                    MStatus = "Rejected";
                } else {

                    Toast.makeText(getApplicationContext(), "Please Check Approve or Reject", Toast.LENGTH_SHORT).show();
                }

            }
        });


        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId==-1) {
                    Toast.makeText(getApplicationContext(), "Please Check Approve or Reject", Toast.LENGTH_SHORT).show();
                } else {
                    leaveApproval();
                }
            }
        });
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


    }


    private void leaveApproval() {
        progressDialog = new ProgressDialog(LeaveApproval.this);
        progressDialog.setMessage("Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final LeaveApprovalRequest leaveApprovalRequest = new LeaveApprovalRequest(CompanyId.getText().toString(),
                Employee.getText().toString(), LeaveId.getText().toString(), MStatus, MyNotes.getText().toString());
        Call<LeaveApprovalRequest> call = MyApiClient.getUserService().PostDatas(leaveApprovalRequest);


        call.enqueue(new Callback<LeaveApprovalRequest>() {
            @Override
            public void onResponse(Call<LeaveApprovalRequest> call, Response<LeaveApprovalRequest> response) {
                if (response.isSuccessful()) {
                    LeaveApprovalRequest leaveApprovalRequest1 = response.body();

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Status is :" + leaveApprovalRequest1.getStatus(), Toast.LENGTH_LONG).show();
                    if (leaveApprovalRequest1.getStatus() != null) {

                        LeaveAprSumRoomDB db = LeaveAprSumRoomDB.getDbInstance(getApplicationContext());
                        db.leaveAprSumDAO().deleteRowLeaveAprSum(position);

                        LeaveAprSumAdapter adapter = new LeaveAprSumAdapter(arrayList, getApplicationContext());
                        arrayList.remove(position);
                        adapter.notifyDataSetChanged();
                        finish();
                    }


                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LeaveApprovalRequest> call, Throwable t) {
                progressDialog.dismiss();
                if (isNetworkAvailable()) {
                    Toast.makeText(getApplicationContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(getApplicationContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }


}