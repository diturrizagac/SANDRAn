package com.example.scoreregisterapp.data.repository

import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService

class LessonRepository {
    private var restProvider: RestProvider? = null
    private val TAG = "LessonRepository"

    constructor(restProvider: RestProvider) {
        this.restProvider = restProvider
    }

    companion object {
        private var repository: LessonRepository? = null
        @JvmStatic
        fun getInstance(): LessonRepository {
            if (repository == null) {
                repository = LessonRepository(RestService.getRestProvider())
            }
            return repository as LessonRepository
        }
    }
}