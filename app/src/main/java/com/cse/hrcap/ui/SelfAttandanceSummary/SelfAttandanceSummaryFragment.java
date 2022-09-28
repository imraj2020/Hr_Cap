package com.cse.hrcap.ui.SelfAttandanceSummary;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.hrcap.R;

public class SelfAttandanceSummaryFragment extends Fragment {

    private SelfAttandanceSummaryViewModel mViewModel;

    public static SelfAttandanceSummaryFragment newInstance() {
        return new SelfAttandanceSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.self_attandance_summary_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(SelfAttandanceSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}