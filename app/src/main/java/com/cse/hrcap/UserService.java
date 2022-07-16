package com.cse.hrcap;
import com.google.gson.JsonObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Path;
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

//    @POST("Api/LoginApi")
//    Call<LoginResponse> userLogin(@Body LoginRequest loginRequest);
}
