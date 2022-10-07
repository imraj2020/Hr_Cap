package com.cse.hrcap.ui.AttdanceRegApproval;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdRegSummary.AtdRegInfo;
import com.cse.hrcap.databinding.AttdanceRegApprovalFragmentBinding;

import java.util.List;

public class AttdanceRegApprovalFragment extends Fragment {

    private AttdanceRegApprovalViewModel mViewModel;
    AttdanceRegApprovalFragmentBinding binding;


    public static AttdanceRegApprovalFragment newInstance() {
        return new AttdanceRegApprovalFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding=AttdanceRegApprovalFragmentBinding.inflate(inflater);

        //code here





        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(AttdanceRegApprovalViewModel.class);
        // TODO: Use the ViewModel
    }

}