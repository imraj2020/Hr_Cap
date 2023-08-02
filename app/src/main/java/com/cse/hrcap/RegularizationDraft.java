package com.cse.hrcap;

import androidx.appcompat.app.AppCompatActivity;

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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cse.hrcap.RoomRegEntryDraft.RegDraftInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftRoomDB;
import com.cse.hrcap.RoomRegReason.RegReasonRoomDB;
import com.cse.hrcap.network.AttandanceRegularizationRequest;
import com.cse.hrcap.network.MyApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegularizationDraft extends AppCompatActivity implements AdapterView.OnItemSelectedListener  {

    TextView Txt_Name;
    EditText StartDate, EndDate, FromTime,ToTime,Notes;
    Spinner Spinner_Reason;
    public static String myspinneritems;
    public static int spinnervalue;
    public static String employee;
    public static String companyid;
    Button Cancel, Submit;
    DatePickerDialog  datePickerDialog;
    int  starthour, startminute,endhour,endminute;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_regularization_draft);
        //shared preference

        // Fetching the stored data
        // from the SharedPreference
        SharedPreferences sh = getSharedPreferences("my_prefs", MODE_PRIVATE);

        employee = sh.getString("Employee", "");
        companyid = sh.getString("CompanyId", "");

       // Toast.makeText(getApplicationContext(),""+companyid,Toast.LENGTH_LONG).show();



        //test
        Intent intent = getIntent();
        int mypositions = intent.getIntExtra("DataPosition", 0);
        // Toast.makeText(getApplicationContext(), "Data Position is "+mypositions, Toast.LENGTH_SHORT).show();

        RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(getApplicationContext());


        List<RegDraftInfo> list = db.regDraftDAO().getAllDatafromRow(mypositions);


        spinnervalue = list.get(0).getSpinnervalue();
        String Sd = list.get(0).getStartdate();
        String Ed = list.get(0).getEnddate();
        String Ft = list.get(0).getFromttime();
        String Tt = list.get(0).getTotime();
        String Re = list.get(0).getReason();
        String Em = list.get(0).getEmployee();
        String Co = list.get(0).getCompanyid();
        String Note = list.get(0).getNotes();
        String fullname = list.get(0).getFullname();


        //typecast here
        Txt_Name = findViewById(R.id.txt_name);
        StartDate = findViewById(R.id.ET_startdate);
        EndDate = findViewById(R.id.ET_enddate);
        FromTime = findViewById(R.id.ET_fromtime);
        ToTime = findViewById(R.id.ET_totime);
        Notes = findViewById(R.id.ET_note);
        Submit = findViewById(R.id.ibtn_submit);
        Cancel = findViewById(R.id.ibtn_cancel);
        Spinner_Reason = findViewById(R.id.spinner_reasons);
        Spinner_Reason.setOnItemSelectedListener(this);
        Txt_Name.setText(fullname);
        StartDate.setText(Sd);
        EndDate.setText(Ed);
        FromTime.setText(Ft);
        ToTime.setText(Tt);
        Notes.setText(Note);





        loadSpinnerData();

        //Spinner Selection
        Spinner_Reason.post(new Runnable() {
            public void run() {
                Spinner_Reason.setSelection(spinnervalue);
            }
        });


        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttandanceRegularization();
            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //Select Start Date

        StartDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RegularizationDraft.this,
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

        //Select End Date
        EndDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // calender class's instance and get current date , month and year from calender
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR); // current year
                int mMonth = c.get(Calendar.MONTH); // current month
                int mDay = c.get(Calendar.DAY_OF_MONTH); // current day
                // date picker dialog
                datePickerDialog = new DatePickerDialog(RegularizationDraft.this,
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




        // Select From Time
        FromTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        RegularizationDraft.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                starthour = i;
                                startminute = i1;
                                String time = starthour +":" + startminute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    java.util.Date date = f24Hours.parse(time);

                                    //12 hours time formet
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    FromTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //Set transparent Background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous selected time
                timePickerDialog.updateTime(starthour,startminute);
                timePickerDialog.show();

            }
        });

        // Select To time
        ToTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        RegularizationDraft.this,
                        android.R.style.Theme_Holo_Dialog_MinWidth,
                        new TimePickerDialog.OnTimeSetListener() {
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                endhour = i;
                                endminute = i1;
                                String time = endhour +":" + endminute;
                                SimpleDateFormat f24Hours = new SimpleDateFormat("HH:mm");
                                try {
                                    Date date = f24Hours.parse(time);

                                    //12 hours time formet
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    ToTime.setText(f12Hours.format(date));
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                            }
                        },12,0,false
                );
                //Set transparent Background
                timePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                //Display previous selected time
                timePickerDialog.updateTime(endhour,endminute);
                timePickerDialog.show();

            }
        });

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        myspinneritems = parent.getItemAtPosition(position).toString();
       // Toast.makeText(getApplicationContext(), "item is: "+myspinneritems, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void loadSpinnerData() {

        RegReasonRoomDB db = RegReasonRoomDB.getDbInstances(getApplicationContext());
        List<String> labels = db.regReasonDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        Spinner_Reason.setAdapter(dataAdapter);
    }


    private void AttandanceRegularization() {

        final AttandanceRegularizationRequest attandanceRegularizationRequest = new AttandanceRegularizationRequest
                (companyid,employee,StartDate.getText().toString(),EndDate.getText().toString()
                        ,FromTime.getText().toString(),ToTime.getText().toString(),myspinneritems,Notes.getText().toString());
        Call<AttandanceRegularizationRequest> call = MyApiClient.getUserService().PostData(attandanceRegularizationRequest);


        call.enqueue(new Callback<AttandanceRegularizationRequest>() {
            @Override
            public void onResponse(Call<AttandanceRegularizationRequest> call, Response<AttandanceRegularizationRequest> response) {
                if (response.isSuccessful()){
                    AttandanceRegularizationRequest attandanceRegularizationRequest1 = response.body();
                    Toast.makeText(getApplicationContext(), "Status is :"+attandanceRegularizationRequest1.getStatus(), Toast.LENGTH_LONG).show();


                }
                else {
                    Toast.makeText(getApplicationContext(),"Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<AttandanceRegularizationRequest> call, Throwable t) {
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