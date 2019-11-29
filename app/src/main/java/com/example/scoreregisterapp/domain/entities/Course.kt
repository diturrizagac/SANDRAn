package com.example.scoreregisterapp.domain.entities

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Course : Serializable {
    @SerializedName("created")
    @Expose
    var created: Long? = null
    @SerializedName("name")
    @Expose
    var name: String? = null
    @SerializedName("id")
    @Expose
    var id: String? = null
    @SerializedName("updated")
    @Expose
    var updated: Long? = null
    @SerializedName("plan")
    @Expose
    var plan: String? = null
    @SerializedName("objectId")
    @Expose
    var objectId: String? = null
    @SerializedName("cycle")
    @Expose
    var cycle: String? = null
    @SerializedName("ownerId")
    @Expose
    var ownerId: String? = null
    @SerializedName("___class")
    @Expose
    var _class: String? = null
}