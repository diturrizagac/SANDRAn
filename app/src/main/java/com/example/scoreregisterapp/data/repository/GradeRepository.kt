package com.example.scoreregisterapp.data.repository

import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService

class GradeRepository: Repository<GradeRepository> {

    private var restProvider: RestProvider? = null
    private val TAG = "GradeRepository"

    companion object {
        private var repository: GradeRepository? = null
    }

    constructor(restProvider: RestProvider?) {
        this.restProvider = restProvider
    }

    override fun getInstance(): GradeRepository {
        if (repository == null) {
            repository = GradeRepository(restProvider)
        }
        return repository as GradeRepository
    }


}