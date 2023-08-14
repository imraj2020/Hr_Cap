package com.cse.hrcap.ui.RegEntryDraftFrag;

import static android.content.Context.MODE_PRIVATE;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.SharedPreferences;
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
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftRoomDB;
import com.cse.hrcap.RoomRegReason.RegReasonRoomDB;
import com.cse.hrcap.databinding.FragmentRegEntryDraftBinding;
import com.cse.hrcap.network.AttandanceRegularizationRequest;
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

public class ReguEntryDraftFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private ReguEntryDraftViewModel mViewModel;
    FragmentRegEntryDraftBinding binding;
    TextView Txt_Name;
    EditText StartDate, EndDate, FromTime, ToTime, Notes;
    Spinner Spinner_Reason;
    public static String myspinneritems;
    public static int spinnervalue;
    public static String employee;
    public static String companyid;
    Button Cancel, Submit;
    DatePickerDialog datePickerDialog;
    int starthour, startminute, endhour, endminute;

    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdfs = new SimpleDateFormat("dd/MM/yyyy");
    public String EndDates, StartDates;

    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());

    public String StartTimes, EndTimes;

    public static int mypositions;

    public static ReguEntryDraftFragment newInstance() {
        return new ReguEntryDraftFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentRegEntryDraftBinding.inflate(inflater);

        //test
        SharedPreferences sh = requireActivity().getSharedPreferences("my_prefs", MODE_PRIVATE);

        employee = sh.getString("Employee", "");
        companyid = sh.getString("CompanyId", "");

        //test
        Bundle args = getArguments();
        mypositions = args.getInt("clicked_data_2", 0);


        if (mypositions == 0) {
            Toast.makeText(requireContext(), "Error:Data Position is " + mypositions, Toast.LENGTH_SHORT).show();
        }

        RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(requireContext());


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
        Txt_Name = binding.txtName;
        StartDate = binding.ETStartdate;
        EndDate = binding.ETEnddate;
        FromTime = binding.ETFromtime;
        ToTime = binding.ETTotime;
        StartTimes = FromTime.getText().toString().trim();
        EndTimes = ToTime.getText().toString().trim();

        Notes = binding.ETNote;
        Submit = binding.ibtnSubmit;
        Cancel = binding.ibtnCancel;
        Spinner_Reason = binding.spinnerReason;
        Spinner_Reason.setOnItemSelectedListener(this);
        Txt_Name.setText(fullname);
        StartDate.setText(Sd);
        StartDates = StartDate.getText().toString().trim();

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

                String Enddates, Endtimes;
                Enddates = EndDate.getText().toString().trim();
                Endtimes = ToTime.getText().toString().trim();

                if (Enddates.equals("Select Correct Date") && Endtimes.equals("Select Correct Time") ||
                        Enddates.equals("Select Correct Date") || Endtimes.equals("Select Correct Time")) {

                    Toast.makeText(requireContext(), "Select Correct Date/Time", Toast.LENGTH_LONG).show();
                } else {
                    AttandanceRegularization();
                }

            }
        });

        Cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
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
                datePickerDialog = new DatePickerDialog(requireContext(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                // set day of month , month and year value in the edit text
                                // StartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
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


        // Select From Time
        FromTime.setOnClickListener(new View.OnClickListener() {
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
                                    java.util.Date date = f24Hours.parse(time);

                                    //12 hours time formet
                                    SimpleDateFormat f12Hours = new SimpleDateFormat("hh:mm aa");
                                    FromTime.setText(f12Hours.format(date));
                                    StartTimes = FromTime.getText().toString();
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

        // Select To time
        ToTime.setOnClickListener(new View.OnClickListener() {
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
                                    ToTime.setText(f12Hours.format(date));
                                    EndTimes = ToTime.getText().toString();
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


        return binding.getRoot();
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

                Toast.makeText(requireContext(), "Start date should not be greater than end date", Toast.LENGTH_LONG).show();
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
            Date startTime = timeFormat.parse(FromTime.getText().toString());
            Date endTime = timeFormat.parse(ToTime.getText().toString());

            if (startTime.before(endTime)) {

            } else if (startTime.after(endTime)) {
                Toast.makeText(requireContext(), "End time should not be smaller than start time.", Toast.LENGTH_SHORT).show();
                ToTime.setText("Select Correct Time");


            } else {

            }

        } catch (ParseException e) {
            // Handle parsing error if the user enters an invalid time format.
            e.printStackTrace();
        }
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

        RegReasonRoomDB db = RegReasonRoomDB.getDbInstances(requireContext());
        List<String> labels = db.regReasonDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);


        // attaching data adapter to spinner
        Spinner_Reason.setAdapter(dataAdapter);
    }


    private void AttandanceRegularization() {

        final AttandanceRegularizationRequest attandanceRegularizationRequest = new AttandanceRegularizationRequest
                (companyid, employee, StartDate.getText().toString(), EndDate.getText().toString()
                        , FromTime.getText().toString(), ToTime.getText().toString(), myspinneritems, Notes.getText().toString());
        Call<AttandanceRegularizationRequest> call = MyApiClient.getUserService().PostData(attandanceRegularizationRequest);


        call.enqueue(new Callback<AttandanceRegularizationRequest>() {
            @Override
            public void onResponse(Call<AttandanceRegularizationRequest> call, Response<AttandanceRegularizationRequest> response) {
                if (response.isSuccessful()) {
                    AttandanceRegularizationRequest attandanceRegularizationRequest1 = response.body();
                    Toast.makeText(requireContext(), "Status is :" + attandanceRegularizationRequest1.getStatus(), Toast.LENGTH_LONG).show();

                    RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(requireContext().getApplicationContext());

                    db.regDraftDAO().deleteRegdraftinfo(mypositions);

                    Navigation.findNavController(requireView()).navigate(R.id.nav_attadanceregsummary);

                } else {
                    Toast.makeText(requireContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<AttandanceRegularizationRequest> call, Throwable t) {
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });

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
        mViewModel = new ViewModelProvider(this).get(ReguEntryDraftViewModel.class);
        // TODO: Use the ViewModel
    }

}