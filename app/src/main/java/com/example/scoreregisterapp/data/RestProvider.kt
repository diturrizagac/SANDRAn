package com.example.scoreregisterapp.data


import com.example.scoreregisterapp.domain.entities.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface RestProvider {


    @GET("{bl_key}/{api_key}/data/Users/{objectId}")
    fun getUser(
        @Path("bl_key") bl_key : String,
        @Path("api_key") api_key : String,
        @Path("objectId") objectId: String
    ): Call<User>
}