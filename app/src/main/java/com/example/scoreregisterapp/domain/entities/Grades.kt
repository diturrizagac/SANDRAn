package com.example.scoreregisterapp.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Grades {


    @SerializedName("id_course")
    @Expose
    var id_course: String? = null

    @SerializedName("partial_exam")
    @Expose
    var partial_exam: Double? = null

    @SerializedName("auxiliar_exam")
    @Expose
    var auxiliar_exam: Double? = null

    @SerializedName("final_grade")
    @Expose
    var final_grade: Double? = null

    @SerializedName("final_exam")
    @Expose
    var final_exam: Double? = null

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
}