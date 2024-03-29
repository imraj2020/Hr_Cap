package com.cse.hrcap.ui.LeaveSummary;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.LeaveSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryRoomDB;
import com.cse.hrcap.databinding.LeaveSummaryFragmentBinding;
import com.cse.hrcap.network.MyApiClient;
import com.cse.hrcap.network.LeaveSummary;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class LeaveSummaryFragment extends Fragment {

    private LeaveSummaryViewModel mViewModel;
    LeaveSummaryFragmentBinding binding;
    TextView LeaveSummary;
    RecyclerView LeaveSummaryLv;
    List<LeaveSummaryInfo> arrayList;
    public static LeaveSummaryRoomDB leaveSummaryRoomDB;
    SwipeRefreshLayout lsSwipeRefreshLayout;
    // Create a BackPressedHandler reference


    public static LeaveSummaryFragment newInstance() {
        return new LeaveSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveSummaryFragmentBinding.inflate(inflater);
        LeaveSummaryLv = binding.leavesummarylv;
        arrayList = new ArrayList<>();




        // SwipeRefreshLayout
        lsSwipeRefreshLayout = binding.lvsummaryswipe;
        lsSwipeRefreshLayout.setColorSchemeResources(R.color.purple_500,
                android.R.color.holo_green_dark,
                android.R.color.holo_orange_dark,
                android.R.color.holo_blue_dark);


        lsSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {

                lsSwipeRefreshLayout.post(new Runnable() {
                                              @Override
                                              public void run() {
                                                  setLeaveSummaryDatabase();
                                                  leavesummary();
                                                  Toast.makeText(requireContext(), "Swipe Complete", Toast.LENGTH_LONG).show();
                                                  lsSwipeRefreshLayout.setEnabled(false);
                                              }
                                          }
                );

            }

        });


        setLeaveSummaryDatabase();
        leavesummary();
        loaddatainlistview();
        return binding.getRoot();
    }



    private void loaddatainlistview() {


        arrayList = leaveSummaryRoomDB.leaveSummaryDAO().getAllSummary();
        LeaveSummaryAdapter adapter = new LeaveSummaryAdapter(arrayList, requireContext());
        LeaveSummaryLv.setLayoutManager(new LinearLayoutManager(requireContext()));
        LeaveSummaryLv.setAdapter(adapter);
        int size = arrayList.size();
        if (size == 0) {
            binding.TvNoData.setVisibility(View.VISIBLE);
        }
        binding.totalresult1.setText(Integer.toString(size));
        //Don't know about notifidatasetchenge work or not
        adapter.notifyDataSetChanged();
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    //leave summary
    private void leavesummary() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String userid = intent.getStringExtra("Employee");
        Call<List<LeaveSummary>> call = MyApiClient.getUserService().leavesummary(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveSummary>> call, Response<List<LeaveSummary>> response) {

                if (response.isSuccessful()) {

                    List<LeaveSummary> nlist = response.body();

                    if (nlist != null && !nlist.isEmpty()) {


                        binding.TvNoData.setVisibility(View.GONE);


                        for (LeaveSummary post : nlist) {


                            LeaveSummaryInfo leaveSummaryInfo = new LeaveSummaryInfo(post.getLeaveId(), post.getLeaveTypeName(),
                                    post.getFromDate(), post.getToDate(), post.getTotalDay(), post.getTotalHours(),
                                    post.getEntryBy(), post.getEntryDateTime(), post.getLeaveStatusId(), post.getLeaveStatusName());
                            leaveSummaryRoomDB.leaveSummaryDAO().insertLeaveSummary(leaveSummaryInfo);

                        }
                    } else if (nlist.isEmpty()) {
                        leaveSummaryRoomDB.leaveSummaryDAO().deleteAll();
                        Toast.makeText(requireContext(), "No data found", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(requireContext(), "Sorry Something went Wrong", Toast.LENGTH_SHORT).show();
                    }

                    loaddatainlistview();
                } else {
                    Toast.makeText(requireContext(), "No Data Found", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveSummary>> call, Throwable t) {
                if (isNetworkAvailable()) {
                    Toast.makeText(requireContext(), "Sorry Something went Wrong", Toast.LENGTH_SHORT).show();
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

    public void setLeaveSummaryDatabase() {
        leaveSummaryRoomDB = Room.databaseBuilder(requireContext(), LeaveSummaryRoomDB.class, "LeaveSummary.db")
                .allowMainThreadQueries().build();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveSummaryViewModel.class);
        // TODO: Use the ViewModel
    }


    public void onBackPressed() {
    }
}