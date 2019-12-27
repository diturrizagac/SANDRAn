package com.example.scoreregisterapp.data.repository

import android.util.Log
import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService
import com.example.scoreregisterapp.data.RestService.APP_ID
import com.example.scoreregisterapp.data.RestService.REST_API_KEY
import com.example.scoreregisterapp.data.callback.OnPostItemCallback
import com.example.scoreregisterapp.domain.entities.Lesson
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LessonRepository : Repository<LessonRepository>{

    private var restProvider: RestProvider? = null
    private val TAG = "LessonRepository"

    companion object {
        private var repository: LessonRepository? = null
    }

    constructor(restProvider: RestProvider?) {
        this.restProvider = restProvider
    }

    override fun getInstance(): LessonRepository {
        if (repository == null) {
            repository = LessonRepository(restProvider)
        }
        return repository as LessonRepository
    }

    fun createLesson(lesson: Lesson?, callback: OnPostItemCallback<Lesson>?) {
        val currentLesson = RestService.getRestProvider().createLesson(APP_ID, REST_API_KEY,lesson)
        Log.i(TAG, "POST---> ${currentLesson.request().url()}")
        requestPostLesson(currentLesson,callback)
    }

    private fun requestPostLesson(call:  Call<Lesson>?, callback: OnPostItemCallback<Lesson>?) {
        call?.enqueue(
            object : Callback<Lesson> {
                override fun onFailure(call: Call<Lesson>, t: Throwable) {
                    callback?.onError()
                    Log.e(TAG,t.toString())
                    Log.e(TAG, " -------> Unable to submit post to API.")
                }

                override fun onResponse(call: Call<Lesson>, response: Response<Lesson>) {
                    if (response.isSuccessful) {
                        val lessonResponse = response.body()
                        if (lessonResponse != null) {
                            callback?.onSuccess(lessonResponse)
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


}