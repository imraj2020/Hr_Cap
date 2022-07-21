package com.cse.hrcap.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import com.cse.hrcap.R;
import com.cse.hrcap.databinding.AttandanceReglarizationFragmentBinding;
import com.cse.hrcap.databinding.FragmentHomeBinding;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    private FragmentHomeBinding binding;
    TextView receiver_msg;
    TextView employeeid,CompanyId,DesignationId,Designation,FullName,Grade,GradeId,EmpId,Department,DepartmentId,Position;
    TextView PositionId,Category,CategoryId,FirstName,MiddleName,LastName,Prefix,Suffix,PersonalEmail,MobilenNo;
    TextView ImageTitle,ImagePath,JoiningDate,CostCenterId,PayCycleId,LocationId,SupervisorId,SupervisorName,welcome;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {


        homeViewModel =
                new ViewModelProvider(this).get(HomeViewModel.class);

        //my binding
        // binding =FragmentHomeBinding.inflate(inflater);

        //old binding
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final TextView textView = binding.textHome;
        employeeid = binding.employeeid;
        CompanyId = binding.companyid;
        DesignationId =binding.DesignationId;
        Designation =binding.Designation;
        FullName = binding.FullName;
        Grade = binding.Grade;
        GradeId = binding.GradeId;
        EmpId = binding.EmpId;
        Department = binding.Department;
        DepartmentId = binding.DepartmentId;
        Position = binding.Position;
        PositionId = binding.PositionId;
        Category = binding.Category;
        CategoryId = binding.CategoryId;
        FirstName = binding.FirstName;
        MiddleName = binding.MiddleName;
        LastName = binding.LastName;
        Prefix = binding.Prefix;
        Suffix = binding.Suffix;
        PersonalEmail = binding.PersonalEmail;
        MobilenNo = binding.MobilenNo;
        ImageTitle = binding.ImageTitle;
        ImagePath = binding.ImagePath;
        JoiningDate = binding.JoiningDate;
        CostCenterId = binding.CostCenterId;
        PayCycleId = binding.PayCycleId;
        LocationId = binding.LocationId;
        SupervisorId = binding.SupervisorId;
        SupervisorName = binding.SupervisorName;
        welcome = binding.textHomes;

        Intent intent = getActivity().getIntent();

        if (intent.getExtras() != null) {
            int EmployeeId = intent.getIntExtra("EmployeeId",1);
            String companyid = intent.getStringExtra("CompanyId");
            int DesignationIds = intent.getIntExtra("DesignationId",1);
            String Designations = intent.getStringExtra("Designation");
            String fullname = intent.getStringExtra("FullName");
            String grade = intent.getStringExtra("Grade");
            //int gradeid = intent.getIntExtra("GradeId", 1);
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
            int mobileno = intent.getIntExtra("MobilenNo", 1);
            String imagetitle = intent.getStringExtra("ImageTitle");
            String imagepath = intent.getStringExtra("ImagePath"); // returning some image link
            String joinningdate = intent.getStringExtra("JoiningDate");
            int costcenterid = intent.getIntExtra("CostCenterId", 1);
            int paycycleid = intent.getIntExtra("PayCycleId", 1);
            int locationid = intent.getIntExtra("LocationId", 1);
            int supervisorid = intent.getIntExtra("SupervisorId", 1);
            String supervisorname = intent.getStringExtra("SupervisorName");


            employeeid.setText("EmployeeId :" + EmployeeId);
            CompanyId.setText("Company Id :" + companyid);
            DesignationId.setText("DesignationId :" + DesignationIds);
            Designation.setText("Designation :" +Designations);
            FullName.setText("Full Name:"+fullname );
            Grade.setText("Grade :"+grade);
            GradeId.setText("Grade Id :"+gradeid);
            EmpId.setText("EmpId :" +empid);
            Department.setText("Department : "+department);
            DepartmentId.setText("Department Id : "+departmentid);
            Position.setText("Position : "+position);
            PositionId.setText("Position Id : "+positionid);
            Category.setText("Category : "+category);
            CategoryId.setText("Category Id : "+categoryid);
            FirstName.setText("First Name : "+firstname);
            MiddleName.setText("Middle Name : "+middlename);
            LastName.setText("Last Name : "+lastname);
            Prefix.setText("Prefix : "+prefix);
            Suffix.setText("Suffix : "+suffix);
            PersonalEmail.setText("Personal Email : "+personalemail);
            MobilenNo.setText("Mobile No : "+mobileno);
            ImageTitle.setText("Image Title : "+imagetitle);
            ImagePath.setText("Image Path : "+imagepath);
            JoiningDate.setText("Joining Date : "+joinningdate);
            CostCenterId.setText("Cost Center Id : "+costcenterid);
            PayCycleId.setText("Pay Cycle Id : "+paycycleid);
            LocationId.setText("Location Id : "+locationid);
            SupervisorId.setText("Supervisor Id : "+supervisorid);
            SupervisorName.setText("Supervisor Name : "+supervisorname);
            welcome.setText("Welcome " + fullname);














        }

//        if (intent.getExtras() != null) {
//            String passedUsername = intent.getStringExtra("data");
//            username.setText("Welcome " + passedUsername);
//        }
//        //receive message binding
//        receiver_msg = binding.receivedMessage;
//
//                // create the get Intent object
//        Intent intent = getActivity().getIntent();;
//
//        // receive the value by getStringExtra() method
//        // and key must be same which is send by first activity
//        String str = intent.getStringExtra("email_key");
//
//        // display the string into textView
//        receiver_msg.setText(str);


            homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
                @Override
                public void onChanged(@Nullable String s) {
                    textView.setText(s);
                }
            });
            return root;
        }

        @Override
        public void onDestroyView () {
            super.onDestroyView();
            binding = null;
        }
    }
