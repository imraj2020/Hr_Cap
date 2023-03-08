package com.cse.hrcap.ui.chengepassword;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.os.Handler;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cse.hrcap.LoginActivity;
import com.cse.hrcap.LoginDbHelper;
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.network.ChengePasswordApiClient;
import com.cse.hrcap.network.ChengePasswordResponse;
import com.cse.hrcap.databinding.ChengePasswordFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChengePasswordFragment extends Fragment {
    ChengePasswordFragmentBinding binding;
    EditText UserName, OldPassword, NewPassword, ConfirmPassword, CompanyId;
    Button Savebutton;
    private ChengePasswordViewModel mViewModel;
    private boolean isPasswordUpdated = false;

    public static ChengePasswordFragment newInstance() {
        return new ChengePasswordFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        // return inflater.inflate(R.layout.chenge_password_fragment, container, false);
        binding = ChengePasswordFragmentBinding.inflate(inflater);
        UserName = binding.etusername;
        OldPassword = binding.etoldpassword;
        NewPassword = binding.etnewpassword;
        ConfirmPassword = binding.etconfirmpassword;
        CompanyId = binding.etcompanyid;
        Savebutton = binding.savebtn;

        Intent intent = getActivity().getIntent();
        String companyid = intent.getStringExtra("CompanyId");
        String username = intent.getStringExtra("Employee");


        UserName.setText(username);
        CompanyId.setText(companyid);


        Savebutton.setOnClickListener(view -> {
            if (TextUtils.isEmpty(UserName.getText().toString()) || TextUtils.isEmpty(OldPassword.getText().toString()) ||
                    TextUtils.isEmpty(NewPassword.getText().toString()) || TextUtils.isEmpty(ConfirmPassword.getText().toString()) ||
                    TextUtils.isEmpty(CompanyId.getText().toString())) {


                if (TextUtils.isEmpty(UserName.getText().toString().trim())) {
                    UserName.setError("Username Can't be Empty");
                }
                if (TextUtils.isEmpty(OldPassword.getText().toString().trim())) {
                    OldPassword.setError("Password Can't be Empty");
                }
                if (TextUtils.isEmpty(NewPassword.getText().toString().trim())) {
                    NewPassword.setError("Password Can't be Empty");
                }
                if (TextUtils.isEmpty(ConfirmPassword.getText().toString().trim())) {
                    ConfirmPassword.setError("Password Can't be Empty");
                }
                if (TextUtils.isEmpty(CompanyId.getText().toString().trim())) {
                    CompanyId.setError("CompanyId Can't be Empty");
                }


                Toast.makeText(requireContext(), "All Field is Required", Toast.LENGTH_LONG).show();
            } else {

                if (NewPassword.getText().toString().equals(ConfirmPassword.getText().toString())) {

                    chengepassword();

                } else {
                    ConfirmPassword.setError("Password Did Not Matched");
                }

            }
        });


        return binding.getRoot();


    }

    private void chengepassword() {
        String username = UserName.getText().toString();
        String oldpassword = OldPassword.getText().toString();
        String newpassword = NewPassword.getText().toString();
        String confirmpassword = ConfirmPassword.getText().toString();
        String companyid = CompanyId.getText().toString();
        Call<ChengePasswordResponse> loginResponseCall = ChengePasswordApiClient.getUserService().chengePassword(username, oldpassword, newpassword, confirmpassword, companyid);
        loginResponseCall.enqueue(new Callback<ChengePasswordResponse>() {
            @Override
            public void onResponse(Call<ChengePasswordResponse> call, Response<ChengePasswordResponse> response) {

                if (response.isSuccessful()) {
                    //Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_LONG).show();
                    ChengePasswordResponse loginResponse = response.body();
                    Toast.makeText(requireContext(), "Status is :" + loginResponse.getStatus(), Toast.LENGTH_SHORT).show();

                    if (loginResponse.getStatus().equals("Password Successfully Updated")) {

                        Toast.makeText(requireContext(), "Please Login With New Password", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(requireContext(), LoginActivity.class);
                        LoginDbHelper LoginDbHelper = new LoginDbHelper(requireContext());
                        LoginDbHelper.deleteAllRecords();
                        LoginDbHelper.insertRecord(username, confirmpassword);


                        startActivity(intent);
                        getActivity().finish();
                    }

                } else {
                    Toast.makeText(requireContext(), "Sorry something went wrong", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ChengePasswordResponse> call, Throwable t) {
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
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ChengePasswordViewModel.class);
        // TODO: Use the ViewModel
    }

}