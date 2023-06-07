package com.cse.hrcap.ui.home;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;

import com.cse.hrcap.LoginDbHelper;
import com.cse.hrcap.R;
import com.cse.hrcap.RegularizationApproval;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftInfo;
import com.cse.hrcap.RoomRegEntryDraft.RegDraftRoomDB;
import com.cse.hrcap.RoomUserInfo.UserInfo;
import com.cse.hrcap.RoomUserInfo.UserRoomDB;
import com.cse.hrcap.databinding.FragmentHomeBinding;
import com.cse.hrcap.ui.AtdRegApprovalSummary.AtdRegApprovalSummaryFragment;
import com.cse.hrcap.ui.AttandanceRegularizationSummary.AttandanceRegularizationSummaryFragment;
import com.cse.hrcap.ui.LeaveApprovalSummary.LeaveApprovalSummaryFragment;
import com.cse.hrcap.ui.LeaveSummary.LeaveSummaryFragment;
import com.cse.hrcap.ui.SelfAttandanceSummary.SelfAttandanceSummaryFragment;
import com.cse.hrcap.ui.attandancereg.AttandanceReglarizationFragment;
import com.cse.hrcap.ui.leave.LeaveRequestFragment;
import com.cse.hrcap.ui.selfattandance.SelfAttandanceFragment;

import java.util.List;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView receiver_msg;
    TextView employeeid, CompanyId, DesignationId, Designation, FullName, Grade, GradeId, EmpId, Department, DepartmentId, Position;
    TextView PositionId, Category, CategoryId, FirstName, MiddleName, LastName, Prefix, Suffix, PersonalEmail, MobilenNo;
    TextView ImageTitle, ImagePath, JoiningDate, CostCenterId, PayCycleId, LocationId, SupervisorId, SupervisorName, welcome;
    TextView SelfAttendance, SelfAttendanceSummary, LeaveRequest, LeaveSummary, LeaveApprovalSummary,
            AttendanceRegularizationEntry, AttendanceRegularizationSummary, AttendanceRegularizationApprovalSummary;

    CardView SelfAttendanceCard,SelfAttendanceSummaryCard,LeaveRequestCard,LeaveSummaryCard,LeaveApprovalSummaryCard,
            AttendanceRegularizationEntryCard,AttendanceRegularizationSummaryCard,AttendanceRegularizationApprovalSummaryCard;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        //my binding
        binding = FragmentHomeBinding.inflate(inflater);

        //old binding
        // binding = FragmentHomeBinding.inflate(inflater, container, false);
        //View root = binding.getRoot();


        final TextView textView = binding.textHome;
        employeeid = binding.employeeid;
        CompanyId = binding.companyid;
        DesignationId = binding.DesignationId;
        Designation = binding.Designation;
        FullName = binding.FullName;
        Position = binding.Position;
        PersonalEmail = binding.PersonalEmail;
        MobilenNo = binding.MobilenNo;
        SupervisorName = binding.SupervisorName;
        welcome = binding.textHomes;

        SelfAttendance = binding.tvSelfAttendance;
        SelfAttendanceSummary = binding.tvSelfAttendanceSummary;
        LeaveRequest = binding.tvLeaveRequest;
        LeaveSummary = binding.tvLeaveSummary;
        LeaveApprovalSummary = binding.tvLeaveApprovalSummary;
        AttendanceRegularizationEntry = binding.tvAttendanceRegularizationEntry;
        AttendanceRegularizationSummary = binding.tvAttendanceRegularizationSummary;
        AttendanceRegularizationApprovalSummary = binding.tvAttendanceRegularizationApprovalSummary;

        SelfAttendanceCard = binding.SelfAttendanceCard;
        SelfAttendanceSummaryCard = binding.SelfAttendanceSummaryCard;
        LeaveRequestCard = binding.LeaveRequestCard;
        LeaveSummaryCard = binding.LeaveSummaryCard;
        LeaveApprovalSummaryCard = binding.LeaveApprovalSummaryCard;
        AttendanceRegularizationEntryCard = binding.AttendanceRegularizationEntryCard;
        AttendanceRegularizationSummaryCard = binding.AttendanceRegularizationSummaryCard;
        AttendanceRegularizationApprovalSummaryCard = binding.AttendanceRegularizationApprovalSummaryCard;



        SelfAttendance.setSelected(true);
        SelfAttendanceSummary.setSelected(true);
        LeaveRequest.setSelected(true);
        LeaveSummary.setSelected(true);
        LeaveApprovalSummary.setSelected(true);
        AttendanceRegularizationEntry.setSelected(true);
        AttendanceRegularizationSummary.setSelected(true);
        AttendanceRegularizationApprovalSummary.setSelected(true);


        SelfAttendanceCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Navigation.findNavController(view).navigate(R.id.nav_selfattandance);

//                Fragment destinationFragment = new SelfAttandanceFragment();
//                FragmentManager fragmentManager = getFragmentManager();
//                FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
//                fragmentTransaction.replace(R.id.nav_host_fragment_content_main, destinationFragment);
//                fragmentTransaction.addToBackStack(null);
//                fragmentTransaction.commit();
            }
        });

        SelfAttendanceSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Navigation.findNavController(view).navigate(R.id.nav_selfattandancesummary);

            }
        });

        LeaveRequestCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_leave);
            }
        });


        LeaveSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_leavesummary);
            }
        });


        LeaveApprovalSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_leaveaprsummary);
            }
        });


        AttendanceRegularizationEntryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_attadancereg);
            }
        });


        AttendanceRegularizationSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_attadanceregsummary);
            }
        });


        AttendanceRegularizationApprovalSummaryCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Navigation.findNavController(view).navigate(R.id.nav_atdregaprsummary);
            }
        });


//        SelfAttendance.post(new Runnable() {
//            @Override
//            public void run() {
//                SelfAttendance.setSelected(true);
//            }
//        });


        Intent intent = getActivity().getIntent();

        if (intent.getExtras() != null) {
            String employee = intent.getStringExtra("Employee");
            int EmployeeId = intent.getIntExtra("EmployeeId", 1);
            String companyid = intent.getStringExtra("CompanyId");
            int DesignationIds = intent.getIntExtra("DesignationId", 1);
            String Designations = intent.getStringExtra("Designation");
            String fullname = intent.getStringExtra("FullName");
            String grade = intent.getStringExtra("Grade");
            int gradeids = intent.getIntExtra("GradeId", 1);
            String gradeid = intent.getStringExtra("GradeId");// gradeid should be integer
            String empid = intent.getStringExtra("EmpId");
            String department = intent.getStringExtra("Department");
            int departmentid = intent.getIntExtra("DepartmentId", 1);
            String position = intent.getStringExtra("Position");
            int positionid = intent.getIntExtra("PositionId", 1);
            String category = intent.getStringExtra("Category");
            int categoryid = intent.getIntExtra("CategoryId", 1);
            String firstname = intent.getStringExtra("FirstName");
            String middlename = intent.getStringExtra("MiddleName");
            String lastname = intent.getStringExtra("LastName");
            String prefix = intent.getStringExtra("Prefix");
            String suffix = intent.getStringExtra("Suffix");
            String personalemail = intent.getStringExtra("PersonalEmail");
            int mobileno = intent.getIntExtra("MobilenNo", 1);//should be integer
            String mobilenos = intent.getStringExtra("MobilenNo");
            String imagetitle = intent.getStringExtra("ImageTitle");
            String imagepath = intent.getStringExtra("ImagePath"); // returning some image link
            String joinningdate = intent.getStringExtra("JoiningDate");
            int costcenterid = intent.getIntExtra("CostCenterId", 1);
            int paycycleid = intent.getIntExtra("PayCycleId", 1);
            int locationid = intent.getIntExtra("LocationId", 1);
            int supervisorid = intent.getIntExtra("SupervisorId", 1);
            String supervisorname = intent.getStringExtra("SupervisorName");

            //Saving data in room database

            if (companyid != null) {
                UserRoomDB db = UserRoomDB.getDbInstance(requireContext());
                UserInfo userInfo = new UserInfo(employee, EmployeeId, companyid, DesignationIds, Designations, fullname,
                        grade, gradeids, empid, department, departmentid, position, positionid, category,
                        categoryid, firstname, middlename, lastname, prefix, suffix, personalemail, mobilenos,
                        imagetitle, imagepath, joinningdate, costcenterid, paycycleid, locationid, supervisorid,
                        supervisorname);
                db.userDAO().insertUser(userInfo);
            }

            //test
            Intent intents = getActivity().getIntent();
            String mypositions = intents.getStringExtra("Employee");
            // Toast.makeText(getApplicationContext(), "Data Position is "+mypositions, Toast.LENGTH_SHORT).show();

            UserRoomDB database = UserRoomDB.getDbInstance(requireContext());


            List<UserInfo> list = database.userDAO().getAllDatafromRow(mypositions);
            String user = list.get(0).getEmployee();
            int employeids = list.get(0).getEmployeeid();
            String company_id = list.get(0).getCompanyid();
            int designationid = list.get(0).getDesignationid();
            String designation = list.get(0).getDesignation();
            String fullnames = list.get(0).getFullname();
            String positions = list.get(0).getPosition();
            String email = list.get(0).getPersonalemail();
            String mobilen = list.get(0).getMobilenno();
            String supervisor = list.get(0).getSupervisorname();


            welcome.setText("Welcome " + fullnames);
            employeeid.setText("EmployeeId : " + employeids);
            CompanyId.setText("Company Id : " + company_id);
            DesignationId.setText("DesignationId : " + designationid);
            Designation.setText("Designation : " + designation);
            FullName.setText("Full Name: " + fullnames);
            Position.setText("Position : " + positions);
            PersonalEmail.setText("Personal Email : " + email);
            MobilenNo.setText("Mobile No : " + mobilen);
            SupervisorName.setText("Supervisor Name : " + supervisor);


        }

//        if (intent.getExtras() == null){
//            Toast.makeText(requireContext(), "intent is null", Toast.LENGTH_SHORT).show();
//        }


        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });
        return binding.getRoot();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}
