package com.example.albummanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var itemAdapters: ItemAdapters
    private lateinit var linearLayoutManager: LinearLayoutManager
    private lateinit var itemList: ArrayList<MyDataItem>
    companion object {
        const val BASE_URL = "https://jsonplaceholder.typicode.com/"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val actionBar = supportActionBar
        actionBar!!.setDisplayShowHomeEnabled(true)
        actionBar.setDisplayHomeAsUpEnabled(true)
        actionBar.displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM
        actionBar.setCustomView(R.layout.center_app_name)

        itemList = ArrayList()
        rvPhotoList.setHasFixedSize(true)
        linearLayoutManager = LinearLayoutManager(this)
        itemAdapters = ItemAdapters(this, getItemList())
        rvPhotoList.adapter = itemAdapters
        rvPhotoList.layoutManager = linearLayoutManager

        getMyData()
    }

    private fun getMyData() {
        //Make Retrofit Builder Object
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(BASE_URL)
            .build()
            .create(ApiInterface::class.java)

        //Get Date from the Retrofit Builder
        val retrofitData = retrofit.getData()//inherited from the Api Interface

        //Add enqueue method
        retrofitData.enqueue(object : Callback<ArrayList<MyDataItem>?> {
            override fun onResponse(
                call: Call<ArrayList<MyDataItem>?>,
                response: Response<ArrayList<MyDataItem>?>
            ) {
                val responseBody = response.body()!!

                //Print data to the Recycler View
                itemAdapters = ItemAdapters(baseContext, responseBody)
                rvPhotoList.adapter = itemAdapters

            }


            override fun onFailure(call: Call<ArrayList<MyDataItem>?>, t: Throwable) {
                TODO("Not yet implemented")
            }

        })

    }

    private fun getItemList():ArrayList<MyDataItem>
    {
        return itemList
    }

}

