package com.cse.hrcap.ui.leave;

import androidx.lifecycle.ViewModelProvider;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.util.Log;
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
import android.widget.Toast;

import com.cse.hrcap.RoomLeave.LeaveInfo;
import com.cse.hrcap.RoomLeave.MyRoomDB;
import com.cse.hrcap.databinding.LeaveRequestFragmentBinding;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveRequest;
import com.cse.hrcap.network.LeaveTypeResponse;
import com.cse.hrcap.network.UserService;

import java.util.Calendar;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveRequestFragment extends Fragment implements AdapterView.OnItemSelectedListener {
    private LeaveRequestViewModel mViewModel;
    private LeaveRequestFragmentBinding binding;
    private UserService userService;
    TextView Leavetyperesponse;
    Spinner spinner,spinnertwo;
    public static  String label;
    TextView EmpName, CompanyId;
    EditText EtDay,EtStartDate,EtEndDate,EtStartTime,EtEndTime,EtReason;
    Button BtnSubmit,Btncheck;
    DatePickerDialog datePickerDialog;
  //  LeaveTypeDbHelper dbs;
    public MyRoomDB roomDB;

    public static LeaveRequestFragment newInstance() {
        return new LeaveRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveRequestFragmentBinding.inflate(inflater);

        //Leavetyperesponse = binding.textViewResults;
        EtDay = binding.etday;
        EmpName = binding.tvempname;
        CompanyId = binding.tvcompanyid;
        EtStartDate = binding.etstartdate;
        EtEndDate = binding.etenddate;
        EtStartTime = binding.etstarttime;
        EtEndTime = binding.etendtime;
        EtReason = binding.etreason;
        BtnSubmit = binding.btnsubmit;
        spinner = binding.spinner;
        //spinnertwo = binding.spinnerday;
        spinner.setOnItemSelectedListener(this);
       // spinnertwo.setOnItemSelectedListener(this);


//        String[] daytype = { "Full Day", "Time"};
//        //Creating the ArrayAdapter instance having the country list
//        ArrayAdapter aa = new ArrayAdapter(requireContext(),android.R.layout.simple_spinner_item,daytype);
//        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
//        //Setting the ArrayAdapter data on the Spinner
//        spinnertwo.setAdapter(aa);


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

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });


        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String employeename = intent.getStringExtra("Employee");
        EmpName.setText(employeename);
        CompanyId.setText(companyid);
       // spinner.setOnItemSelectedListener(this);

        leaveTypes();
        setDatabase();
        loadSpinnerData();
        //loadSpinnerData();
        BtnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                leaveRequest();
            }
        });
        // return inflater.inflate(R.layout.leave_request_fragment, container, false);
        return binding.getRoot();
    }


    private void leaveTypes() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<LeaveTypeResponse>> call = LeaveApiClient.getUserService().leavetype(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveTypeResponse>> call, Response<List<LeaveTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LeaveTypeResponse> nlist = response.body();

                    Toast.makeText(getContext(), "Retrive Successfull", Toast.LENGTH_SHORT).show();
                    Log.d("LeaveResponse",nlist.get(0).getLeaveTypeName().toString() );
//                    StudentInfo studentInfo = new StudentInfo();
//                    studentInfo.setLeavetypename("Test");
//                    Log.d("LeaveResponse",studentInfo.getLeavetypename() );
//                     roomDB.studentDAO().insertStudent(studentInfo);

                    for (LeaveTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name: " + post.getLeaveTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";

                        LeaveInfo leaveInfo = new LeaveInfo(post.getLeaveTypeName());
                        roomDB.leaveDAO().insertLeave(leaveInfo);



                       // Leavetyperesponse.append(content);
                    }
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveTypeResponse>> call, Throwable t) {
               // Leavetyperesponse.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveRequestViewModel.class);
        // TODO: Use the ViewModel
    }

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
//        LeaveTypeDbHelper db = new LeaveTypeDbHelper(requireContext());
//        List<String> labels = db.getAllLabels();
        List<String> labels = roomDB.leaveDAO().getAllName();
        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, labels);

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

      //  Toast.makeText(requireContext(),country[position] , Toast.LENGTH_LONG).show();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

    private void leaveRequest() {
//        UserService userService = getRetrofit().create(UserService.class);
        final LeaveRequest leaveRequest = new LeaveRequest(EmpName.getText().toString(),label,
                EtDay.getText().toString(),EtStartDate.getText().toString(),EtEndDate.getText().toString(),
                EtReason.getText().toString(),EtStartTime.getText().toString(),EtEndTime.getText().toString(),
                CompanyId.getText().toString());
        Call<LeaveRequest> call = LeaveApiClient.getUserService().PostData(leaveRequest);


        call.enqueue(new Callback<LeaveRequest>() {
            @Override
            public void onResponse(Call<LeaveRequest> call, Response<LeaveRequest> response) {
                if (response.isSuccessful()){
                    LeaveRequest leaveResponse = response.body();
                    Toast.makeText(requireContext(), "Status is :"+leaveResponse.getStatus(), Toast.LENGTH_LONG).show();


                }
                else {
                    Toast.makeText(requireContext(),"Something went Wrong", Toast.LENGTH_LONG).show();
                }

            }

            @Override
            public void onFailure(Call<LeaveRequest> call, Throwable t) {
                Toast.makeText(requireContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();
            }
        });

    }

    private void setDatabase(){
        roomDB = Room.databaseBuilder(requireContext(), MyRoomDB.class,"Leavetype.db")
                .allowMainThreadQueries().build();
    }

    private void showDataFromDb() {

        List<LeaveInfo> leaveInfoList = roomDB.leaveDAO().getAllLeave();
        for (int i = 0; i< leaveInfoList.size(); i++){
//            Log.d("StuentInfo", studentInfoList.get(i).getId()+"\t"
//                    +studentInfoList.get(i).getLeavename()+"\t"
//                    +studentInfoList.get(i).getSubject()+"\t"
//                    +studentInfoList.get(i).getDepartment());

            Toast.makeText(requireContext(), "Data is:"+ leaveInfoList.get(i).getId()+"\t"
                    + leaveInfoList.get(i).getName()+"\t" , Toast.LENGTH_SHORT).show();
        }
    }




}
