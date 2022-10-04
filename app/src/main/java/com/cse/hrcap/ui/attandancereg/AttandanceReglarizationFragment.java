package com.cse.hrcap.ui.attandancereg;

import static android.content.Context.MODE_PRIVATE;
import static com.cse.hrcap.databinding.AttandanceReglarizationFragmentBinding.*;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

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

import com.cse.hrcap.MainActivity;
import com.cse.hrcap.R;
import com.cse.hrcap.databinding.AttandanceReglarizationFragmentBinding;
import com.cse.hrcap.databinding.SelfAttandanceFragmentBinding;
import com.cse.hrcap.network.AttandanceRegularizationRequest;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveRequest;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AttandanceReglarizationFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private AttandanceReglarizationViewModel mViewModel;
    private Spinner spinner_reason;
    String[] Reason;
    public static String spinneritem;
    TextView TxtName,CheckDraft;
    EditText FromTime, ToTime, Note,StartDate,EndDate;
    DatePickerDialog  datePickerDialog;
    Button BtnCancel, BtnSubmit, BtnDraft;
    int  starthour, startminute,endhour,endminute;
    AttandanceReglarizationFragmentBinding binding;
    public  static int userChoice;
    public static AttandanceReglarizationFragment newInstance() {
        return new AttandanceReglarizationFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding =AttandanceReglarizationFragmentBinding.inflate(inflater);
        spinner_reason = binding.spinnerReason;
        TxtName = binding.txtname;
        StartDate = binding.etStartdate;
        EndDate = binding.etEnddate;
        FromTime = binding.etfromtime;
        ToTime = binding.ettotime;
        Note = binding.etnote;
        BtnCancel = binding.btncancel;
        CheckDraft = binding.checkdraft;
        BtnDraft = binding.btndraft;

        BtnSubmit = binding.btnSubmit;



        //getting full name
        Intent intent = getActivity().getIntent();
        String fullname = intent.getStringExtra("FullName");
        TxtName.setText(fullname);

        //Submit Button
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AttandanceRegularization();
            }
        });


        //Saving Draft
        //Checking Save As Draft

        BtnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Shared Preference for switch
                // Creating a shared pref object
                // with a file name "MySharedPref"
                // in private mode

                SharedPreferences sharedPreferences = getActivity().getPreferences(Context.MODE_PRIVATE);
                SharedPreferences.Editor myEdit = sharedPreferences.edit();

                // write all the data entered by the user in SharedPreference and apply
                myEdit.putString("Start Date", StartDate.getText().toString());
                myEdit.putString("End Date", EndDate.getText().toString());
                myEdit.putString("From Time", FromTime.getText().toString());
                myEdit.putString("To Time", ToTime.getText().toString());
                myEdit.putString("Reason", spinneritem);
                myEdit.putString("Note", Note.getText().toString());




                // myEdit.putInt("Day Type", Integer.parseInt(age.getText().toString()));
                myEdit.apply();

                Toast.makeText(requireContext(), "Data Saved As Draft", Toast.LENGTH_SHORT).show();
            }
        });






        //Checking Draft
        CheckDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Fetching the stored data
                // from the SharedPreference
                SharedPreferences sh = getActivity().getPreferences(Context.MODE_PRIVATE);

                SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName",MODE_PRIVATE);
                int spinnerValue = sharedPref.getInt("userChoiceSpinner",-1);
                if(spinnerValue != -1) {
                    // set the selected value of the spinner
                    spinner_reason.setSelection(spinnerValue);
                }

                String start_date = sh.getString("Start Date", "");
                String end_date = sh.getString("End Date", "");
                String Fromtime = sh.getString("From Time", "");
                String Totime = sh.getString("To Time", "");
                String Reasons = sh.getString("Reason", spinneritem);
                String Notes = sh.getString("Note", "");



                // Setting the fetched data
                // in the EditTexts
                StartDate.setText(start_date);
                EndDate.setText(end_date);
                FromTime.setText(Fromtime);
                ToTime.setText(Totime);

                Note.setText(Notes);

                CheckDraft.setVisibility(View.GONE);


                // Toast.makeText(requireContext(), "Retrive Successfull", Toast.LENGTH_SHORT).show();

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
                datePickerDialog = new DatePickerDialog(requireContext(),
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
                        requireContext(),
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
                        requireContext(),
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



        Reason = getResources().getStringArray(R.array.Reason);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.Reason, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner_reason.setAdapter(adapter);
        spinner_reason.setOnItemSelectedListener(this);


        // Cancel Button Click Event
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Launching new Activity on selecting single List Item
                Intent i = new Intent(getActivity(), MainActivity.class);
                startActivity(i);
            }
        });


        return binding.getRoot();
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
        SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName",0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userChoiceSpinner",userChoice);
        prefEditor.commit();
        Toast.makeText(parent.getContext(), spinneritem, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    private void AttandanceRegularization() {
        //getting full name
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employee = intent.getStringExtra("Employee");
        final AttandanceRegularizationRequest attandanceRegularizationRequest = new AttandanceRegularizationRequest
                (companyid,employee,StartDate.getText().toString(),EndDate.getText().toString()
                ,FromTime.getText().toString(),ToTime.getText().toString(),spinneritem,Note.getText().toString());
        Call<AttandanceRegularizationRequest> call = LeaveApiClient.getUserService().PostData(attandanceRegularizationRequest);


        call.enqueue(new Callback<AttandanceRegularizationRequest>() {
            @Override
            public void onResponse(Call<AttandanceRegularizationRequest> call, Response<AttandanceRegularizationRequest> response) {
                if (response.isSuccessful()){
                    AttandanceRegularizationRequest attandanceRegularizationRequest1 = response.body();
                    Toast.makeText(requireContext(), "Status is :"+attandanceRegularizationRequest1.getStatus(), Toast.LENGTH_LONG).show();


                }
                else {
                    Toast.makeText(requireContext(),"Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<AttandanceRegularizationRequest> call, Throwable t) {
                Toast.makeText(requireContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }


}