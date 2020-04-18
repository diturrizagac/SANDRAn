package com.example.scoreregisterapp.data.repository

import com.example.scoreregisterapp.data.RestProvider
import com.example.scoreregisterapp.data.RestService

class GradeRepository: Repository<GradeRepository> {

    private val TAG = "GradeRepository"

    companion object {
        private var repository: GradeRepository? = null
    }

    override fun getInstance(): GradeRepository {
        if (repository == null) {
            repository = GradeRepository()
        }
        return repository as GradeRepository
    }


}