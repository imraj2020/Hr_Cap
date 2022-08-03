package com.cse.hrcap.ui.chengepassword;

import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
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
import com.cse.hrcap.MainActivity;
import com.cse.hrcap.network.ChengePasswordApiClient;
import com.cse.hrcap.network.ChengePasswordResponse;
import com.cse.hrcap.databinding.ChengePasswordFragmentBinding;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChengePasswordFragment extends Fragment {
    ChengePasswordFragmentBinding binding;
    EditText UserName, OldPassword,NewPassword,ConfirmPassword, CompanyId;
    Button Savebutton;
    private ChengePasswordViewModel mViewModel;

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

        Savebutton.setOnClickListener(view -> {
            if(TextUtils.isEmpty(UserName.getText().toString()) || TextUtils.isEmpty(OldPassword.getText().toString()) ||
                    TextUtils.isEmpty(NewPassword.getText().toString()) || TextUtils.isEmpty(ConfirmPassword.getText().toString()) ||
                    TextUtils.isEmpty(CompanyId.getText().toString())){
                Toast.makeText(requireContext(),"All Field is Required", Toast.LENGTH_LONG).show();
            }else{
                //proceed to chenge password
                chengepassword();
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
        Call<ChengePasswordResponse> loginResponseCall = ChengePasswordApiClient.getUserService().chengePassword(username,oldpassword,newpassword,confirmpassword,companyid);
        loginResponseCall.enqueue(new Callback<ChengePasswordResponse>() {
            @Override
            public void onResponse(Call<ChengePasswordResponse> call, Response<ChengePasswordResponse> response) {

                if(response.isSuccessful()){
                    //Toast.makeText(requireContext(),"Login Successful", Toast.LENGTH_LONG).show();
                    ChengePasswordResponse loginResponse = response.body();
                    Toast.makeText(requireContext(), "Status is :"+loginResponse.getStatus(), Toast.LENGTH_LONG).show();
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {

                        }
                    },700);

                }else{
                    Toast.makeText(requireContext(),"Sorry something went wrong", Toast.LENGTH_LONG).show();

                }

            }

            @Override
            public void onFailure(Call<ChengePasswordResponse> call, Throwable t) {
                Toast.makeText(requireContext(),"Throwable "+t.getLocalizedMessage(), Toast.LENGTH_LONG).show();

            }
        });

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