package com.example.sach.retrofit.network;


import com.example.sach.retrofit.model.Details;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;

/**
 * Created by Gokul Balakrishnan on 4/4/2015.
 */
public interface api {

    @GET("/jsonone.php")
    public void getStations(Callback<String[]> response);


    @GET("/jsontwo.php")
    public void getname( Callback<List<Details>> response);

    @GET("/jsonthree.php")
    public void getstock( Callback<Details> response);


}
