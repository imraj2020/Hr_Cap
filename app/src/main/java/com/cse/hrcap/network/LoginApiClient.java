package com.cse.hrcap.network;


import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginApiClient {

    private static Retrofit getRetrofit(){
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new BasicAuthInterceptor("HRApi", "123456"))
                .build();





//        OkHttpClient okHttpClient = new OkHttpClient.Builder().addInterceptor(new Interceptor() {
//            @Override
//            public Response intercept(Chain chain) throws IOException {
//                Request newRequest  = chain.request().newBuilder()
//                        .addHeader("Authorization", "Bearer" + 1234567)
//                        .url("http://184.168.127.174:1394/Api/LoginApi/")
//                        .build();
//                return chain.proceed(newRequest);
//            }
//
//        }).build();








        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("http://184.168.127.174:1313/")
                .client(okHttpClient)
                .build();

        return retrofit;
    }


    public static UserService getUserService(){
        UserService userService = getRetrofit().create(UserService.class);
        return userService;
    }

}
