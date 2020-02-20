package com.example.parsaniahardik.imagesliderurl.Data;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class ResBanner {

    @SerializedName("data")
    private ArrayList<DataBanner> dataBanners;

    public ResBanner(){
    }

    public ArrayList<DataBanner> getDataBanners() {
        return dataBanners;
    }
}
