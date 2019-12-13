package com.example.scoreregisterapp.data


import com.example.scoreregisterapp.domain.entities.Attendance
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Lesson
import com.example.scoreregisterapp.domain.entities.User
import retrofit2.Call
import retrofit2.http.*

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

    @GET("{bl_key}/{api_key}/data/Users/{objectId")
    fun getUserGrades(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?,
        @Path("objectId") objectId: String?,
        @Query("loadRelations") loadRelations: String
    ): Call<User>


    @POST("{bl_key}/{api_key}/data/Attendance")
    fun createAttendance(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?,
        @Body attendance: Attendance?
    ): Call<Attendance>


    @POST("{bl_key}/{api_key}/data/Lesson")
    fun createLesson(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?,
        @Body lesson: Lesson?
    ): Call<Lesson>



    @GET("{bl_key}/{api_key}/data/")
    fun getAttendance2(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?

    ): Call<Attendance>
    @GET("{bl_key}/{api_key}/data/")
    fun getAttendance3(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?

    ): Call<Attendance>
    @GET("{bl_key}/{api_key}/data/")
    fun getAttendance4(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?

    ): Call<Attendance>
    @GET("{bl_key}/{api_key}/data/")
    fun getAttendance5(
        @Path("bl_key") bl_key : String?,
        @Path("api_key") api_key : String?

    ): Call<Attendance>


}