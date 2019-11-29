package com.example.scoreregisterapp.data.repository

import android.util.Log
import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class CourseRepository {
    private var restProvider: RestProvider? = null
    private val TAG = "CourseRepository"

    constructor(restProvider: RestProvider) {
        this.restProvider = restProvider
    }

    companion object {
        private var repository: CourseRepository? = null
        @JvmStatic
        fun getInstance(): CourseRepository {
            if (repository == null) {
                repository = CourseRepository(RestService.getRestProvider())
            }
            return repository as CourseRepository
        }
    }

    fun getCourse(courseId: String?, size: Int?, callback: OnGetItemCallback<Course>?) {
        val course = RestService.getRestProvider().getCourse(RestService.APP_ID, RestService.REST_API_KEY,courseId)
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

                    /*try {
                        if (response.isSuccessful) {
                            i = i?.minus(1)
                            if (i!! > 0){
                                val courseResponse = response.body()
                                if (courseResponse != null) {
                                    callback?.onSuccess(courseResponse)
                                }
                            }
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }*/
                }

            }
        )
    }
}