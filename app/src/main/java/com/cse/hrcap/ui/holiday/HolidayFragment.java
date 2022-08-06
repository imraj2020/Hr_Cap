package com.cse.hrcap.ui.holiday;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.R;
import com.cse.hrcap.databinding.HolidayFragmentBinding;
import com.cse.hrcap.network.HolidayResponse;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveTypeResponse;
import com.cse.hrcap.ui.leave.LeaveTypeDbHelper;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayFragment extends Fragment {

    private HolidayViewModel mViewModel;
    private HolidayFragmentBinding binding;
    TextView Holidayres;


    public static HolidayFragment newInstance() {
        return new HolidayFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = HolidayFragmentBinding.inflate(inflater);
        Holidayres = binding.tvholiday;


        holidayTypes();
        return binding.getRoot();
    }

    private void holidayTypes() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<HolidayResponse>> call = LeaveApiClient.getUserService().holiday(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<HolidayResponse>>() {
            @Override
            public void onResponse(Call<List<HolidayResponse>> call, Response<List<HolidayResponse>> response) {

                if (response.isSuccessful()) {

                    List<HolidayResponse> nlist = response.body();


                    for (HolidayResponse post : nlist) {
                        String content = "";
                        content += "Holiday ID: " + post.getHolidayId() + "\n";
                        content += "Company ID: " + post.getCompanyId()+ "\n";
                        content += "Holiday name: " + post.getHolidayName()+ "\n";
                        content += "Short Name: " + post.getShortName()+ "\n";
                        content += "ReligionSpecific : " + post.getReligionSpecific() + "\n";
                        content += "Religion ID: " + post.getReligionId()+ "\n";
                        content += "Religion Name: " + post.getReligionName()+ "\n";
                        content += "Type ID: " + post.getTypeId()+ "\n";
                        content += "Type Name: " + post.getTypeName()+ "\n";
                        content += "Description: " + post.getDescription() + "\n";
                        content += "Active: " + post.getActive()+ "\n";
                        content += "EveryYearSameMonthDay : " + post.getEveryYearSameMonthDay()+ "\n\n";

                        Holidayres.append(content);
                    }
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<HolidayResponse>> call, Throwable t) {
                Holidayres.setText(t.getMessage());
                Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(HolidayViewModel.class);
        // TODO: Use the ViewModel
    }

}