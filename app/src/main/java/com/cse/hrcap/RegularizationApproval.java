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
import com.cse.hrcap.network.RegularizationApprovalResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RegularizationApproval();
            }
        });

        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });



    }

    private void RegularizationApproval() {
//        UserService userService = getRetrofit().create(UserService.class);
        final RegularizationApprovalResponse regularizationApprovalResponse = new RegularizationApprovalResponse(
                CompanyId.getText().toString(),Employee.getText().toString(),MovementId.getText().toString(),
              FromTime.getText().toString(),ToTime.getText().toString(),MyNotes.getText().toString(),MyStatus
        );
        Call<RegularizationApprovalResponse> call = LeaveApiClient.getUserService().MyPostData(regularizationApprovalResponse);


        call.enqueue(new Callback<RegularizationApprovalResponse>() {
            @Override
            public void onResponse(Call<RegularizationApprovalResponse> call, Response<RegularizationApprovalResponse> response) {
                if (response.isSuccessful()) {
                    RegularizationApprovalResponse regularizationApprovalResponse1 = response.body();
                    Toast.makeText(getApplicationContext(), "Status is :" + regularizationApprovalResponse1.getStatus(), Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegularizationApprovalResponse> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

}