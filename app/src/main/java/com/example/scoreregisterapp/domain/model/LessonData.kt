package com.example.scoreregisterapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class LessonData: Serializable{
    @SerializedName("id_lesson")
    @Expose
    var id_lesson: String? = null
    @SerializedName("id_teacher")
    @Expose
    var id_teacher: String? = null
    @SerializedName("id_course")
    @Expose
    var id_course: String? = null
}