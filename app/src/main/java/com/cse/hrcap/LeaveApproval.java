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



    }







}