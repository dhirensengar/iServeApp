package com.cears.serviceapp.webservice;


import com.cears.serviceapp.models.EquipmentTypeResponse;
import com.cears.serviceapp.models.GetAllJobResponse;
import com.cears.serviceapp.models.LoginResponse;
import com.cears.serviceapp.models.PartsResponse;
import com.cears.serviceapp.models.PreQuestionResponse;
import com.cears.serviceapp.models.QuestionResponse;
import com.cears.serviceapp.models.SectionNameResponse;
import com.cears.serviceapp.models.ServiceTypeResponse;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface Api {


    @POST("service")
    Call<LoginResponse> login(@Body HashMap loginModel);

    @POST("service")
    Call<PreQuestionResponse> prequestion(@Body HashMap loginModel);

    @POST("service")
    Call<EquipmentTypeResponse> equipmentype(@Body HashMap loginModel);


    @POST("service")
    Call<ServiceTypeResponse> servicelist(@Body HashMap loginModel);


    @POST("service")
    Call<SectionNameResponse> sectiontype(@Body HashMap loginModel);

    @POST("service")
    Call<PartsResponse> parts(@Body HashMap loginModel);

    @POST("service")
    Call<LoginResponse> addjobticket(@Body HashMap loginModel);


    @POST("service")
    Call<GetAllJobResponse> getalljob(@Body HashMap<String, Object> loginModel);

    @POST("service")
    Call<QuestionResponse> getquestions(@Body HashMap loginModel);

    /*

    @POST("chat/read_message")
    Call<IsReadResponse> read_message(@Body Map<String, String> options);
*/

 /*   @GET("api.php?method=register")
    Call<DataObject> doSignup1(@QueryMap Map<String, String> options);*/

}
