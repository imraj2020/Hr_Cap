package com.cse.hrcap.ui.AttandanceRegularizationSummary;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.hrcap.R;

public class AttandanceRegularizationSummaryFragment extends Fragment {

    private AttandanceRegularizationSummaryViewModel mViewModel;

    public static AttandanceRegularizationSummaryFragment newInstance() {
        return new AttandanceRegularizationSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.attandance_regularization_summary_fragment, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttandanceRegularizationSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}