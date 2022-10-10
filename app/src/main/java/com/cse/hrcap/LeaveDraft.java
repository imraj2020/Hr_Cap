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

//        db.leaveDraftDAO().getAllDatafromRow(mypositions);


        List<LeaveDraftInfo> list = db.leaveDraftDAO().getAllDatafromRow(mypositions);

        boolean s = list.get(0).isSwitchvalue();

        if(s==true){
            DayType.setChecked(true);
            DayTypes = "Full Day";
            Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        }
        else if(s==false){
            DayType.setChecked(false);
            DayTypes = "Time";
            Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(getApplicationContext(), "Not True or False"+s, Toast.LENGTH_SHORT).show();
        }


        //Toast.makeText(getApplicationContext(), ""+s, Toast.LENGTH_SHORT).show();





        showDataFromDb();




    }


    private void showDataFromDb() {

//        List<StudentInfo> studentInfoList = roomDB.studentDAO().getAllStudent();
//        for (int i = 0; i<studentInfoList.size(); i++){
//            Log.d("StuentInfo", studentInfoList.get(i).getId()+"\t"
//                    +studentInfoList.get(i).getName()+"\t"
//                    +studentInfoList.get(i).getSubject()+"\t"
//                    +studentInfoList.get(i).getDepartment());
        }
}