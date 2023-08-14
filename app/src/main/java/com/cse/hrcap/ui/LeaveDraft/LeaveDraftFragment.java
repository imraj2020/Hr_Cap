package com.cse.hrcap.ui.LeaveDraft;

import static com.cse.hrcap.MainActivity.leaveroomDB;

import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.app.Dialog;
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
import android.widget.ListView;
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
import com.cse.hrcap.network.EmployeeResponse;
import com.cse.hrcap.network.LeaveRequest;
import com.cse.hrcap.network.MyApiClient;

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

public class LeaveDraftFragment extends Fragment implements AdapterView.OnItemSelectedListener {

    private LeaveDraftViewModel mViewModel;
    FragmentLeaveDraftBinding binding;
    EditText StartDate;
    EditText EndDate;
    EditText StartTime;
    EditText EndTime;
    EditText Reason;
    EditText DelegatePerson;
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

    public String StartTimes, EndTimes;


    //test
    private final ArrayList<EmployeeResponse> empname = new ArrayList<>();

    Dialog dialog;

    String EmployeeID = "";
    public static  int dPosition;

    public static int mypositions;

    public static LeaveDraftFragment newInstance() {
        return new LeaveDraftFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = FragmentLeaveDraftBinding.inflate(inflater);


        //test
        Bundle args = getArguments();
        mypositions = args.getInt("clicked_data_", 0);


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
        dPosition = list.get(0).getDelegatePosition();





        // DelegatePerson = binding.delegateperson1;
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
        DelegatePerson();

        BtnSubmits.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Enddates, Endtimes;
                Enddates = EndDate.getText().toString().trim();
                Endtimes = EndTime.getText().toString().trim();

                if (Enddates.equals("Select Correct Date") && Endtimes.equals("Select Correct Time") ||
                        Enddates.equals("Select Correct Date") || Endtimes.equals("Select Correct Time")) {

                    Toast.makeText(requireContext(), "Select Correct Date/Time", Toast.LENGTH_LONG).show();
                } else {
                    leaveRequest();

                }
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
                CompanyId.getText().toString(), EmployeeID);
        Call<LeaveRequest> call = MyApiClient.getUserService().PostData(leaveRequest);


        call.enqueue(new Callback<LeaveRequest>() {
            @Override
            public void onResponse(Call<LeaveRequest> call, Response<LeaveRequest> response) {
                if (response.isSuccessful()) {
                    LeaveRequest leaveResponse = response.body();
                    Toast.makeText(requireContext(), "Status is :" + leaveResponse.getStatus(), Toast.LENGTH_LONG).show();

                    LeaveDraftRoomDB db = LeaveDraftRoomDB.getDbInstance(requireContext().getApplicationContext());
                    db.leaveDraftDAO().deleteLeavedraftinfo(mypositions);

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
            Date startTime = timeFormat.parse(StartTime.getText().toString());
            Date endTime = timeFormat.parse(EndTime.getText().toString());

            if (startTime.before(endTime)) {

            } else if (startTime.after(endTime)) {
                Toast.makeText(requireContext(), "End time should not be smaller than start time.", Toast.LENGTH_SHORT).show();
                EndTime.setText("Select Correct Time");


            } else {

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
                    binding.simpleProgressBar1.setVisibility(View.GONE);
                    binding.delegateperson1.setVisibility(View.VISIBLE);

                    try {
                        if (nlist.size() >= dPosition) {
                            EmployeeResponse Employee = nlist.get(dPosition);
                            String EmployeeName = Employee.getEmployeeName();
                            EmployeeID = Employee.getEmpIdAutomatic();
                            // Use the data as needed, for example, display it in a TextView
                            binding.Tvdelegateperson.setText(EmployeeID  + " (" + EmployeeName+")");

                        }
                    }catch (Exception e){

                    }




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
                    CustomerResponseList.add(i, nlist.get(i).getEmpIdAutomatic() + "\n (" + nlist.get(i).getEmployeeName() + ")\n");
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


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveDraftViewModel.class);
        // TODO: Use the ViewModel
    }
}