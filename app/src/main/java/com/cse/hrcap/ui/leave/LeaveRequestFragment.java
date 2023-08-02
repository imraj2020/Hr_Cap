package com.cse.hrcap.ui.leave;

import static android.content.Context.MODE_PRIVATE;

import static com.cse.hrcap.MainActivity.leaveroomDB;

import androidx.appcompat.widget.SwitchCompat;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
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

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeave.LeaveInfo;
import com.cse.hrcap.RoomLeave.MyRoomDB;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftInfo;
import com.cse.hrcap.RoomLeaveDraft.LeaveDraftRoomDB;
import com.cse.hrcap.RoomUserInfo.UserInfo;
import com.cse.hrcap.RoomUserInfo.UserRoomDB;
import com.cse.hrcap.databinding.LeaveRequestFragmentBinding;
import com.cse.hrcap.network.EmployeeResponse;
import com.cse.hrcap.network.MyApiClient;
import com.cse.hrcap.network.LeaveRequest;
import com.cse.hrcap.network.UserService;
import com.google.android.gms.common.internal.Constants;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveRequestFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private LeaveRequestViewModel mViewModel;
    private LeaveRequestFragmentBinding binding;
    private UserService userService;
    TextView Leavetyperesponse, CheckDraft;
    Spinner spinner, spinnertwo;
    public static String label;
    TextView EmpName, CompanyId, TvStartTime, TvEndTime, TvDayType, TvLeaveType;
    EditText EtDay, EtStartDate, EtEndDate, EtStartTime, EtEndTime, EtReason;
    Button BtnSubmit, Btncheck, BtnCancel, BtnDraft;
    DatePickerDialog datePickerDialog;
    LinearLayout Lenearinfo;
    //  LeaveTypeDbHelper dbs;
    public MyRoomDB roomDB;
    int starthour, startminute, endhour, endminute;
    SwitchCompat mySwitch;
    public static String Status;
    TextView DateTime;
    public static boolean switchst;
    private final ArrayList<EmployeeResponse> empname = new ArrayList<>();
    Dialog dialog;
    String EmployeeID = "";
    ProgressDialog progressDialog;
    ProgressDialog mprogressDialog;
    private SimpleDateFormat dateFormat;
    public static boolean x = true;
    private long selectedStartDate = 0;
    private long selectedEndDate = 0;
    public String EndDate, StartDate;
    Calendar calendar = Calendar.getInstance();
    SimpleDateFormat sdfs = new SimpleDateFormat("dd/MM/yyyy");
    public LeaveRequestFragment() {
    }


    public static LeaveRequestFragment newInstance() {
        return new LeaveRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveRequestFragmentBinding.inflate(inflater);

        //Leavetyperesponse = binding.textViewResults;
        // EtDay = binding.etday;
        EmpName = binding.tvempname;
        CompanyId = binding.tvcompanyid;
        EtStartDate = binding.etstartdate;
        EtEndDate = binding.etenddate;
        EtStartTime = binding.etstarttime;
        EtEndTime = binding.etendtime;
        EtReason = binding.etreason;
        BtnSubmit = binding.btnsubmit;
        BtnCancel = binding.btncancel;
        spinner = binding.spinner;
        mySwitch = binding.tgdaytype;
        TvStartTime = binding.tvstarttime;
        TvEndTime = binding.tvendtime;
        BtnDraft = binding.btndraft;
//        CheckDraft = binding.tvCheckdraft;


//        Calendar calendar = Calendar.getInstance();
//        Date currentDate = calendar.getTime();
//        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); // current date
//        String formattedDate = sdf.format(currentDate);
//
//        EtStartDate.setText(formattedDate);
//        StartDate = EtStartDate.getText().toString();
//        EtEndDate.setText(EtStartDate.getText().toString());
//        EndDate = EtEndDate.getText().toString();

        // Inside your method or class

        StartDate = sdfs.format(calendar.getTime());
        EndDate = StartDate;



        //spinnertwo = binding.spinnerday;
        spinner.setOnItemSelectedListener(this);
        // spinnertwo.setOnItemSelectedListener(this);
        EtStartTime.setVisibility(View.GONE);
        EtEndTime.setVisibility(View.GONE);
        TvStartTime.setVisibility(View.GONE);
        TvEndTime.setVisibility(View.GONE);


//        setLeaveDraftDatabase();


        //Checking Save As Draft

        BtnDraft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mprogressDialog = new ProgressDialog(requireContext());
                mprogressDialog.setMessage("Saving...");
                mprogressDialog.setCancelable(false);
                mprogressDialog.show();

                if (isNetworkAvailable()) {
                    //Spinner Position
                    SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName", MODE_PRIVATE);
                    int spinnerValue = sharedPref.getInt("userChoiceSpinner", -1);

                    // Time And Date
                    SimpleDateFormat sdf = new SimpleDateFormat("HH:mm ',' dd.MM.yyyy");
                    String currentDateandTime = sdf.format(new Date());

                    //label and status ()
//                label,Status


                    Intent intent = getActivity().getIntent();
                    String companyid = intent.getStringExtra("CompanyId");
                    String employee = intent.getStringExtra("Employee");

                    //Testing new Roomdb
                    LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(requireContext());


                    LeaveDraftInfo mydraft = new LeaveDraftInfo(employee, spinnerValue, switchst, EtStartDate.getText().toString(),
                            EtEndDate.getText().toString(), EtStartTime.getText().toString(), EtEndTime.getText().toString(),
                            EtReason.getText().toString(), companyid, currentDateandTime, label, Status);
                    db.leaveDraftDAO().insertLeaveDraft(mydraft);

                    try {
                        mprogressDialog.dismiss();
                        Toast.makeText(requireContext(), " Saved As Draft ", Toast.LENGTH_SHORT).show();
                        Navigation.findNavController(v).navigate(R.id.nav_leavedraft);
                    } catch (Exception e) {
                        Toast.makeText(requireContext(), "Sorry Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    mprogressDialog.dismiss();
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }

            }
        });


        SharedPreferences preferences = this.getActivity().getPreferences(MODE_PRIVATE);
        boolean switchstatus = preferences.getBoolean("switch", true);  //default is true
        if (switchstatus = true) //if (tgpref) may be enough, not sure
        {
            switchst = true;
            mySwitch.setChecked(true);
            Status = "Full Day";
            // Toast.makeText(requireContext(),"Status is:"+tgpref,Toast.LENGTH_LONG).show();
        }
        if (switchstatus = false) {
            switchst = false;
            mySwitch.setChecked(false);
            Status = "Time";
            // Toast.makeText(requireContext(),"Status is:"+tgpref,Toast.LENGTH_LONG).show();
        }

        //Toggle Button
        mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

                if (isChecked) {

                    switchst = true;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("switch", true); // value to store
                    editor.commit();
                    Status = "Full Day";


                    EtStartTime.setVisibility(View.GONE);
                    EtEndTime.setVisibility(View.GONE);
                    TvStartTime.setVisibility(View.GONE);
                    TvEndTime.setVisibility(View.GONE);
                    Toast.makeText(requireContext(), "You Select:" + Status, Toast.LENGTH_LONG).show();
                } else {
                    switchst = false;
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putBoolean("switch", false); // value to store
                    editor.commit();
                    Status = "Time";

                    EtStartTime.setVisibility(View.VISIBLE);
                    EtEndTime.setVisibility(View.VISIBLE);
                    TvStartTime.setVisibility(View.VISIBLE);
                    TvEndTime.setVisibility(View.VISIBLE);
                    Toast.makeText(requireContext(), "You Select:" + Status, Toast.LENGTH_LONG).show();
                }
            }
        });


        // Checking draft

//        CheckDraft.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
////                if(spinnerValue != -1) {
////                    // set the selected value of the spinner
////                    spinner.setSelection(spinnerValue);
////                }
//
//
//            }
//        });


        // Cancel Button Click Event
        BtnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                requireActivity().onBackPressed();
            }
        });

        //Start time

        EtStartTime.setOnClickListener(new View.OnClickListener() {
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
                                    EtStartTime.setText(f12Hours.format(date));
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

        EtEndTime.setOnClickListener(new View.OnClickListener() {
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
                                    EtEndTime.setText(f12Hours.format(date));
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


        EtStartDate.setOnClickListener(new View.OnClickListener() {
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
                                EtStartDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                StartDate = EtStartDate.getText().toString();
                                EtEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                EndDate = EtEndDate.getText().toString();
                                compareDates();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        EtEndDate.setOnClickListener(new View.OnClickListener() {
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

                                EtEndDate.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                                EndDate = EtEndDate.getText().toString();
                                compareDates();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employeename = intent.getStringExtra("Employee");
        String fullname = intent.getStringExtra("FullName");


        Intent intents = getActivity().getIntent();
        String mypositions = intents.getStringExtra("Employee");

        UserRoomDB database = UserRoomDB.getDbInstance(requireContext());


        List<UserInfo> list = database.userDAO().getAllDatafromRow(mypositions);
        String fullnames = list.get(0).getFullname();

        binding.tvempfullname.setText(fullnames);
        EmpName.setText(employeename);
        CompanyId.setText(companyid);
        // spinner.setOnItemSelectedListener(this);

//        leaveTypes();
//        setDatabase();
        loadSpinnerData();
        DelegatePerson();
        compareDates();
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                        leaveRequest();


            }
        });
        // return inflater.inflate(R.layout.leave_request_fragment, container, false);
        return binding.getRoot();
    }


    // In your function where you want to compare the dates:
    public void compareDates() {

        sdfs.setLenient(false);

        try {
            // Parse the strings into Date objects
            Date startDate = sdfs.parse(StartDate);
            Date endDate = sdfs.parse(EndDate);
            EtStartDate.setText(StartDate);
            EtEndDate.setText(EndDate);

            // Compare the dates
            if (startDate.before(endDate)) {

            } else if (startDate.after(endDate)) {

                Toast.makeText(requireContext(),"StartDate Should not Greater then EndDate",Toast.LENGTH_LONG).show();
                EtEndDate.setText("Select Correct Date");
               // EtEndDate.setError("Select Correct Date");
            } else {

                 }
        } catch (java.text.ParseException e) {
            // Handle the parsing error if the input strings are not in the correct format
            e.printStackTrace();
        }
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveRequestViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * Function to load the spinner data from SQLite database
     */
    private void loadSpinnerData() {
//        LeaveTypeDbHelper db = new LeaveTypeDbHelper(requireContext());
//        List<String> labels = db.getAllLabels();
        List<String> labels = leaveroomDB.leaveDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(), android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        label = parent.getItemAtPosition(position).toString();
        int userChoice = spinner.getSelectedItemPosition();
        SharedPreferences sharedPref = getActivity().getSharedPreferences("FileName", 0);
        SharedPreferences.Editor prefEditor = sharedPref.edit();
        prefEditor.putInt("userChoiceSpinner", userChoice);
        prefEditor.commit();

        //  Toast.makeText(requireContext(),country[position] , Toast.LENGTH_LONG).show();

        // Showing selected spinner item
//        Toast.makeText(parent.getContext(), "You selected: " + label,
//                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void leaveRequest() {

        progressDialog = new ProgressDialog(requireContext());
        progressDialog.setMessage("Submitting...");
        progressDialog.setCancelable(false);
        progressDialog.show();
//        UserService userService = getRetrofit().create(UserService.class);
        final LeaveRequest leaveRequest = new LeaveRequest(EmpName.getText().toString(), label,
                Status, EtStartDate.getText().toString(), EtEndDate.getText().toString(),
                EtReason.getText().toString(), EtStartTime.getText().toString(), EtEndTime.getText().toString(),
                CompanyId.getText().toString(), EmployeeID);

        //    Toast.makeText(requireContext(), "" + EmployeeID, Toast.LENGTH_SHORT).show();

        Call<LeaveRequest> call = MyApiClient.getUserService().PostData(leaveRequest);


        call.enqueue(new Callback<LeaveRequest>() {
            @Override
            public void onResponse(Call<LeaveRequest> call, Response<LeaveRequest> response) {
                if (response.isSuccessful()) {
                    LeaveRequest leaveResponse = response.body();
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), "Status is :" + leaveResponse.getStatus(), Toast.LENGTH_LONG).show();
                    try {
                        Navigation.findNavController(getView()).navigate(R.id.nav_leavesummary);
                    } catch (Exception e) {
                        Toast.makeText(requireContext(), "Sorry Something Wrong", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    progressDialog.dismiss();
                    Toast.makeText(requireContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LeaveRequest> call, Throwable t) {
                progressDialog.dismiss();
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                    //  Toast.makeText(requireContext(), ""+t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
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


//    private void setDatabase(){
//        roomDB = Room.databaseBuilder(requireContext(), MyRoomDB.class,"Leavetype.db")
//                .allowMainThreadQueries().build();
//    }

    private void DelegatePerson() {

        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
//
//        ProgressDialog progressDialog = new ProgressDialog(requireContext());
//        progressDialog.setMessage("Loading...");
//        progressDialog.setCancelable(false);
//        progressDialog.show();

        Call<List<EmployeeResponse>> call = MyApiClient.getUserService().GetAllEmployee(companyid);


        call.enqueue(new Callback<List<EmployeeResponse>>() {
            @Override
            public void onResponse(Call<List<EmployeeResponse>> call, Response<List<EmployeeResponse>> response) {

                if (response.isSuccessful()) {

                    List<EmployeeResponse> nlist = response.body();
                    empname.addAll(response.body());
                    addSpinnerData(nlist);
                    binding.simpleProgressBar.setVisibility(View.GONE);
                    binding.delegateperson.setVisibility(View.VISIBLE);
//                    progressDialog.dismiss();
                    //            Toast.makeText(requireContext(), "Status is :"+nlist.get(0).getFirstName(), Toast.LENGTH_LONG).show();


                } else {
                    Toast.makeText(requireContext(), "Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<List<EmployeeResponse>> call, Throwable t) {
                //     Toast.makeText(requireContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void addSpinnerData(final List<EmployeeResponse> nlist) {
        binding.Tvdelegateperson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Initialize dialog
                dialog = new Dialog(getContext());

                // set custom dialog
                dialog.setContentView(R.layout.dialog_searchable_spinner);

                // set transparent background
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));

                // show dialog
                dialog.show();

                // Initialize and assign variable
                EditText editText = dialog.findViewById(R.id.edit_text);
                ListView listView = dialog.findViewById(R.id.list_view);

                // Initialize array adapter
                List<String> CustomerResponseList = new ArrayList<>();
                CustomerResponseList.add(0, "Please Select");
                for (int i = 1; i < nlist.size(); i++) {
                    CustomerResponseList.add(i, nlist.get(i).getEmpIdAutomatic() + "\n (" + nlist.get(i).getFirstName() + ")\n");
                }
                ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(), android.R.layout.simple_list_item_1, CustomerResponseList);
                listView.setAdapter(adapter);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        adapter.getFilter().filter(s);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        adapter.getFilter().filter(s);
                    }


                });

                listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        binding.Tvdelegateperson.setText(adapter.getItem(position));

                        try {

                            String textViewText = binding.Tvdelegateperson.getText().toString();
                            int startIndex = textViewText.indexOf("BD");
                            int endIndex = textViewText.indexOf("(");

                            EmployeeID = textViewText.substring(startIndex, endIndex).trim();

                        } catch (Exception e) {
                            // Utilities.showLogcatMessage("binding.tvCustomerList " + e);

                        }
                        dialog.dismiss();
                    }
                });
            }
        });
    }


    private void showDataFromDb() {

        List<LeaveInfo> leaveInfoList = roomDB.leaveDAO().getAllLeave();
        for (int i = 0; i < leaveInfoList.size(); i++) {
//            Log.d("StuentInfo", studentInfoList.get(i).getId()+"\t"
//                    +studentInfoList.get(i).getLeavename()+"\t"
//                    +studentInfoList.get(i).getSubject()+"\t"
//                    +studentInfoList.get(i).getDepartment());

            Toast.makeText(requireContext(), "Data is:" + leaveInfoList.get(i).getId() + "\t"
                    + leaveInfoList.get(i).getName() + "\t", Toast.LENGTH_SHORT).show();
        }
    }


}
