package com.cse.hrcap;

import static com.cse.hrcap.R.id.MyBtnSubmit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import com.cse.hrcap.R;
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

import com.cse.hrcap.MyAdapters.AtdRegAprSummaryAdapter;
import com.cse.hrcap.MyAdapters.LeaveAprSumAdapter;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumRoomDB;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumRoomDB;
import com.cse.hrcap.network.MyApiClient;
import com.cse.hrcap.network.RegularizationApprovalResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegularizationApproval extends AppCompatActivity {

    RadioGroup radioGroup;
    TextView CompanyId, Employee, MovementId, FromTime, ToTime;

    TextView FullName, EmpCode, MyMovementId, StartDate, EndDate, MyFromTime, MyToTime, Reason, Status, EntryBy, EntryDate, Note;
    EditText MyNotes;
    Button BtnSubmits, BtnCancel, BtnCheck;
    String MyStatus = null;
    ProgressDialog progressDialog;

    List<AtdRegAprSumInfo> arrayList;


    int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regularization_approval);


        radioGroup = findViewById(R.id.myradios);
        CompanyId = findViewById(R.id.TVS_companyid);
        Employee = findViewById(R.id.TVS_employee);
        MovementId = findViewById(R.id.TVS_MovementId);
        FromTime = findViewById(R.id.TVS_FromTime);
        ToTime = findViewById(R.id.TVS_ToTime);
        MyNotes = findViewById(R.id.MSnotes);
        BtnSubmits = findViewById(R.id.MyBtnSubmit);
        BtnCancel = findViewById(R.id.bTnCancels);



        FullName = findViewById(R.id.a_FullName);
        //  EmpCode = findViewById(R.id.a_EmpCode);
        MyMovementId = findViewById(R.id.a_MovementId);
        StartDate = findViewById(R.id.a_StartDates);
        EndDate = findViewById(R.id.a_EndDates);
        MyFromTime = findViewById(R.id.a_FromTimes);
        MyToTime = findViewById(R.id.a_ToTimes);
        Reason = findViewById(R.id.a_Reasons);
        Status = findViewById(R.id.a_Statuss);
        EntryBy = findViewById(R.id.a_EntryBys);
        EntryDate = findViewById(R.id.a_EntryDates);
        Note = findViewById(R.id.a_Notes);


        Intent intent = getIntent();


        String companyId, fromTime, toTime, fullname, empcode, requestid, startdate, enddate,
                reason, status, entryby, entrydate, note;

        position = intent.getIntExtra("atdposition", 0);
        companyId = intent.getStringExtra("ICompanyId");
        requestid = intent.getStringExtra("IMovementId");
        fromTime = intent.getStringExtra("IFromTime");
        toTime = intent.getStringExtra("IToTime");
        fullname = intent.getStringExtra("Ifullname");
        empcode = intent.getStringExtra("Iempcode");
        startdate = intent.getStringExtra("Istartdate");
        enddate = intent.getStringExtra("Ienddate");
        reason = intent.getStringExtra("Ireason");
        status = intent.getStringExtra("Istatus");
        entryby = intent.getStringExtra("Ientryby");
        entrydate = intent.getStringExtra("Ientrydate");
        note = intent.getStringExtra("Inote");


        SharedPreferences bb = getSharedPreferences("my_prefs", 0);
        String employee = bb.getString("Employee", "");


        CompanyId.setText(companyId);
        Employee.setText(employee);
        MovementId.setText(requestid);
        FromTime.setText(fromTime);
        ToTime.setText(toTime);


        FullName.setText(fullname + "(" + empcode + ")");
        // EmpCode.setText(empcode);
        MyMovementId.setText(requestid);
        StartDate.setText(startdate);
        EndDate.setText(enddate);
        MyFromTime.setText(fromTime);
        MyToTime.setText(toTime);
        Reason.setText(reason);
        Status.setText(status);
        EntryBy.setText(entryby);
        EntryDate.setText(entrydate);
        Note.setText(note + "");




        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (checkedId == R.id.approved) {

                    Toast.makeText(getApplicationContext(), "Approve Selected", Toast.LENGTH_SHORT).show();
                    MyStatus = "Approve";

                    //some code
                } else if (checkedId == R.id.rejected) {

                    Toast.makeText(getApplicationContext(), "Reject Selected", Toast.LENGTH_SHORT).show();

                    //Need to know rejecte or rejected
                    MyStatus = "Reject";
                } else {

                    Toast.makeText(getApplicationContext(), "Please Check Approve or Reject", Toast.LENGTH_SHORT).show();
                }

            }
        });

        BtnSubmits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int selectedId = radioGroup.getCheckedRadioButtonId();
                if (selectedId==-1) {
                    Toast.makeText(getApplicationContext(), "Please Check Approve or Reject", Toast.LENGTH_SHORT).show();
                } else {
                    RegularizationApprovals();
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

    private void RegularizationApprovals() {

        progressDialog = new ProgressDialog(RegularizationApproval.this);
        progressDialog.setMessage("Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
        final RegularizationApprovalResponse regularizationApprovalResponse = new RegularizationApprovalResponse(
                CompanyId.getText().toString(), Employee.getText().toString(), MovementId.getText().toString(),
                FromTime.getText().toString(), ToTime.getText().toString(), MyNotes.getText().toString(), MyStatus
        );
        Call<RegularizationApprovalResponse> call = MyApiClient.getUserService().MyPostData(regularizationApprovalResponse);


        call.enqueue(new Callback<RegularizationApprovalResponse>() {
            @Override
            public void onResponse(Call<RegularizationApprovalResponse> call, Response<RegularizationApprovalResponse> response) {
                if (response.isSuccessful()) {
                    RegularizationApprovalResponse regularizationApprovalResponse1 = response.body();

                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Status is :" + regularizationApprovalResponse1.getStatus(), Toast.LENGTH_LONG).show();

                    if (regularizationApprovalResponse1.getStatus() != null) {

                        AtdRegAprSumRoomDB db = AtdRegAprSumRoomDB.getDbInstances(getApplicationContext());
                        db.atdRegAprSumDAO().deleteRowAtdRegApproval(position);

//                        AtdRegAprSummaryAdapter adapter = new AtdRegAprSummaryAdapter(arrayList, getApplicationContext());
//                        arrayList.remove(position);
//                        adapter.notifyDataSetChanged();


                        finish();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<RegularizationApprovalResponse> call, Throwable t) {
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