package com.cse.hrcap.ui.LeaveSummary;

import static com.cse.hrcap.MainActivity.holidayRoomDB;
import static com.cse.hrcap.MainActivity.leaveSummaryRoomDB;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

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


    public static LeaveSummaryFragment newInstance() {
        return new LeaveSummaryFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveSummaryFragmentBinding.inflate(inflater);
        LeaveSummaryLv = binding.leavesummarylv;
        arrayList = new ArrayList<>();



        loaddatainlistview();
        return binding.getRoot();
    }


    private void loaddatainlistview() {


        arrayList = leaveSummaryRoomDB.leaveSummaryDAO().getAllSummary();
        LeaveSummaryAdapter adapter = new LeaveSummaryAdapter(arrayList, requireContext());
        LeaveSummaryLv.setLayoutManager(new LinearLayoutManager(requireContext()));
        LeaveSummaryLv.setAdapter(adapter);
//        Toast.makeText(this, arrayList.size() + "", Toast.LENGTH_SHORT).show();
//        myAdapter = new MyAdapter(this, (ArrayList<StudentInfo>) arrayList);
//        MyListView.setAdapter(myAdapter);
//        myAdapter.notifyDataSetChanged();

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveSummaryViewModel.class);
        // TODO: Use the ViewModel
    }




}