package com.cse.hrcap.ui.LeaveSummary;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.MyAdapters.HolidayAdapter;
import com.cse.hrcap.MyAdapters.LeaveSummaryAdapter;
import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomLeave.LeaveInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryInfo;
import com.cse.hrcap.RoomLeaveSummary.LeaveSummaryRoomDB;
import com.cse.hrcap.databinding.LeaveSummaryFragmentBinding;
import com.cse.hrcap.databinding.LoanAdvSalaryFragmentBinding;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveSummary;
import com.cse.hrcap.network.LeaveTypeResponse;

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
        Call<List<LeaveSummary>> call = LeaveApiClient.getUserService().leavesummary(companyid, userid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveSummary>>() {
            @Override
            public void onResponse(Call<List<LeaveSummary>> call, Response<List<LeaveSummary>> response) {

                if (response.isSuccessful()) {

                    List<LeaveSummary> nlist = response.body();


                    for (LeaveSummary post : nlist) {
//                        String content = "";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Employee ID: " + post.getEmployeeId() + "\n";
//                        content += "Leave Type Id :" + post.getLeaveTypeId() + "\n";
//                        content += "Leave Type Name:" + post.getLeaveTypeName() + "\n";
//                        content += "Taken Leave: " + post.getTakenLeave() + "\n";
//                        content += "Total Leave :" + post.getTotalLeave() + "\n";
//                        content += "Available Leave: " + post.getAvailableLeave() + "\n\n";

                        LeaveSummaryInfo leaveSummaryInfo = new LeaveSummaryInfo(post.getLeaveId(), post.getLeaveTypeName(),
                                post.getFromDate(), post.getToDate(), post.getTotalDay(), post.getTotalHours(),
                                post.getEntryBy(),post.getEntryDateTime(),post.getLeaveStatusId(),post.getLeaveStatusName());
                        leaveSummaryRoomDB.leaveSummaryDAO().insertLeaveSummary(leaveSummaryInfo);


                        //  LeaveBalance.append(content);
                    }

                    loaddatainlistview();
                } else {
                    Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveSummary>> call, Throwable t) {
//                LeaveBalance.setText(t.getMessage());
                Toast.makeText(requireContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
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




}