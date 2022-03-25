package com.example.albummanager

data class MyDataItem(
    //Must have the same name with the json link
    val albumId :Int,
    val id: Int,
    val title: String,
    val url:String,
    val thumbnailUrl: String
)