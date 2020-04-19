package com.example.scoreregisterapp.data.repository

import android.annotation.SuppressLint
import android.util.Log
import com.example.scoreregisterapp.data.RestService
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.domain.entities.Enrollment
import com.example.scoreregisterapp.domain.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EnrollmentRepository private constructor() {

    private object HOLDER {
        val INSTANCE = EnrollmentRepository()
    }

    companion object {
        private val TAG = "EnrollmentRepository"
        val instance: EnrollmentRepository by lazy { HOLDER.INSTANCE }
    }

    fun getUserEnrollment(userId: String?, callback: OnGetItemsCallback<Enrollment>?) {
        val enrollment = RestService.getRestProvider().getUserEnrollment(APP_ID,REST_API_KEY,userId,"enrollment")
        Log.i(TAG, "GET---> ${enrollment?.request()?.url()}")
        requestUserEnrollment(enrollment,callback)
    }

    private fun requestUserEnrollment(call: Call<User>?, callback: OnGetItemsCallback<Enrollment>?) {
        call?.enqueue(
            object : Callback<User> {
                @SuppressLint("LongLogTag")
                override fun onFailure(call: Call<User>, t: Throwable) {
                    Log.v("ERROR $TAG", t.toString())
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        if (userResponse != null) {
                            callback?.onSuccess(userResponse.enrollment)
                        } else {
                            callback?.onError()
                        }
                    } else {
                        callback?.onError()
                    }
                }

            }
        )
    }
}