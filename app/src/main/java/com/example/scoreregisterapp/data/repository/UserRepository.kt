package com.example.scoreregisterapp.data.repository

import android.util.Log
import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.domain.entities.Grades
import com.example.scoreregisterapp.domain.entities.User
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class UserRepository : Repository<UserRepository> {

    private val TAG = "UserRepository"

    companion object {
            private var repository: UserRepository? = null
    }

    override fun getInstance(): UserRepository {
        if (repository == null) {
            repository = UserRepository()
        }
        return repository as UserRepository
    }

    fun getUser(userId: String?, callback: OnGetItemCallback<User>?) {
        val user = getRestProvider().getUser(APP_ID, REST_API_KEY,userId)
        Log.i(TAG, "GET---> ${user?.request()?.url()}")
        requestUser(user, callback)
    }

    private fun requestUser(call: Call<User>?, callback: OnGetItemCallback<User>?) {
        call?.enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    callback?.onError()
                    Log.v("ERROR $TAG", t.toString())
                }

                override fun onResponse(call: Call<User>, response: Response<User>) {
                    if (response.isSuccessful) {
                        val userResponse = response.body()
                        if (userResponse != null) {
                            callback?.onSuccess(userResponse)
                        }
                    }
                }

            }
        )
    }

    fun getUserGrades(userId: String?, callback: OnGetItemsCallback<Grades>?) {
        val grades = getRestProvider().getUserGrades(APP_ID, REST_API_KEY, userId, "grades")
        Log.i(TAG, "GET---> ${grades.request().url()}")
        requestUserGrades(grades, callback)
    }

    private fun requestUserGrades(call: Call<User>?, callback: OnGetItemsCallback<Grades>?) {
        call?.enqueue(
            object : Callback<User> {
                override fun onFailure(call: Call<User>, t: Throwable) {
                    callback?.onError()
                    Log.v("ERROR $TAG", t.toString())
                }

                override fun onResponse(call: Call<User>?, response: Response<User>?) {
                    if (response?.isSuccessful!!) {
                        val userResponse = response.body()
                        if (userResponse != null) {
                            callback?.onSuccess(userResponse.grades!!)
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