package com.cse.hrcap;

import static com.cse.hrcap.MainActivity.leaveroomDB;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.LeaveDraftAdapter;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveRequest;
import com.cse.hrcap.ui.LeaveRequestDraft.LeaveRequestDraftFragment;
import com.cse.hrcap.ui.attandancereg.AttandanceReglarizationFragment;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveDraft extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    EditText StartDate, EndDate, StartTime, EndTime, Reason;
    TextView EmployeeName, CompanyId;
    TextView TvStartTime, TvEndTime;
    Spinner LvSpinner;
    SwitchCompat DayType;
    public static String DayTypes;
    public static int spinnerval;
    public static String spinneritems;
    Button BtnSubmits, BtnCancels;
    int starthour, startminute, endhour, endminute;
    DatePickerDialog datePickerDialog;


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
        TvStartTime = findViewById(R.id.tV_starttime);
        TvEndTime = findViewById(R.id.tV_endtime);
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
            TvStartTime.setVisibility(View.GONE);
            TvEndTime.setVisibility(View.GONE);
            // Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        } else if (switchv == false) {
            DayType.setChecked(false);
            DayTypes = "Time";
            StartTime.setVisibility(View.VISIBLE);
            EndTime.setVisibility(View.VISIBLE);
            TvStartTime.setVisibility(View.VISIBLE);
            TvEndTime.setVisibility(View.VISIBLE);
            // Toast.makeText(getApplicationContext(), "Answer is: "+DayTypes, Toast.LENGTH_SHORT).show();
        } else {
            // Toast.makeText(getApplicationContext(), "Not True or False"+switchv, Toast.LENGTH_SHORT).show();
        }

        //Setting Edittext clicked
        StartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        LeaveDraft.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                starthour = i;
                                startminute = i1;
                                String time = starthour + ":" + startminute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);

                                    //12 hours time formet
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    StartTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //Set transparent Background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous selected time
                timePickerDialog.updateTime(starthour, startminute);
                timePickerDialog.show();

            }
        });

        //End time

        EndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        LeaveDraft.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                endhour = i;
                                endminute = i1;
                                String time = endhour + ":" + endminute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);

                                    //12 hours time formet
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    EndTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        }, 12, 0, false
                );
                //Set transparent Background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous selected time
                timePickerDialog.updateTime(endhour, endminute);
                timePickerDialog.show();

            }
        });


        StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(LeaveDraft.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                StartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(LeaveDraft.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                EndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        //Toggle Button
        DayType.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    DayTypes = "Full Day";
                    StartTime.setVisibility(View.GONE);
                    EndTime.setVisibility(View.GONE);
                    TvStartTime.setVisibility(View.GONE);
                    TvEndTime.setVisibility(View.GONE);
                    Toast.makeText(getApplicationContext(), "You Select:" + DayTypes, Toast.LENGTH_LONG).show();
                } else {

                    DayTypes = "Time";
                    StartTime.setVisibility(View.VISIBLE);
                    EndTime.setVisibility(View.VISIBLE);
                    TvStartTime.setVisibility(View.VISIBLE);
                    TvEndTime.setVisibility(View.VISIBLE);
                    Toast.makeText(getApplicationContext(), "You Select:" + DayTypes, Toast.LENGTH_LONG).show();
                }

            }
        });


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

        BtnCancels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();

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