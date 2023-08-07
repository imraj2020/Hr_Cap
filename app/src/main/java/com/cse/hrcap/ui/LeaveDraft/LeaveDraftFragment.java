package com.cse.hrcap.ui.LeaveDraft;

import static com.cse.hrcap.MainActivity.leaveroomDB;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeave.MyRoomDB;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomUserInfo.UserInfo;
import com.cse.hrcap.RoomUserInfo.UserRoomDB;
import com.cse.hrcap.databinding.FragmentLeaveDraftBinding;
import com.cse.hrcap.network.LeaveRequest;
import com.cse.hrcap.network.MyApiClient;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveDraftFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private LeaveDraftViewModel mViewModel;
    FragmentLeaveDraftBinding binding;
    EditText StartDate, EndDate, StartTime, EndTime, Reason, DelegatePerson;
    TextView EmployeeName, CompanyId, EmpFullName;
    TextView TvStartTime, TvEndTime;
    Spinner LvSpinner;
    SwitchCompat DayType;
    public static String DayTypes;
    public static int spinnerval;
    public static String spinneritems;
    Button BtnSubmits, BtnCancels;
    int starthour, startminute, endhour, endminute;
    DatePickerDialog datePickerDialog;
    public MyRoomDB roomDB;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdfs = new SimpleDateFormat("dd/MM/yyyy");
    public String EndDates, StartDates;
    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());

    public  String StartTimes,EndTimes;


    public static LeaveDraftFragment newInstance() {
        return new LeaveDraftFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLeaveDraftBinding.inflate(inflater);



        //test
        Bundle args = getArguments();
        int mypositions = args.getInt("clicked_data_", 0);


        if (mypositions == 0) {
            Toast.makeText(requireContext(), "Error:Data Position is " + mypositions, Toast.LENGTH_SHORT).show();
        }

       // Toast.makeText(requireContext(), "Data Position is " + mypositions, Toast.LENGTH_SHORT).show();

        LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(requireContext());

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

        DelegatePerson = binding.etdraftdelegateperson;
        StartDate = binding.eTstartdate;
        EndDate = binding.eTenddate;
        StartTime = binding.eTstarttime;
        EndTime = binding.eTendtime;
        Reason = binding.eTreason;
        EmployeeName = binding.tVempname;
        CompanyId = binding.tVcompanyid;
        LvSpinner = binding.lvspinner;
        DayType = binding.tGdaytype;
        BtnSubmits = binding.submitbtn;
        BtnCancels = binding.cancelbtn;
        TvStartTime = binding.tVStarttime;
        TvEndTime = binding.tVEndtime;
        StartDate.setText(Sd);
        StartDates = StartDate.getText().toString().trim();


        EndDate.setText(Ed);
        StartTime.setText(St);
        EndTime.setText(Et);

        StartTimes = StartTime.getText().toString().trim();
        EndTimes = EndTime.getText().toString().trim();


        Reason.setText(Re);
        EmployeeName.setText(Em);
        CompanyId.setText(Co);
        EmpFullName = binding.tvempfullnames;


        UserRoomDB database = UserRoomDB.getDbInstance(requireContext());
        List<UserInfo> lists = database.userDAO().getAllUser();
        String fullname = lists.get(0).getFullname();
        EmpFullName.setText(fullname);


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
                        requireContext(),
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
                                    StartTimes = StartTime.getText().toString();
                                    onCheckTime();
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
                        requireContext(),
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
                                    EndTimes = EndTime.getText().toString();
                                    onCheckTime();
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
                datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                              //  StartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                StartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                StartDates = StartDate.getText().toString();
                                EndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                EndDates = EndDate.getText().toString();
                                compareDates();

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
                datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                              //  EndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                EndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                EndDates = EndDate.getText().toString();
                                compareDates();

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
                    Toast.makeText(requireContext(), "You Select:" + DayTypes, Toast.LENGTH_LONG).show();
                } else {

                    DayTypes = "Time";
                    StartTime.setVisibility(View.VISIBLE);
                    EndTime.setVisibility(View.VISIBLE);
                    TvStartTime.setVisibility(View.VISIBLE);
                    TvEndTime.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), "You Select:" + DayTypes, Toast.LENGTH_LONG).show();
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
                //finish();
                requireActivity().onBackPressed();
               // Navigation.findNavController(requireView()).navigate(R.id.nav_leavedraft);
            }
        });


        return binding.getRoot();
    }


    private void loadSpinnerData() {

        List<String> labels = leaveroomDB.leaveDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, labels);

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
                CompanyId.getText().toString(), DelegatePerson.getText().toString());
        Call<LeaveRequest> call = MyApiClient.getUserService().PostData(leaveRequest);


        call.enqueue(new Callback<LeaveRequest>() {
            @Override
            public void onResponse(Call<LeaveRequest> call, Response<LeaveRequest> response) {
                if (response.isSuccessful()) {
                    LeaveRequest leaveResponse = response.body();
                    Toast.makeText(requireContext(), "Status is :" + leaveResponse.getStatus(), Toast.LENGTH_LONG).show();
                    Navigation.findNavController(requireView()).navigate(R.id.nav_leavesummary);
                   // requireActivity().finish();

                } else {
                    Toast.makeText(requireContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }


            @Override
            public void onFailure(Call<LeaveRequest> call, Throwable t) {
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void compareDates() {

        sdfs.setLenient(false);

        try {
            // Parse the strings into Date objects
            Date startDate = sdfs.parse(StartDates);
            Date endDate = sdfs.parse(EndDates);
            StartDate.setText(StartDates);
            EndDate.setText(EndDates);

            // Compare the dates
            if (startDate.before(endDate)) {

            } else if (startDate.after(endDate)) {

                Toast.makeText(requireContext(),"StartDate Should not Greater then EndDate",Toast.LENGTH_LONG).show();
                EndDate.setText("Select Correct Date");
                // EtEndDate.setError("Select Correct Date");
            } else {

            }
        } catch (java.text.ParseException e) {
            // Handle the parsing error if the input strings are not in the correct format
            e.printStackTrace();
        }
    }

    public void onCheckTime() {

        try {
            Date startTime = timeFormat.parse(StartTime.getText().toString());
            Date endTime = timeFormat.parse(EndTime.getText().toString());

            if (startTime.before(endTime)){

            }
            else if (startTime.after(endTime)) {
                Toast.makeText(requireContext(), "End time should not be smaller than start time.", Toast.LENGTH_SHORT).show();
                EndTime.setText("Select Correct Time");


            }else {

            }

        } catch (ParseException e) {
            // Handle parsing error if the user enters an invalid time format.
            e.printStackTrace();
        }
    }


    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveDraftViewModel.class);
        // TODO: Use the ViewModel
    }
}