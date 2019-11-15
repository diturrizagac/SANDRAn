package com.example.scoreregisterapp.domain.entities

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Course : Serializable {
    @SerializedName("created")
    @Expose
    private var created: Int? = null
    @SerializedName("name")
    @Expose
    private var name: String? = null
    @SerializedName("id")
    @Expose
    private var id: String? = null
    @SerializedName("updated")
    @Expose
    private var updated: Int? = null
    @SerializedName("plan")
    @Expose
    private var plan: String? = null
    @SerializedName("objectId")
    @Expose
    private var objectId: String? = null
    @SerializedName("cycle")
    @Expose
    private var cycle: String? = null
    @SerializedName("ownerId")
    @Expose
    private var ownerId: Any? = null
    @SerializedName("___class")
    @Expose
    private var _class: String? = null
}