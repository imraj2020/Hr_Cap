package com.cse.hrcap.SyncData;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.databinding.LeaveBalanceFragmentBinding;
import com.cse.hrcap.databinding.SyncFragmentBinding;
import com.cse.hrcap.ui.holiday.HolidayFragment;

public class SyncFragment extends Fragment {

    private SyncViewModel mViewModel;
    SyncFragmentBinding binding;
    Button BtnSync;
    HolidayFragment holidayFragment;

    public static SyncFragment newInstance() {
        return new SyncFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = SyncFragmentBinding.inflate(inflater);

        //
        BtnSync = binding.btnsync;

        BtnSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                holidayFragment.holidayTypes();
//                holidayFragment.setDatabase();
                Toast.makeText(requireContext(), "Data Sync Complete", Toast.LENGTH_SHORT).show();
            }
        });




        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SyncViewModel.class);
        // TODO: Use the ViewModel
    }

}