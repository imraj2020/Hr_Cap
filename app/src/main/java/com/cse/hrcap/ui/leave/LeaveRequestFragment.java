package com.cse.hrcap.ui.leave;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.databinding.LeaveRequestFragmentBinding;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveTypeResponse;
import com.cse.hrcap.network.LoginApiClient;
import com.cse.hrcap.network.LoginResponse;
import com.cse.hrcap.network.UserService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LeaveRequestFragment extends Fragment {

    private LeaveRequestViewModel mViewModel;
    private LeaveRequestFragmentBinding binding;
    private UserService userService;
    TextView LeaveType;

    public static LeaveRequestFragment newInstance() {
        return new LeaveRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveRequestFragmentBinding.inflate(inflater);

        LeaveType = binding.textViewResult;

        leavetypes();
       // return inflater.inflate(R.layout.leave_request_fragment, container, false);
        return binding.getRoot();
    }

//    private void leavetype() {
//        String companyid = "BAN31016";
//        Call<LeaveTypeResponse> loginResponseCall = LeaveApiClient.getUserService().leavetype(companyid);
//        //ekhan theke code korte hobe
//
//    }

    private void leavetypes() {
        String companyid = "BAN31016";
        Call<List<LeaveTypeResponse>>call = LeaveApiClient.getUserService().leavetype(companyid);
       // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);
        call.enqueue(new Callback<List<LeaveTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveTypeResponse>> call, Response<List<LeaveTypeResponse>> response) {

                if (!response.isSuccessful()) {
                    LeaveType.setText("Code: " + response.code());
                    Toast.makeText(getContext(), "Retrive Success", Toast.LENGTH_SHORT).show();
                    return;
                }
                else {

                    List<LeaveTypeResponse> posts = response.body();

                    for (LeaveTypeResponse post : posts) {
                        String content = "";
                        content += "LeaveTypeId " + post.getLeaveTypeId() + "\n";
                        content += "LeaveTypeName " + post.getLeaveTypeName() + "\n";
                        content += "CompanyId " + post.getCompanyId() + "\n";
                        content += "ShortName " + post.getShortName() + "\n";
                        content += "Description " + post.getDescription() + "\n";

                        LeaveType.append(content);
                    }
                }
            }

            @Override
            public void onFailure(Call<List<LeaveTypeResponse>> call, Throwable t) {
                LeaveType.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(LeaveRequestViewModel.class);
        // TODO: Use the ViewModel
    }

}