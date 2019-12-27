package com.example.scoreregisterapp.data.repository

interface Repository<T> {
    fun getInstance() : T
}