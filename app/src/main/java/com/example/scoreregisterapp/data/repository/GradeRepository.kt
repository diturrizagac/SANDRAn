package com.example.scoreregisterapp.data.repository

class GradeRepository {

    private object HOLDER {
        val INSTANCE = GradeRepository()
    }
    companion object {
        private val TAG = "GradeRepository"
        val instance: GradeRepository by lazy { HOLDER.INSTANCE }
    }



}