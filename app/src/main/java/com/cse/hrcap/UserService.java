package com.cse.hrcap;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {
//    @POST("basic")
//    Call<LoginResponse> getuser(@Header("Authorization")  String authHeader) ;
 //previous
    @POST("LoginApi/")
 Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);


}
