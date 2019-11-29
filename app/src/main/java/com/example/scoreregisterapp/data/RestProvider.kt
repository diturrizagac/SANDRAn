package com.example.scoreregisterapp.data


import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.User
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RestProvider {


    @GET("{bl_key}/{api_key}/data/Users/{objectId}")
    fun getUser(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?,
        @Path("objectId") objectId: String?
    ): Call<User>?

    @GET("{bl_key}/{api_key}/data/Users/{objectId}")
    fun getUserEnrollment(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?,
        @Path("objectId") objectId: String?,
        @Query("loadRelations") loadRelations: String
    ): Call<User>?

    @GET("{bl_key}/{api_key}/data/course/{objectId}")
    fun getCourse(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?,
        @Path("objectId") objectId: String?
    ): Call<Course>?

}