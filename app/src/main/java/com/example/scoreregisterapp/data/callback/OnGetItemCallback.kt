package com.example.scoreregisterapp.data.callback

interface OnGetItemCallback<T> {
    fun onSuccess(item: T)
    fun onError()
}