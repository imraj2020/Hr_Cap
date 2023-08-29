package com.cse.hrcap.network;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
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


    @POST("Api/AttendanceApi/PostCheckTimeApi")
    Call<AtdcheckResponse> atdcheckResponseCall(
            @Query("Employee")  String Employee ,
            @Query("InOutTime") String InOutTime,
            @Query("Type") String Type,
            @Query("CompanyId") String CompanyId
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


    @GET("Api/HolidayApi")
    Call<List<HolidayResponse>> holiday(
            @Query("CompanyId") String CompanyId
    );


    @GET("Api/LeaveBalanceApi")
    Call<List<LeaveBalanceResponse>> leavebalance(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );
    @POST("Api/LeaveApi")
    Call<LeaveRequest> PostData(@Body LeaveRequest leaveRequest);

    @POST("Api/AttendanceApi")
    Call<AttandanceRequest> PostDatass(@Body AttandanceRequest attandanceRequest);

    @GET("Api/LeaveApi")
    Call<List<LeaveSummary>> leavesummary(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );

    @GET("Api/AttendanceApi")
    Call<List<AttdanceSummary>> attdancesummary(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );

    @GET("Api/AttendanceRegularizationApiMultiple")
    Call<List<AttdanceRegularizationSummary>> attdanceregsummary(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );


    @POST("Api/AttendanceRegularizationApiMultiple")
    Call<AttandanceRegularizationRequest> PostData(@Body AttandanceRegularizationRequest attandanceRegularizationRequest);


    @GET("Api/MovementCaptureMultipleDaysApprovalApi")
    Call<List<AtdRegAprSummary>> atdregaprsummary(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );


    @GET("Api/LeaveApprovalApi")
    Call<List<LeaveAprSummary>> leaveaprsummary(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );

    //Api/LeaveApprovalApi

    @POST("Api/LeaveApprovalApi")
    Call<LeaveApprovalRequest> PostDatas(@Body LeaveApprovalRequest leaveApprovalRequest);


    @POST("Api/MovementCaptureMultipleDaysApprovalApi")
    Call<RegularizationApprovalResponse> MyPostData(@Body RegularizationApprovalResponse regularizationApproval);




    @GET("Api/MovementReasonApi")
    Call<List<RegReasonRequest>> myreason(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee
    );



    @GET("Api/AttendanceApi")
    Call<CheckAttendanceResponse> GetAtdInfo(
            @Query("CompanyId") String CompanyId,
            @Query("Employee") String Employee,
            @Query("ToDate") String ToDate
    );


    @POST("Api/CompanyEmployeeApi/PostCompanyEmployeeApi")
    Call<List<EmployeeResponse>> GetAllEmployee(
            @Query("CompanyId") String CompanyId
    );

}
