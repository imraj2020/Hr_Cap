package com.cse.hrcap.ui.LeaveApprovalSummary;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
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

import com.cse.hrcap.MyAdapters.LeaveAprSumAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumInfo;
import com.cse.hrcap.RoomLeaveAprSummary.LeaveAprSumRoomDB;
import com.cse.hrcap.databinding.LeaveApprovalSummaryFragmentBinding;
import com.cse.hrcap.network.MyApiClient;
import com.cse.hrcap.network.LeaveAprSummary;

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
    private int visibleCardCount = 0;

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
        Call<List<LeaveAprSummary>> call = MyApiClient.getUserService().leaveaprsummary(companyid, userid);



        call.enqueue(new Callback<List<LeaveAprSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveAprSummary>> call, Response<List<LeaveAprSummary>> response) {

                LeaveAprSumRoomDB db = LeaveAprSumRoomDB.getDbInstance(requireContext());

                if (response.isSuccessful()) {

                    List<LeaveAprSummary> nlist = response.body();

                    if (nlist != null && !nlist.isEmpty()){


                            binding.TvNoData.setVisibility(View.GONE);


                        for (LeaveAprSummary post : nlist) {

                            LeaveAprSumInfo existingData = db.leaveAprSumDAO().getLeaveAprSumById(post.getLeaveId());

                          if(existingData != null){

                              // Data already exists, update it
                              existingData.setLeaveid(post.getLeaveId());
                              existingData.setCompanyid(post.getCompanyId());
                              existingData.setEmpid(post.getEmpId());
                              existingData.setEmpcode(post.getEmpCode());
                              existingData.setLeavetypeid(post.getLeaveTypeId());
                              existingData.setLeavetypename(post.getLeaveTypeName());
                              existingData.setFromdate(post.getFromDate());
                              existingData.setTodate(post.getToDate());
                              existingData.setTotalday(post.getTotalDay());
                              existingData.setFromtime(post.getFromTime());
                              existingData.setTotime(post.getToTime());
                              existingData.setTotalhours(post.getTotalHours());
                              existingData.setLeavestatusId(post.getLeavestatusid());
                              existingData.setLeavestatusname(post.getLeaveStatusName());
                              existingData.setFullname(post.getFullName());
                              existingData.setIndivrequeststatus(post.getIndivRequestStatus());
                              existingData.setIndivrequeststatusname(post.getIndivRequestStatusName());
                              existingData.setEntryby(post.getEntryBy());
                              existingData.setEntrydate(post.getEntryDate());

                              db.leaveAprSumDAO().updateLeaveAprSum(existingData);

                            //  Toast.makeText(requireContext(), "Data Updating", Toast.LENGTH_SHORT).show();


                          }else{

                              LeaveAprSumInfo leaveAprSumInfo = new LeaveAprSumInfo(post.getLeaveId(),post.getCompanyId(),post.getEmpId(),
                                      post.getEmpCode(),post.getLeaveTypeId(),post.getLeaveTypeName(),post.getFromDate(),post.getToDate(),
                                      post.getTotalDay(),post.getFromTime(),post.getToTime(),post.getTotalHours(),post.getLeavestatusid(),
                                      post.getLeaveStatusName(),post.getFullName(),post.getIndivRequestStatus(),post.getIndivRequestStatusName(),
                                      post.getEntryBy(),post.getEntryDate());
                              db.leaveAprSumDAO().insertLeaveAprSummary(leaveAprSumInfo);

                            //  Toast.makeText(requireContext(), "Data Inserting", Toast.LENGTH_SHORT).show();
                          }

                        }

                    }

                    else if(nlist.isEmpty()) {

                        db.leaveAprSumDAO().deleteAll();
                        Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(requireContext(), "Sorry Something went Wrong", Toast.LENGTH_SHORT).show();
                    }



                   loaddatainlistview();
                } else {

                    Toast.makeText(requireContext(), "Sorry Something went Wrong", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveAprSummary>> call, Throwable t) {
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong ", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(requireContext(), "No internet connection available", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (connectivityManager != null) {
            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }
        return false;
    }



    private void loaddatainlistview() {

        LeaveAprSumRoomDB db = LeaveAprSumRoomDB.getDbInstance(requireContext());
        arrayList = db.leaveAprSumDAO().getAllleaveAprSummary();
        int size = arrayList.size();
        if(size==0){
            binding.TvNoData.setVisibility(View.VISIBLE);
        }

        binding.totalresult2.setText(Integer.toString(size));
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