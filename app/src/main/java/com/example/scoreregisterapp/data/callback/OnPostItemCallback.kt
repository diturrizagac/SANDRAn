package com.example.scoreregisterapp.data.callback

interface OnPostItemCallback<T> {
    fun onSuccess(item: T?)
    fun onError()
}