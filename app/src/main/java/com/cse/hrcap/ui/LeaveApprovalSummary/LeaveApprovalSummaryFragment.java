package com.cse.hrcap.ui.LeaveApprovalSummary;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.AtdRegAprSummaryAdapter;
import com.cse.hrcap.MyAdapters.LeaveAprSumAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumRoomDB;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumRoomDB;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.databinding.LeaveApprovalSummaryFragmentBinding;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveAprSummary;
import com.cse.hrcap.network.LeaveSummary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveApprovalSummaryFragment extends Fragment {

    private LeaveApprovalSummaryViewModel mViewModel;
    LeaveApprovalSummaryFragmentBinding binding;
    List<LeaveAprSumInfo> arrayList;
    RecyclerView recyclerView;
    SwipeRefreshLayout lvaprSwipe;


    public static LeaveApprovalSummaryFragment newInstance() {
        return new LeaveApprovalSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        binding = LeaveApprovalSummaryFragmentBinding.inflate(inflater);
        arrayList = new ArrayList<>();
        recyclerView = binding.leaveaprsummaryrecycle;



        // SwipeRefreshLayout
        lvaprSwipe = binding.leaveaprswipe;
        lvaprSwipe.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        lvaprSwipe.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                lvaprSwipe.post(new Runnable() {
                                              @Override
                                              public void run() {
                                                  leaveaprsummary();
                                                  Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                  lvaprSwipe.setEnabled(false);
                                              }
                                          }
                );

            }

        });




        leaveaprsummary();
        loaddatainlistview();


        return binding.getRoot();
    }


    private void leaveaprsummary() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String userid = intent.getStringExtra("Employee");
        Call<List<LeaveAprSummary>> call = LeaveApiClient.getUserService().leaveaprsummary(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveAprSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveAprSummary>> call, Response<List<LeaveAprSummary>> response) {

                if (response.isSuccessful()) {

                    List<LeaveAprSummary> nlist = response.body();


                    for (LeaveAprSummary post : nlist) {



                        LeaveAprSumRoomDB db = LeaveAprSumRoomDB.getDbInstance(requireContext());

                        LeaveAprSumInfo leaveAprSumInfo = new LeaveAprSumInfo(post.getLeaveId(),post.getCompanyId(),post.getEmpId(),
                                post.getEmpCode(),post.getLeaveTypeId(),post.getLeaveTypeName(),post.getFromDate(),post.getToDate(),
                                post.getTotalDay(),post.getFromTime(),post.getToTime(),post.getTotalHours(),post.getLeavestatusid(),
                                post.getLeaveStatusName(),post.getFullName(),post.getIndivRequestStatus(),post.getIndivRequestStatusName(),
                                post.getEntryBy(),post.getEntryDate());
                        db.leaveAprSumDAO().insertLeaveAprSummary(leaveAprSumInfo);


                        //  LeaveBalance.append(content);
                    }

                   loaddatainlistview();
                } else {
                    Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveAprSummary>> call, Throwable t) {
//                LeaveBalance.setText(t.getMessage());
                Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }



    private void loaddatainlistview() {

        LeaveAprSumRoomDB db = LeaveAprSumRoomDB.getDbInstance(requireContext());
        arrayList = db.leaveAprSumDAO().getAllleaveAprSummary();
        LeaveAprSumAdapter adapter = new LeaveAprSumAdapter(arrayList, requireContext());
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveApprovalSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

}