package com.example.scoreregisterapp.data.repository

import android.util.Log
import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnPostItemCallback
import com.example.scoreregisterapp.domain.entities.Attendance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class AttendanceRepository: Repository<AttendanceRepository> {

    private var restProvider: RestProvider? = null
    private val TAG = "AttendanceRepository"

    constructor(restProvider: RestProvider?) {
        this.restProvider = restProvider
    }

    companion object {
        private var repository: AttendanceRepository? = null
    }

    override fun getInstance(): AttendanceRepository {
        if (repository == null) {
            repository = AttendanceRepository(restProvider)
        }
        return repository as AttendanceRepository
    }

    fun postAttendance(attendance: Attendance?, callback: OnPostItemCallback<Attendance>?) {
        val currentAttendance = getRestProvider().createAttendance(APP_ID, REST_API_KEY,attendance)
        Log.i(TAG, "POST---> ${currentAttendance.request().url()}")
        requestPostAttendance(currentAttendance,callback)
    }

    private fun requestPostAttendance(call: Call<Attendance>?, callback: OnPostItemCallback<Attendance>?) {
        call?.enqueue(
            object : Callback<Attendance> {
                override fun onFailure(call: Call<Attendance>, t: Throwable) {
                    callback?.onError()
                    Log.e(TAG,t.toString())
                    Log.e(TAG, " -------> Unable to submit post to API.")
                }

                override fun onResponse(call: Call<Attendance>, response: Response<Attendance>) {
                    if (response.isSuccessful) {
                        val attendanceResponse = response.body()
                        if (attendanceResponse != null) {
                            callback?.onSuccess(attendanceResponse)
                            Log.i(TAG, "post submitted to API." + response.body().toString())
                        } else {
                            callback?.onError()
                            Log.i(TAG, "post failed to API." + response.body().toString())
                        }
                    } else {
                        callback?.onError()
                        Log.i(TAG, "post failed to API." + response.body().toString())
                    }
                }

            }
        )

    }

    fun getAttendanceByUser() {

    }

    private fun getRequestAttendance() {

    }

}