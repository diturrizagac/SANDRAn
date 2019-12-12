package com.example.scoreregisterapp.presentation

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.diturrizaga.easypay.util.NavigationTo.goTo
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.domain.model.HomeData
import com.example.scoreregisterapp.domain.model.Role
import com.example.scoreregisterapp.presentation.adapter.HomeAdapter
import com.example.scoreregisterapp.presentation.qr.QrGenerateActivity
import com.example.scoreregisterapp.presentation.qr.QrScanActivity

import java.util.ArrayList


class UserHomeActivity : AppCompatActivity() {

    private val TAG = "UserHomeActivity"
    private var userId: String? = null
    private var userRole: String? = null

    private var showQrButton: Button? = null

    private var itemList: ArrayList<HomeData>? = null
    private var recyclerView: RecyclerView? = null
    private var homeAdapter: HomeAdapter? = null

    private var adapterListener = object : View.OnClickListener {
        override fun onClick(view: View?) {
            val viewHolder = view!!.tag as RecyclerView.ViewHolder
            val position = viewHolder.adapterPosition

            when (position) {
                0 -> goTo(UserProfileActivity::class.java, this@UserHomeActivity, userId)
                1 -> goTo(QrScanActivity::class.java, this@UserHomeActivity, userId)
                2 -> goTo(UserCoursesActivity::class.java, this@UserHomeActivity, userId)
                3 -> goTo(getActivityByRole(), this@UserHomeActivity, userId)
            }
            //goTo(UserProfileActivity::class.java,this@UserHomeActivity, userId)
            Log.v(TAG, "tapping any item" )
        }
    }

    private fun getActivityByRole(): Class<*> {
        return if (userRole.equals(Role.teacher.name)) {
            CreateLessonActivity::class.java
        } else {
            StudentSummaryActivity::class.java
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
        userId = intent.extras?.getString("userId")
        userRole = intent.extras?.getString("userRole")
    }

    private fun setListener() {
        homeAdapter!!.setOnItemClickListener(adapterListener)
        showQrButton!!.setOnClickListener {
            goTo(QrGenerateActivity::class.java, this, userId)
        }
    }

}
