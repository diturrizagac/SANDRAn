package com.example.scoreregisterapp.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Lesson : Serializable{
    @SerializedName("subject")
    @Expose
    var subject: String? = null

    @SerializedName("id_course")
    @Expose
    var id_course: String? = null

    @SerializedName("id_teacher")
    @Expose
    var id_teacher: String? = null

    @SerializedName("objectId")
    @Expose
    var objectId: String? = null

    @SerializedName("created")
    @Expose
    var created: Long? = null

    @SerializedName("updated")
    @Expose
    var updated: Long? = null

    @SerializedName("ownerId")
    @Expose
    var ownerId: String? = null

    @SerializedName("___class")
    @Expose
    var _class: String? = null

    @SerializedName("attendance")
    @Expose
    var attendances: List<Attendance>? = null
}