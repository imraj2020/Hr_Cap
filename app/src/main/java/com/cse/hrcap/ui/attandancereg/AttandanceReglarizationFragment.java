package com.cse.hrcap.ui.attandancereg;

import static android.content.Context.MODE_PRIVATE;

import static androidx.navigation.fragment.FragmentKt.findNavController;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
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
import com.cse.hrcap.RoomUserInfo.UserInfo;
import com.cse.hrcap.RoomUserInfo.UserRoomDB;
import com.cse.hrcap.databinding.AttandanceReglarizationFragmentBinding;
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

public class AttandanceReglarizationFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AttandanceReglarizationViewModel mViewModel;
    private Spinner spinner_reason;
    String[] Reason;
    public static String spinneritem;
    TextView TxtName, CheckDraft;
    EditText FromTime, ToTime, Note, StartDate, EndDate;
    DatePickerDialog datePickerDialog;
    int starthour, startminute, endhour, endminute;
    Button BtnCancel, BtnSubmit, BtnDraft;
    AttandanceReglarizationFragmentBinding binding;
    public static int userChoice;
    ProgressDialog progressDialog;
    public String TXTEndDate, TXTStartDate;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdfs = new SimpleDateFormat("dd/MM/yyyy");

    SimpleDateFormat timeFormat = new SimpleDateFormat("hh:mm aa", Locale.getDefault());

    public String StartTimes, EndTimes;


    public static AttandanceReglarizationFragment newInstance() {
        return new AttandanceReglarizationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = AttandanceReglarizationFragmentBinding.inflate(inflater);
        spinner_reason = binding.spinnerReason;
        TxtName = binding.txtnames;
        StartDate = binding.etStartdate;
        EndDate = binding.etEnddate;
        FromTime = binding.etfromtime;
        ToTime = binding.ettotime;

        StartTimes = FromTime.getText().toString().trim();
        EndTimes = ToTime.getText().toString().trim();


        Note = binding.etnote;
        BtnCancel = binding.btncancel;
//        CheckDraft = binding.checkdraft;
        BtnDraft = binding.btndraft;

        BtnSubmit = binding.btnSubmit;

        TXTStartDate = sdfs.format(calendar.getTime());
        TXTEndDate = TXTStartDate;


        //getting full name
        //test
        Intent intents = getActivity().getIntent();
        String mypositions = intents.getStringExtra("Employee");
        // Toast.makeText(getApplicationContext(), "Data Position is "+mypositions, Toast.LENGTH_SHORT).show();

        UserRoomDB database = UserRoomDB.getDbInstance(requireContext());
        List<UserInfo> list = database.userDAO().getAllDatafromRow(mypositions);
        String fullnames = list.get(0).getFullname();
        TxtName.setText(fullnames);


        //Submit Button
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String Enddates, Endtimes;
                Enddates = EndDate.getText().toString().trim();
                Endtimes = ToTime.getText().toString().trim();

                if (Enddates.equals("Select Correct Date") && Endtimes.equals("Select Correct Time") ||
                        Enddates.equals("Select Correct Date") || Endtimes.equals("Select Correct Time")) {

                    Toast.makeText(requireContext(), "Select Correct Date/Time", Toast.LENGTH_LONG).show();
                } else {

                    if (spinner_reason.getSelectedItemPosition() == 0) {
                        // No item selected, show an error message
                        Toast.makeText(requireContext(), "Please Select Reason", Toast.LENGTH_SHORT).show();
                    } else {
                        AttandanceRegularization();
                    }

                }
            }
        });


        //Saving Draft
        //Checking Save As Draft

        BtnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (spinner_reason.getSelectedItemPosition() == 0) {
                    // No item selected, show an error message
                    Toast.makeText(requireContext(), "Please Select Reason", Toast.LENGTH_SHORT).show();
                } else {
                    if (isNetworkAvailable()) {

                        Intent intent = getActivity().getIntent();
                        String companyid = intent.getStringExtra("CompanyId");
                        String employee = intent.getStringExtra("Employee");

                        //Spinner Position
                        SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName", MODE_PRIVATE);
                        int spinnerValue = sharedPref.getInt("userChoiceSpinners", -1);

                        // Time And Date
                        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ',' dd.MM.yyyy");
                        String currentDateandTime = sdf.format(new Date());


                        RegDraftRoomDB db = RegDraftRoomDB.getDbInstance(requireContext());

                        RegDraftInfo drafts = new RegDraftInfo(employee, companyid, spinnerValue, StartDate.getText().toString(),
                                EndDate.getText().toString(), FromTime.getText().toString(), ToTime.getText().toString(),
                                spinneritem, currentDateandTime, Note.getText().toString(), TxtName.getText().toString());
                        db.regDraftDAO().insertRegDraft(drafts);
                        try {
                            Toast.makeText(requireContext(), " Saved As Draft ", Toast.LENGTH_SHORT).show();

                            Navigation.findNavController(requireView()).popBackStack(R.id.nav_home, true);
                            Navigation.findNavController(v).popBackStack(R.id.nav_attadancereg, true);
                            Navigation.findNavController(v).navigate(R.id.nav_regentrydraft);


                        } catch (Exception e) {
                            Toast.makeText(requireContext(), "Sorry Something Wrong", Toast.LENGTH_SHORT).show();
                        }

                    } else {
                        Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                    }
                }



            }
        });


        //Checking Draft
//        CheckDraft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Fetching the stored data
//                // from the SharedPreference
//                SharedPreferences sh = getActivity().getPreferences(Context.MODE_PRIVATE);
//
//                SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName",MODE_PRIVATE);
//                int spinnerValue = sharedPref.getInt("userChoiceSpinner",-1);
//                if(spinnerValue != -1) {
//                    // set the selected value of the spinner
//                    spinner_reason.setSelection(spinnerValue);
//                }
//
//                String start_date = sh.getString("Start Date", "");
//                String end_date = sh.getString("End Date", "");
//                String Fromtime = sh.getString("From Time", "");
//                String Totime = sh.getString("To Time", "");
//                String Reasons = sh.getString("Reason", spinneritem);
//                String Notes = sh.getString("Note", "");
//
//
//
//                // Setting the fetched data
//                // in the EditTexts
//                StartDate.setText(start_date);
//                EndDate.setText(end_date);
//                FromTime.setText(Fromtime);
//                ToTime.setText(Totime);
//
//                Note.setText(Notes);
//
//                CheckDraft.setVisibility(View.GONE);
//
//
//                // Toast.makeText(requireContext(), "Retrive Successfull", Toast.LENGTH_SHORT).show();
//
//            }
//        });


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
                                StartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                TXTStartDate = StartDate.getText().toString();
                                EndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                TXTEndDate = EndDate.getText().toString();
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

                                EndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                TXTEndDate = EndDate.getText().toString();
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


        // Cancel Button Click Event
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requireActivity().onBackPressed();

            }
        });

        compareDates();
        loadSpinnerData();
        spinner_reason.setOnItemSelectedListener(this);

        return binding.getRoot();
    }

    // In your function where you want to compare the dates:
    public void compareDates() {

        sdfs.setLenient(false);

        try {
            // Parse the strings into Date objects
            Date startDate = sdfs.parse(TXTStartDate);
            Date endDate = sdfs.parse(TXTEndDate);
            StartDate.setText(TXTStartDate);
            EndDate.setText(TXTEndDate);

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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttandanceReglarizationViewModel.class);
        // TODO: Use the ViewModel
    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        spinneritem = parent.getItemAtPosition(position).toString();

        userChoice = spinner_reason.getSelectedItemPosition();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userChoiceSpinners", userChoice);
        prefEditor.commit();
        // Toast.makeText(parent.getContext(), spinneritem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }

    private void AttandanceRegularization() {
        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();

        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employee = intent.getStringExtra("Employee");
        final AttandanceRegularizationRequest attandanceRegularizationRequest = new AttandanceRegularizationRequest
                (companyid, employee, StartDate.getText().toString(), EndDate.getText().toString()
                        , FromTime.getText().toString(), ToTime.getText().toString(), spinneritem, Note.getText().toString());
        Call<AttandanceRegularizationRequest> call = MyApiClient.getUserService().PostData(attandanceRegularizationRequest);


        call.enqueue(new Callback<AttandanceRegularizationRequest>() {
            @Override
            public void onResponse(Call<AttandanceRegularizationRequest> call, Response<AttandanceRegularizationRequest> response) {
                if (response.isSuccessful()) {
                    AttandanceRegularizationRequest attandanceRegularizationRequest1 = response.body();
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), "Status is :" + attandanceRegularizationRequest1.getStatus(), Toast.LENGTH_LONG).show();
                    try {
                        Navigation.findNavController(requireView()).popBackStack(R.id.nav_home, true);
                        Navigation.findNavController(requireView()).popBackStack(R.id.nav_attadancereg, true);
                        Navigation.findNavController(getView()).navigate(R.id.nav_attadanceregsummary);
                    } catch (Exception e) {
                        Toast.makeText(requireContext(), "Sorry Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<AttandanceRegularizationRequest> call, Throwable t) {
                progressDialog.dismiss();
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }

    private void loadSpinnerData() {
//        LeaveTypeDbHelper db = new LeaveTypeDbHelper(requireContext());
//        List<String> labels = db.getAllLabels();
        RegReasonRoomDB db = RegReasonRoomDB.getDbInstances(requireContext());
        List<String> labels = db.regReasonDAO().getAllName();
        labels.add(0, "Select");
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner_reason.setAdapter(dataAdapter);
    }


}