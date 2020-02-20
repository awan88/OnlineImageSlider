package com.example.parsaniahardik.imagesliderurl.Apiservice;

public class UrlBase {

    public static final String BASE_URL = "Youse base url"; //http://example.com/

    public static Api getUserService() {
        return RetrofitClient.getClient(BASE_URL).create(Api.class);
    }
}
