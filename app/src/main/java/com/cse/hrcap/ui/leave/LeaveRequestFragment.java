package com.cse.hrcap.ui.leave;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.cse.hrcap.databinding.LeaveRequestFragmentBinding;
import com.cse.hrcap.network.BasicAuthInterceptor;
import com.cse.hrcap.network.LeaveApiClient;
import com.cse.hrcap.network.LeaveTypeResponse;
import com.cse.hrcap.network.UserService;
import com.cse.hrcap.ui.home.MyDbHelper;

import java.util.ArrayList;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LeaveRequestFragment extends Fragment implements AdapterView.OnItemSelectedListener{
    private LeaveRequestViewModel mViewModel;
    private LeaveRequestFragmentBinding binding;
    private UserService userService;
    TextView Leavetyperesponse;
    Spinner spinner;

    public static LeaveRequestFragment newInstance() {
        return new LeaveRequestFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        binding = LeaveRequestFragmentBinding.inflate(inflater);

        Leavetyperesponse = binding.textViewResults;
        spinner = binding.spinner;
        spinner.setOnItemSelectedListener(this);
        leaveTypes();
        loadSpinnerData();
        // return inflater.inflate(R.layout.leave_request_fragment, container, false);
        return binding.getRoot();
    }


    private void leaveTypes() {
        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        Call<List<LeaveTypeResponse>> call = LeaveApiClient.getUserService().leavetype(companyid);
        // Call<LoginResponse> loginResponseCall = LoginApiClient.getUserService().userLogin(userid,password);


        call.enqueue(new Callback<List<LeaveTypeResponse>>() {
            @Override
            public void onResponse(Call<List<LeaveTypeResponse>> call, Response<List<LeaveTypeResponse>> response) {

                if (response.isSuccessful()) {

                    List<LeaveTypeResponse> nlist = response.body();

                    Toast.makeText(getContext(), "List is"+nlist, Toast.LENGTH_SHORT).show();

                    final String  leavetype1, leavetype2, leavetype3, leavetype4,
                            leavetype5, leavetype6, leavetype7, leavetype8, leavetype9, leavetype10;



                        leavetype1 = nlist.get(0).getLeaveTypeName();
                        leavetype2 = nlist.get(1).getLeaveTypeName();
                        leavetype3 = nlist.get(2).getLeaveTypeName();
                        leavetype4 = nlist.get(3).getLeaveTypeName();
                        leavetype5 = nlist.get(4).getLeaveTypeName();
                        leavetype6 = nlist.get(5).getLeaveTypeName();
                        leavetype7 = nlist.get(6).getLeaveTypeName();
                        leavetype8 = nlist.get(7).getLeaveTypeName();
                        leavetype9 = nlist.get(8).getLeaveTypeName();
                        leavetype10 = nlist.get(9).getLeaveTypeName();


                    final List<String> list = new ArrayList<String>();
                    list.add(leavetype1);
                    list.add(leavetype2);
                    list.add(leavetype3);
                    list.add(leavetype4);
                    list.add(leavetype5);
                    list.add(leavetype6);
                    list.add(leavetype7);
                    list.add(leavetype8);
                    list.add(leavetype9);
                    list.add(leavetype10);

                    Toast.makeText(getContext(), "Showing array list"+list, Toast.LENGTH_SHORT).show();
                        LeaveTypeDbHelper LeaveTypeDbHelper = new LeaveTypeDbHelper(requireContext());
                        LeaveTypeDbHelper.insertRecord(leavetype1, leavetype2, leavetype3, leavetype4,
                                leavetype5, leavetype6, leavetype7, leavetype8, leavetype9, leavetype10);





                    // nlist.get(0);

//                    Log.d("myTag", "This is my message"+nlist);
//                    Toast.makeText(requireContext(), "this is list:"+nlist.get(9).getLeaveTypeName(), Toast.LENGTH_LONG).show();

                    for (LeaveTypeResponse post : nlist) {
                        String content = "";
//                        content += "Leave Type ID: " + post.getLeaveTypeId() + "\n";
                        content += "Leave Type Name: " + post.getLeaveTypeName() + "\n";
//                        content += "Company ID: " + post.getCompanyId() + "\n";
//                        content += "Short Name: " + post.getShortName() + "\n";
//                        content += "Description: " + post.getDescription() + "\n\n";

                        Leavetyperesponse.append(content);
                    }
                } else {
                    Toast.makeText(getContext(), "Retrive Failed", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<List<LeaveTypeResponse>> call, Throwable t) {
                Leavetyperesponse.setText(t.getMessage());
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

    /**
     * Function to load the spinner data from SQLite database
     * */
    private void loadSpinnerData() {
        LeaveTypeDbHelper db = new LeaveTypeDbHelper(requireContext());
        List<String> labels = db.getAllLabels();

        // Creating adapter for spinner
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(requireContext(),android.R.layout.simple_spinner_item, labels);

        // Drop down layout style - list view with radio button
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        // attaching data adapter to spinner
        spinner.setAdapter(dataAdapter);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position,
                               long id) {
        // On selecting a spinner item
        String label = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "You selected: " + label,
                Toast.LENGTH_LONG).show();

    }

    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub

    }

}
