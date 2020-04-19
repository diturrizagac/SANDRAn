package com.example.scoreregisterapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diturrizaga.easypay.util.NavigationTo.goTo
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.HomeData
import com.example.scoreregisterapp.domain.model.Role
import com.example.scoreregisterapp.presentation.adapter.HomeAdapter
import com.example.scoreregisterapp.presentation.qr.QrGenerateActivity
import com.example.scoreregisterapp.presentation.qr.QrScanActivity
import org.w3c.dom.Text

import java.util.ArrayList


class UserHomeActivity : AppCompatActivity() {

    private val TAG = "UserHomeActivity"
    private var currentUser: User? = null
    private var userRole: String? = null

    private var showQrButton: Button? = null
    private var profileImage: ImageView? = null
    private var infoButton: ImageView? = null
    private var profileUsername: TextView? = null
    private var profileUserInformation: TextView? = null

    private var itemList: ArrayList<HomeData>? = null
    private var recyclerView: RecyclerView? = null
    private var homeAdapter: HomeAdapter? = null

    private var adapterListener = object : View.OnClickListener {
        override fun onClick(view: View?) {
            val viewHolder = view!!.tag as RecyclerView.ViewHolder
            val position = viewHolder.adapterPosition

            when (position) {
                0 -> goTo(UserProfileActivity::class.java, this@UserHomeActivity, currentUser)
                1 -> goTo(QrScanActivity::class.java, this@UserHomeActivity, currentUser)
                2 -> goTo(UserCoursesActivity::class.java, this@UserHomeActivity, currentUser)
                3 -> goTo(getActivityByRole(), this@UserHomeActivity, currentUser)
            }
            Log.v(TAG, "Choosing item from dashboard" )
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_home)
        retrieveData()
        initializeUI()
        setUpRecyclerView()
        showOptions()
        setListener()
    }

    private fun initializeUI() {
        profileImage = findViewById(R.id.user_image)
        profileUsername = findViewById(R.id.user_image)
        profileUserInformation = findViewById(R.id.user_information)
        infoButton = findViewById(R.id.info_button)
        showQrButton = findViewById(R.id.showQrButton)
        recyclerView = findViewById(R.id.recyclerView)
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator() as RecyclerView.ItemAnimator?
    }

    private fun showOptions() {
        var beanClassForRecyclerView: HomeData?
        itemList = ArrayList()
        titles = if (userRole.equals(Role.student.name)) {
            studenttitles
        } else {
            teachertitles
        }

        for (i in userImages.indices) {
            beanClassForRecyclerView =
                HomeData(userImages[i], titles[i])
            itemList!!.add(beanClassForRecyclerView)
        }
        homeAdapter = HomeAdapter(this, itemList!!)
        recyclerView!!.adapter = homeAdapter
    }

    private fun retrieveData() {
        currentUser = intent.extras?.getSerializable("data") as User
        userRole = currentUser?.userRole
    }

    private fun setListener() {
        homeAdapter!!.setOnItemClickListener(adapterListener)
        showQrButton!!.setOnClickListener {
            goTo(QrGenerateActivity::class.java, this, currentUser)
        }
    }

    private fun getActivityByRole(): Class<*> {
        return if (userRole.equals(Role.teacher.name)) {
            CreateLessonActivity::class.java
        } else {
            GradesListActivity::class.java
        }
    }

    private val userImages = arrayOf(
        R.drawable.coffee_cup,
        R.drawable.doughnut,
        R.drawable.cake,
        R.drawable.buffet
    )

    private val teachertitles = arrayOf(
        "Profile",
        "Register Grade",
        "Current Courses",
        "Create a Lesson"
    )
    private val studenttitles = arrayOf(
        "Profile",
        "Register Attendance",
        "Current Courses",
        "My Grades"
    )


    private var images = arrayOf(
        R.drawable.coffee_cup,
        R.drawable.doughnut,
        R.drawable.cake,
        R.drawable.egg,
        R.drawable.burgerrrr,
        R.drawable.fries,
        R.drawable.pizza,
        R.drawable.noodles,
        R.drawable.fish,
        R.drawable.buffet
    )
    private var titles = arrayOf(
        "Coffee",
        "Donut",
        "Cake",
        "Omelette",
        "Burger",
        "French Fries",
        "Pizza",
        "Noodles",
        "Fish",
        "More"
    )

}
