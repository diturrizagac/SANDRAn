package com.example.scoreregisterapp.domain.model

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class QrData : Serializable{
    @SerializedName("id_student")
    @Expose
    var id_student: String? = ""
    @SerializedName("id_lesson")
    @Expose
    var id_lesson: String? = ""
    @SerializedName("id_teacher")
    @Expose
    var id_teacher: String? = ""
    @SerializedName("id_course")
    @Expose
    var id_course: String? = ""
}