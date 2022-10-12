package com.cse.hrcap;

import static com.cse.hrcap.MainActivity.leaveroomDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveRequest;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveDraft extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText StartDate, EndDate, StartTime, EndTime, Reason;
    TextView EmployeeName, CompanyId;
    Spinner LvSpinner;
    SwitchCompat DayType;
    public static String DayTypes;
    public static int spinnerval;
    public static String spinneritems;
    Button BtnSubmits, BtnCancels;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //test
        Intent intent = getIntent();
        int mypositions = intent.getIntExtra("Position", 0);
        // Toast.makeText(getApplicationContext(), "Data Position is "+mypositions, Toast.LENGTH_SHORT).show();

        LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(getApplicationContext());


        List<LeaveDraftInfo> list = db.leaveDraftDAO().getAllDatafromRow(mypositions);

        boolean switchv = list.get(0).isSwitchvalue();

        spinnerval = list.get(0).getSpinnervalue();
        String Sd = list.get(0).getStartdate();
        String Ed = list.get(0).getEnddate();
        String St = list.get(0).getStarttime();
        String Et = list.get(0).getEndtime();
        String Re = list.get(0).getReason();
        String Em = list.get(0).getEmployee();
        String Co = list.get(0).getCompanyid();








        setContentView(R.layout.activity_leave_draft);
        StartDate = findViewById(R.id.eTstartdate);
        EndDate = findViewById(R.id.eTenddate);
        StartTime = findViewById(R.id.eTstarttime);
        EndTime = findViewById(R.id.eTendtime);
        Reason = findViewById(R.id.eTreason);
        EmployeeName = findViewById(R.id.tVempname);
        CompanyId = findViewById(R.id.tVcompanyid);
        LvSpinner = findViewById(R.id.lvspinner);
        DayType = findViewById(R.id.tGdaytype);
        BtnSubmits = findViewById(R.id.submitbtn);
        BtnCancels = findViewById(R.id.cancelbtn);
        StartDate.setText(Sd);
        EndDate.setText(Ed);
        StartTime.setText(St);
        EndTime.setText(Et);
        Reason.setText(Re);
        EmployeeName.setText(Em);
        CompanyId.setText(Co);



        //Switch value
        if (switchv == true) {
            DayType.setChecked(true);
            DayTypes = "Full Day";
            StartTime.setVisibility(View.GONE);
            EndTime.setVisibility(View.GONE);
            // Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        } else if (switchv == false) {
            DayType.setChecked(false);
            DayTypes = "Time";
            // Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(getApplicationContext(), "Not True or False"+switchv, Toast.LENGTH_SHORT).show();
        }




        LvSpinner.setOnItemSelectedListener(this);



        //Testing spinner
        LvSpinner.post(new Runnable() {
            public void run() {
                LvSpinner.setSelection(spinnerval);
            }
        });


        loadSpinnerData();

        BtnSubmits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveRequest();
            }
        });




    }




    private void loadSpinnerData() {

        List<String> labels = leaveroomDB.leaveDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        LvSpinner.setAdapter(dataAdapter);
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {


            spinneritems = parent.getItemAtPosition(position).toString();



        Toast.makeText(parent.getContext(), "You selected: " + position,
                Toast.LENGTH_LONG).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void leaveRequest() {
//        UserService userService = getRetrofit().create(UserService.class);
        final LeaveRequest leaveRequest = new LeaveRequest(EmployeeName.getText().toString(), spinneritems,
                DayTypes, StartDate.getText().toString(), EndDate.getText().toString(),
                Reason.getText().toString(), StartTime.getText().toString(), EndTime.getText().toString(),
                CompanyId.getText().toString());
        Call<LeaveRequest> call = LeaveApiClient.getUserService().PostData(leaveRequest);


        call.enqueue(new Callback<LeaveRequest>() {
            @Override
            public void onResponse(Call<LeaveRequest> call, Response<LeaveRequest> response) {
                if (response.isSuccessful()) {
                    LeaveRequest leaveResponse = response.body();
                    Toast.makeText(getApplicationContext(), "Status is :" + leaveResponse.getStatus(), Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(getApplicationContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LeaveRequest> call, Throwable t) {
                Toast.makeText(getApplicationContext(), "Throwable " + t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }




}