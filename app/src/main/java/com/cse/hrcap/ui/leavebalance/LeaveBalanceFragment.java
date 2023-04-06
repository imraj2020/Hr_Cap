package com.cse.hrcap.ui.leavebalance;

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
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.LeaveBalanceAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceInfo;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceRoomDB;
import com.cse.hrcap.databinding.LeaveBalanceFragmentBinding;
import com.cse.hrcap.network.MyApiClient;
import com.cse.hrcap.network.LeaveBalanceResponse;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveBalanceFragment extends Fragment {

    private LeaveBalanceViewModel mViewModel;
    private LeaveBalanceFragmentBinding binding;
    RecyclerView LeaveBalanceLv;
    List<LeaveBalanceInfo> arrayList;
    public static LeaveBalanceRoomDB leaveBalanceroomDB;
    SwipeRefreshLayout lSwipeRefreshLayout;


    public static LeaveBalanceFragment newInstance() {
        return new LeaveBalanceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveBalanceFragmentBinding.inflate(inflater);
        LeaveBalanceLv = binding.leavebalancelv;
        arrayList = new ArrayList<>();


        // SwipeRefreshLayout
        lSwipeRefreshLayout = binding.leaveswipe;
        lSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        lSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                lSwipeRefreshLayout.post(new Runnable() {
                                             @Override
                                             public void run() {
                                                 setLeaveBalanceDatabase();
                                                 leaveBalance();
                                                 Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                 lSwipeRefreshLayout.setEnabled(false);
                                             }
                                         }
                );

            }

        });

        setLeaveBalanceDatabase();
        leaveBalance();
        loaddatainlistview();
        return binding.getRoot();
    }

    private void loaddatainlistview() {


        arrayList = leaveBalanceroomDB.leaveBalanceDAO().getAllLeave();
        int size = arrayList.size();
        binding.totalresult2.setText(Integer.toString(size));
        LeaveBalanceAdapter adapter = new LeaveBalanceAdapter(arrayList, requireContext());
        LeaveBalanceLv.setLayoutManager(new LinearLayoutManager(requireContext()));
        LeaveBalanceLv.setAdapter(adapter);
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    private void leaveBalance() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String userid = intent.getStringExtra("Employee");
        Call<List<LeaveBalanceResponse>> call = MyApiClient.getUserService().leavebalance(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveBalanceResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveBalanceResponse>> call, Response<List<LeaveBalanceResponse>> response) {

                if (response.isSuccessful()) {

                    List<LeaveBalanceResponse> nlist = response.body();


                    for (LeaveBalanceResponse post : nlist) {
                        String content = "";
                        content += "Company ID: " + post.getCompanyId() + "\n";
                        content += "Employee ID: " + post.getEmployeeId() + "\n";
                        content += "Leave Type Id :" + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name:" + post.getLeaveTypeName() + "\n";
                        content += "Taken Leave: " + post.getTakenLeave() + "\n";
                        content += "Total Leave :" + post.getTotalLeave() + "\n";
                        content += "Available Leave: " + post.getAvailableLeave() + "\n\n";

                        LeaveBalanceInfo leaveBalanceInfo = new LeaveBalanceInfo(post.getCompanyId(), post.getEmployeeId(),
                                post.getLeaveTypeId(), post.getLeaveTypeName(), post.getTakenLeave(), post.getTotalLeave(),
                                post.getAvailableLeave());
                        leaveBalanceroomDB.leaveBalanceDAO().insertLeaveBalance(leaveBalanceInfo);


                        //  LeaveBalance.append(content);
                    }
                    loaddatainlistview();
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveBalanceResponse>> call, Throwable t) {
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

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveBalanceViewModel.class);
        // TODO: Use the ViewModel
    }

    private void setLeaveBalanceDatabase() {
        leaveBalanceroomDB = Room.databaseBuilder(requireContext(), LeaveBalanceRoomDB.class, "Leavebalance.db")
                .allowMainThreadQueries().build();
    }

}