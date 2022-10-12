package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;

import java.util.List;

public class LeaveDraft extends AppCompatActivity {
    EditText StartDate, EndDate, StartTime,EndTime,Reason;
    TextView EmployeeName, CompanyId;
    Spinner LvSpinner;
    SwitchCompat DayType;
    public static String DayTypes;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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





        Intent intent =getIntent();
        int mypositions = intent.getIntExtra("Position",0);
        Toast.makeText(getApplicationContext(), "Data Position is "+mypositions, Toast.LENGTH_SHORT).show();

        LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(getApplicationContext());



        List<LeaveDraftInfo> list = db.leaveDraftDAO().getAllDatafromRow(mypositions);

        boolean switchv = list.get(0).isSwitchvalue();
        int spinnerval = list.get(0).getSpinnervalue();

        if(switchv==true){
            DayType.setChecked(true);
            DayTypes = "Full Day";
            Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        }
        else if(switchv==false){
            DayType.setChecked(false);
            DayTypes = "Time";
            Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Not True or False"+switchv, Toast.LENGTH_SHORT).show();
        }












    }



}