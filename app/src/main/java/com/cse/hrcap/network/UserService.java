package com.cse.hrcap.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface UserService {



//
//    @POST("Api/LoginApi")
//    Call<LoginResponse> userLogin(@Query("username") String username,
//                                     @Query("userPassword") String userPassword,LoginRequest loginRequest);

//    @POST("basic")
//    Call<LoginResponse> getuser(@Header("Authorization")  String authHeader) ;

    @POST("Api/LoginApi")
 Call<LoginResponse> userLogin(
         @Query("username")  String username ,
         @Query("userPassword") String userPassword
    );

    @POST("Api/ChangePasswordApi")
    Call<ChengePasswordResponse> chengePassword(
            @Query("UserName")  String username ,
            @Query("OldPassword") String oldpassword,
            @Query("NewPassword") String newpassword,
            @Query("ConfirmPassword") String confirmpassword,
            @Query("CompanyId") String companyid
    );

    @GET("Api/LeaveTypeApi")
    Call<List<LeaveTypeResponse>> leavetype(
            @Query("CompanyId") String CompanyId
    );


    @GET("Api/LoanTypeApi")
    Call<List<LoanTypeResponse>> loantype(
            @Query("CompanyId") String CompanyId
    );

   @GET("Api/LoanSubTypeApi")
   Call<List<LoansubTypeResponse>> loansubtype(
           @Query("CompanyId") String CompanyId
   );
//    @POST("Api/LoginApi")
//    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
