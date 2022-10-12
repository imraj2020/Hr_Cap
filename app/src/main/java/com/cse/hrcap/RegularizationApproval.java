package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class RegularizationApproval extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView CompanyId, Employee, MovementId,FromTime,ToTime;
    EditText MyNotes;
    Button BtnSubmit, BtnCancel;
    public static String MyStatus;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regularization_approval);
        radioGroup = findViewById(R.id.myradios);
        CompanyId =findViewById(R.id.TVS_companyid);
        Employee =findViewById(R.id.TVS_employee);
        MovementId =findViewById(R.id.TVS_MovementId);
        FromTime =findViewById(R.id.TVS_FromTime);
        ToTime = findViewById(R.id.TVS_ToTime);
        MyNotes =findViewById(R.id.MSnotes);
        BtnSubmit =findViewById(R.id.bTnSubmits);
        BtnCancel = findViewById(R.id.bTnCancels);


        Intent intent =getIntent();
        String companyId = intent.getStringExtra("ICompanyId");
        String employee = intent.getStringExtra("IEmployee");
        String movementid = intent.getStringExtra("IMovementId");
        String fromtime = intent.getStringExtra("IFromTime");
        String totime = intent.getStringExtra("IToTime");



        CompanyId.setText(companyId);
        Employee.setText(employee);
        MovementId.setText(movementid);
        FromTime.setText(fromtime);
        ToTime.setText(totime);




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.approved) {

                    Toast.makeText(getApplicationContext(), "Approved Selected", Toast.LENGTH_SHORT).show();
                    MyStatus = "Approve";

                    //some code
                } else if (checkedId == R.id.rejected) {

                    Toast.makeText(getApplicationContext(), "Reject Selected", Toast.LENGTH_SHORT).show();

                    //Need to know rejecte or rejected
                    MyStatus = "Reject";
                }else{

                    Toast.makeText(getApplicationContext(), "Please Check Approve or Reject", Toast.LENGTH_SHORT).show();
                }

            }
        });




    }
}