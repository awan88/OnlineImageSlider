package com.example.parsaniahardik.imagesliderurl.Apiservice;

import com.example.parsaniahardik.imagesliderurl.Data.ResBanner;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("YoureApi") // app/publick/api/imageslider
    Call<ResBanner> getBanners();
}
