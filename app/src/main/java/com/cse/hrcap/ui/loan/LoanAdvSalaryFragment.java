package com.cse.hrcap.ui.loan;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.hrcap.R;

public class LoanAdvSalaryFragment extends Fragment {

    private LoanAdvSalaryViewModel mViewModel;

    public static LoanAdvSalaryFragment newInstance() {
        return new LoanAdvSalaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.loan_adv_salary_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LoanAdvSalaryViewModel.class);
        // TODO: Use the ViewModel
    }

}