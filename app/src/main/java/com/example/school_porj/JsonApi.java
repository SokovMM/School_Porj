package com.example.school_porj;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface JsonApi {
    @GET("readAllPrichin")
    Call<ArrayList<StudentClass>> callPost3();

    @POST("create")
    Call<StudentClass>postSt(@Body StudentClass studentClass);


    @GET("readAllClass")
    Call<ArrayList<StudentClass>> callPost2();

    @GET("read/id_group/{id_class}")
    Call<ArrayList<StudentClass>>getClassById(@Path(value = "id_class") Integer id_class);
}
