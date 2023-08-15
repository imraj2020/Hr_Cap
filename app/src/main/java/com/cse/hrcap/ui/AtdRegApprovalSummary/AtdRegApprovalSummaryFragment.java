package com.cse.hrcap.ui.AtdRegApprovalSummary;

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

import com.cse.hrcap.MyAdapters.AtdRegAprSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumInfo;
import com.cse.hrcap.RoomAtdReqAprSummary.AtdRegAprSumRoomDB;
import com.cse.hrcap.databinding.AtdRegApprovalSummaryFragmentBinding;
import com.cse.hrcap.network.AtdRegAprSummary;
import com.cse.hrcap.network.MyApiClient;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AtdRegApprovalSummaryFragment extends Fragment {

    private AtdRegApprovalSummaryViewModel mViewModel;
    AtdRegApprovalSummaryFragmentBinding binding;
    List<AtdRegAprSumInfo> arrayList;
    RecyclerView recyclerView;
    SwipeRefreshLayout imSwipeRefreshLayout;

    public static AtdRegApprovalSummaryFragment newInstance() {
        return new AtdRegApprovalSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = AtdRegApprovalSummaryFragmentBinding.inflate(inflater);
        recyclerView = binding.atdregaprsummary;

        arrayList = new ArrayList<>();


        // SwipeRefreshLayout
        imSwipeRefreshLayout = binding.imyswipe;
        imSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        imSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                imSwipeRefreshLayout.post(new Runnable() {
                                              @Override
                                              public void run() {
                                                  regapprovalsummary();
                                                  Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                  imSwipeRefreshLayout.setEnabled(false);
                                              }
                                          }
                );

            }

        });


        regapprovalsummary();
        loaddatainlistview();

        return binding.getRoot();
    }


    private void regapprovalsummary() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String userid = intent.getStringExtra("Employee");
        Call<List<AtdRegAprSummary>> call = MyApiClient.getUserService().atdregaprsummary(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<AtdRegAprSummary>>() {
            @Override
            public void onResponse(Call<List<AtdRegAprSummary>> call, Response<List<AtdRegAprSummary>> response) {

                AtdRegAprSumRoomDB db = AtdRegAprSumRoomDB.getDbInstances(requireContext());

                if (response.isSuccessful()) {

                    List<AtdRegAprSummary> nlist = response.body();

                    if (nlist != null && !nlist.isEmpty()) {
                        binding.TvNoData.setVisibility(View.GONE);


                        for (AtdRegAprSummary post : nlist) {

                            AtdRegAprSumInfo existingData = db.atdRegAprSumDAO().getAtdRegAprSummaryById(Integer.parseInt(post.getMovementId()));

                            if (existingData != null) {
                                // Data already exists, update it
                                existingData.setCompanyid(post.getCompanyId());
                                existingData.setEmpid(post.getEmpId());
                                existingData.setEmpcode(post.getEmpCode());
                                existingData.setFullname(post.getFullName());
                                existingData.setStartdate(post.getStartDate());
                                existingData.setEnddate(post.getEndDate());
                                existingData.setReason(post.getReason());
                                existingData.setNote(post.getNote());
                                existingData.setFromtime(post.getFromTime());
                                existingData.setTotime(post.getToTime());
                                existingData.setStatus(post.getStatus());
                                existingData.setEntryby(post.getEntryBy());
                                existingData.setEntrydate(post.getEntryDate());
                                db.atdRegAprSumDAO().updateAtdRegAprSummary(existingData);

                              //  Toast.makeText(requireContext(), "Data Updating", Toast.LENGTH_SHORT).show();
                            } else {
                                // Data doesn't exist, insert it
                                AtdRegAprSumInfo atdRegAprSumInfo = new AtdRegAprSumInfo(post.getMovementId(), post.getCompanyId(),
                                        post.getEmpId(), post.getEmpCode(), post.getFullName(), post.getStartDate(),
                                        post.getEndDate(), post.getReason(), post.getNote(), post.getFromTime(), post.getToTime(),
                                        post.getStatus(), post.getEntryBy(), post.getEntryDate());
                                db.atdRegAprSumDAO().insertAtdRegAprSummary(atdRegAprSumInfo);
                             //   Toast.makeText(requireContext(), "Data inserting", Toast.LENGTH_SHORT).show();
                            }
                        }
                    } else if (nlist.isEmpty()) {
                        db.atdRegAprSumDAO().deleteAll();
                        Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_SHORT).show();
                    } else {
                        //if  nlist ==null
                        Toast.makeText(requireContext(), "Sorry Something went Wrong.", Toast.LENGTH_SHORT).show();
                    }


                    loaddatainlistview();
                } else {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong.", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<AtdRegAprSummary>> call, Throwable t) {
                // LeaveBalance.setText(t.getMessage());
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

        AtdRegAprSumRoomDB db = AtdRegAprSumRoomDB.getDbInstances(requireContext());
        arrayList = db.atdRegAprSumDAO().getAllRegaprSummary();
        int size = arrayList.size();

        if (size == 0) {
            binding.TvNoData.setVisibility(View.VISIBLE);
        }
        binding.totalresult5.setText(Integer.toString(size));
        AtdRegAprSummaryAdapter adapter = new AtdRegAprSummaryAdapter(arrayList, requireContext());
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
        mViewModel = new ViewModelProvider(this).get(AtdRegApprovalSummaryViewModel.class);
        // TODO: Use the ViewModel
    }

    public void onBackPressed() {
    }
}