package com.example.albummanager

import retrofit2.Call
import retrofit2.http.GET

interface ApiInterface {
    //Get Request
    @GET("posts")//The last word of the URL
    fun getData():Call<ArrayList<MyDataItem>>
}