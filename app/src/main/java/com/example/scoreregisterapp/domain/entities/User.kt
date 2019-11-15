package com.example.scoreregisterapp.domain.entities

import java.io.Serializable
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

open class User : Serializable {

    @SerializedName("first_name")
    @Expose
    open var firstName: String? = null
    @SerializedName("mid_name")
    @Expose
    open var midName: String? = null
    @SerializedName("last_name")
    @Expose
    open var lastName: String? = null
    @SerializedName("objectId")
    @Expose
    open var objectId: String? = null
    @SerializedName("user_role")
    @Expose
    open var userRole: String? = null
    @SerializedName("mobile_number")
    @Expose
    open var mobileNumber: Any? = null
    @SerializedName("ownerId")
    @Expose
    open var ownerId: String? = null
    @SerializedName("email")
    @Expose
    open var email: String? = null
    @SerializedName("university_id")
    @Expose
    open var universityId: Any? = null
    @SerializedName("lastLogin")
    @Expose
    open var lastLogin: Any? = null
    @SerializedName("userStatus")
    @Expose
    open var userStatus: String? = null
    @SerializedName("created")
    @Expose
    open var created: Int? = null
    @SerializedName("socialAccount")
    @Expose
    open var socialAccount: String? = null
    @SerializedName("blUserLocale")
    @Expose
    open var blUserLocale: String? = null
    @SerializedName("updated")
    @Expose
    open var updated: Int? = null
    @SerializedName("___class")
    @Expose
    open var _class: String? = null

    @SerializedName("student_cycle")
    @Expose
    open var studentCycle: Any? = null

    @SerializedName("teacher_rate")
    @Expose
    open var teacherRate: Any? = null



}