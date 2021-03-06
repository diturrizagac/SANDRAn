package com.example.scoreregisterapp.data.repository

import android.util.Log
import com.example.scoreregisterapp.data.RestService
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.domain.entities.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

object CourseRepository {

    private val TAG = "CourseRepository"

    fun getCourse(courseId: String?, size: Int?, callback: OnGetItemCallback<Course>?) {
        val course = RestService.getRestProvider().getCourse(APP_ID, REST_API_KEY,courseId)
        Log.i(TAG, "GET---> ${course?.request()?.url()}")
        requestCourse(course, size, callback)
    }

    private fun requestCourse(call: Call<Course>?, size: Int?, callback: OnGetItemCallback<Course>?) {
        call?.enqueue(
            object : Callback<Course> {
                override fun onFailure(call: Call<Course>, t: Throwable) {
                    Log.v("ERROR $TAG", t.toString())
                }

                override fun onResponse(call: Call<Course>, response: Response<Course>) {
                    if (response.isSuccessful) {
                        val courseResponse = response.body()
                        if (courseResponse != null) {
                            callback?.onSuccess(courseResponse)
                        }
                    }
                }

            }
        )
    }
}