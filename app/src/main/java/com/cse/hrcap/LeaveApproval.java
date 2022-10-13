package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveApprovalRequest;
import com.cse.hrcap.network.LeaveRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApproval extends AppCompatActivity {
    RadioGroup radioGroup;
    TextView CompanyId, Employee, LeaveId;
    EditText MyNotes;
    Button BtnSubmit, BtnCancel;
    public static String MStatus;

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
        Intent intent =getIntent();
        String companyId = intent.getStringExtra("MCompanyId");
        String employee = intent.getStringExtra("MEmployee");
        String leaveId = intent.getStringExtra("MLeaveId");

        CompanyId.setText(companyId);
        Employee.setText(employee);
        LeaveId.setText(leaveId);






        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.approve) {

                    Toast.makeText(getApplicationContext(), "Approved Selected", Toast.LENGTH_SHORT).show();
                    MStatus = "Approved";

                    //some code
                } else if (checkedId == R.id.reject) {

                    Toast.makeText(getApplicationContext(), "Reject Selected", Toast.LENGTH_SHORT).show();
                    MStatus = "Rejected";
                }else{

                    Toast.makeText(getApplicationContext(), "Please Check Approve or Reject", Toast.LENGTH_SHORT).show();
                }

            }
        });


        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveApproval();
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
//        UserService userService = getRetrofit().create(UserService.class);
        final LeaveApprovalRequest leaveApprovalRequest = new LeaveApprovalRequest(CompanyId.getText().toString(),
                Employee.getText().toString(),LeaveId.getText().toString(),MStatus,MyNotes.getText().toString());
        Call<LeaveApprovalRequest> call = LeaveApiClient.getUserService().PostDatas(leaveApprovalRequest);


        call.enqueue(new Callback<LeaveApprovalRequest>() {
            @Override
            public void onResponse(Call<LeaveApprovalRequest> call, Response<LeaveApprovalRequest> response) {
                if (response.isSuccessful()) {
                    LeaveApprovalRequest leaveApprovalRequest1 = response.body();
                    Toast.makeText(getApplicationContext(), "Status is :" + leaveApprovalRequest1.getStatus(), Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LeaveApprovalRequest> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }







}