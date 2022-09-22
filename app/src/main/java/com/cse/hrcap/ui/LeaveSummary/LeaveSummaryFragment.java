package com.cse.hrcap.ui.LeaveSummary;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeave.LeaveInfo;
import com.cse.hrcap.databinding.LeaveSummaryFragmentBinding;
import com.cse.hrcap.databinding.LoanAdvSalaryFragmentBinding;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveSummary;
import com.cse.hrcap.network.LeaveTypeResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveSummaryFragment extends Fragment {

    private LeaveSummaryViewModel mViewModel;
    LeaveSummaryFragmentBinding binding;
    TextView LeaveSummary;

    public static LeaveSummaryFragment newInstance() {
        return new LeaveSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveSummaryFragmentBinding.inflate(inflater);

        LeaveSummary = binding.leavesummary;

        leavesummary();



        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

    public void leavesummary(){
        Intent intent = getActivity().getIntent();
        String CompanyId = intent.getStringExtra("CompanyId");
        String Employee = intent.getStringExtra("Employee");
        Call<List<LeaveSummary>> call = LeaveApiClient.getUserService().leavesummary(CompanyId,Employee);

        call.enqueue(new Callback<List<LeaveSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveSummary>> call, Response<List<LeaveSummary>> response) {
                if (response.isSuccessful()) {

                    List<LeaveSummary> nlist = response.body();

                    //  Toast.makeText(getContext(), "Retrive Successfull", Toast.LENGTH_SHORT).show();
                    Log.d("LeaveResponse", nlist.get(0).getLeaveTypeName().toString());
//                    StudentInfo studentInfo = new StudentInfo();
//                    studentInfo.setLeavetypename("Test");
//                    Log.d("LeaveResponse",studentInfo.getLeavetypename() );
//                     roomDB.studentDAO().insertStudent(studentInfo);

                    for (LeaveSummary post : nlist) {
                        String content = "";
                        content += "LeaveId: " + post.getLeaveId() + "\n";
                        content += "Leave Type Name: " + post.getLeaveTypeName() + "\n";
                        content += "FromDate: " + post.getFromDate() + "\n";
                        content += "ToDate: " + post.getToDate() + "\n";
                        content += "TotalDay: " + post.getTotalDay() + "\n";
                        content += "TotalHours: " + post.getTotalHours() + "\n";
                        content += "EntryBy: " + post.getEntryBy() + "\n";
                        content += "EntryDateTime: " + post.getEntryDateTime() + "\n";
                        content += "LeaveStatusId: " + post.getLeaveStatusId() + "\n";
                        content += "LeaveStatusName: " + post.getLeaveStatusName() + "\n\n";
//                        LeaveInfo leaveInfo = new LeaveInfo(post.getLeaveTypeName());
//                        leaveroomDB.leaveDAO().insertLeave(leaveInfo);

                         LeaveSummary.append(content);
                    }
                    // Toast.makeText(getApplicationContext(), "Data insert successful", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveSummary>> call, Throwable t) {
                Toast.makeText(requireContext(), ""+t, Toast.LENGTH_SHORT).show();
               // Log.d("response",t);
            }
        });



    }

}