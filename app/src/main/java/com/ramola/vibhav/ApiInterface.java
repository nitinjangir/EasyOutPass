package com.ramola.vibhav;

import com.ramola.vibhav.Model.Student;
import com.ramola.vibhav.Model.StudentListResponse;
import com.ramola.vibhav.Model.UpdateStudentResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

/**
 * Created by sahil on 30/3/17.
 */

public interface ApiInterface {

    @GET("student/detail/{rfId}")
    Call<Student> getStudentInformation(@Path("rfId") String id);

    @GET("student/detail/all/time/{t}")
    Call<StudentListResponse> getAllStudentDetail(@Path("t") int t);

    @GET("student/detail/all")
    Call<StudentListResponse> getAllStudentDetail();

    @POST("student/update/{rfid}")
    @FormUrlEncoded
    Call<UpdateStudentResponse> updateStudentDetail(@Path("rfid") String rfId,@Field("updateTime") long timeOfUpdate, @Field("location") String location);

    @GET("record/all")
    Call<StudentListResponse> getAllRecordDetail();

}
