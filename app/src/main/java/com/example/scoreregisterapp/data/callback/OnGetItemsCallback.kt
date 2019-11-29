package com.example.scoreregisterapp.data.callback

interface OnGetItemsCallback<T> {
    fun onSuccess(items: List<T>?)
    fun onError()
}