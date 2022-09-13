package com.cse.hrcap.ui.leavebalance;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.room.Room;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.RoomHoliday.HolidayInfo;
import com.cse.hrcap.RoomHoliday.HolidayRoomDB;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceInfo;
import com.cse.hrcap.RoomLeaveBalance.LeaveBalanceRoomDB;
import com.cse.hrcap.databinding.LeaveBalanceFragmentBinding;
import com.cse.hrcap.network.HolidayResponse;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveBalanceResponse;
import com.cse.hrcap.ui.holiday.HolidayFragment;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveBalanceFragment extends Fragment {

    private LeaveBalanceViewModel mViewModel;
    private LeaveBalanceFragmentBinding binding;
    TextView LeaveBalance;
    LeaveBalanceRoomDB roomDB;


    public static LeaveBalanceFragment newInstance() {
        return new LeaveBalanceFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding =LeaveBalanceFragmentBinding.inflate(inflater);
        LeaveBalance = binding.leavebalance;



//        leaveBalance();
//        setLeaveBalanceDatabase();
        return binding.getRoot();
    }

//    private void leaveBalance() {
//        Intent intent = getActivity().getIntent();
//        String companyid = intent.getStringExtra("CompanyId");
//        String userid = intent.getStringExtra("Employee");
//        Call<List<LeaveBalanceResponse>> call = LeaveApiClient.getUserService().leavebalance(companyid,userid);
//        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);
//
//
//        call.enqueue(new Callback<List<LeaveBalanceResponse>>() {
//            @Override
//            public void onResponse(Call<List<LeaveBalanceResponse>> call, Response<List<LeaveBalanceResponse>> response) {
//
//                if (response.isSuccessful()) {
//
//                    List<LeaveBalanceResponse> nlist = response.body();
//
//
//                    for (LeaveBalanceResponse post : nlist) {
//                        String content = "";
//                        content += "Company ID: " + post.getCompanyId()+ "\n";
//                        content += "Employee ID: " + post.getEmployeeId()+ "\n";
//                        content += "Leave Type Id :" + post.getLeaveTypeId()+ "\n";
//                        content += "Leave Type Name:" + post.getLeaveTypeName()+ "\n";
//                        content += "Taken Leave: " + post.getTakenLeave() + "\n";
//                        content += "Total Leave :" + post.getTotalLeave()+ "\n";
//                        content += "Available Leave: " + post.getAvailableLeave()+ "\n\n";
//
//                        LeaveBalanceInfo leaveBalanceInfo = new LeaveBalanceInfo(post.getCompanyId(),post.getEmployeeId(),
//                                post.getLeaveTypeId(),post.getLeaveTypeName(),post.getTakenLeave(),post.getTotalLeave(),
//                                post.getAvailableLeave());
//                        roomDB.leaveBalanceDAO().insertLeaveBalance(leaveBalanceInfo);
//
//
//
//                      //  LeaveBalance.append(content);
//                    }
//                } else {
//                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<List<LeaveBalanceResponse>> call, Throwable t) {
//                LeaveBalance.setText(t.getMessage());
//                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
//            }
//        });
//    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveBalanceViewModel.class);
        // TODO: Use the ViewModel
    }
//    private void setLeaveBalanceDatabase(){
//        roomDB = Room.databaseBuilder(requireContext(), LeaveBalanceRoomDB.class,"Leavebalance.db")
//                .allowMainThreadQueries().build();
//    }

}