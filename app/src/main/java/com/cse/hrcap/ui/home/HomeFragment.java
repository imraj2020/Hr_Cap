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
    TextView employeeid,DesignationId;

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
        DesignationId =binding.DesignationId;

        Intent intent = getActivity().getIntent();

        if (intent.getExtras() != null) {
            int passedUsername = intent.getIntExtra("EmployeeId",1);
            int passedUsernam = intent.getIntExtra("DesignationId",1);
            employeeid.setText("Welcome " + passedUsername);
            DesignationId.setText("Welcome " + passedUsernam);
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
