package com.example.scoreregisterapp.data.repository

import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService

class GradeRepository {
    private var restProvider: RestProvider? = null
    private val TAG = "GradeRepository"

    constructor(restProvider: RestProvider) {
        this.restProvider = restProvider
    }

    companion object {
        private var repository: GradeRepository? = null
        @JvmStatic
        fun getInstance(): GradeRepository {
            if (repository == null) {
                repository = GradeRepository(RestService.getRestProvider())
            }
            return repository as GradeRepository
        }
    }
}