package org.techtown.capture.intent;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface RestApi {

    @GET("angle")
    Call<List<Postdeep>> getPostdeeps();
}


