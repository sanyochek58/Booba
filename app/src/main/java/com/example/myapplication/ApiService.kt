package com.example.myapplication

import com.example.myapplication.Model.UserDTOlogin
import com.example.myapplication.Model.UserDTOregister
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiService {
    @POST("/api/users/register")
    fun register(@Body userDTOregister: UserDTOregister) : Call<String>

    @POST("/api/users/auth")
    fun authenticate(@Body userDTOlogin: UserDTOlogin) : Call<String>
}