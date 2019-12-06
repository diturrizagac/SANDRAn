package com.example.scoreregisterapp.domain.entities

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import java.io.Serializable

class Attendance : Serializable {
    @SerializedName("check_in")
    @Expose
    var check_in: String? = null

    @SerializedName("id_student")
    @Expose
    var id_student: String? = null

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