package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.data.repository.CourseRepository
import com.example.scoreregisterapp.data.repository.EnrollmentRepository
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Enrollment
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.CourseData
import com.example.scoreregisterapp.domain.model.HomeData
import com.example.scoreregisterapp.presentation.adapter.CoursesAdapter
import java.util.ArrayList

class UserCoursesActivity : AppCompatActivity() {

    private val TAG = "UserCoursesActivity"

    private var currentUser: User? = null
    private var userId: String? = null
    private var courseId: String? = null

    private var title: TextView? = null
    private var itemList: ArrayList<HomeData>? = null
    private var recyclerView: RecyclerView? = null
    private var courseAdapter: CoursesAdapter? = null

    private var idList: List<Enrollment>? = null
    private var idCoursesList: List<String>? = null
    private var currentCourse: Course? = null
    private var coursesList = ArrayList<Course>()

    private var enrollmentRepository = EnrollmentRepository(getRestProvider()).getInstance()
    private var courseRepository = CourseRepository(getRestProvider()).getInstance()


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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_courses)
        retrieveData()
        initializeUI()
        setUpRecyclerView()
        getUserEnrollment()

    }

    private fun retrieveData() {
        currentUser = intent.extras?.getSerializable("data") as User
        userId = currentUser?.objectId
    }

    private fun initializeUI() {
        recyclerView = findViewById(R.id.courses_recycler_view)
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
    }

    private fun getUserEnrollment() {
        enrollmentRepository.getUserEnrollment(
            userId,
            object : OnGetItemsCallback<Enrollment> {
                override fun onSuccess(items: List<Enrollment>?) {
                    idList = items
                    getAllCourses(idList)
                }

                override fun onError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        )
    }

    fun getAllCourses(items : List<Enrollment>?) {
        val item = items!!.iterator()
        while(item.hasNext()){
            val course = item.next()
            getCourse(course.id_course_db)
        }
    }

    private fun getCourse(courseId: String?) {
        courseRepository.getCourse(
            courseId,
            idCoursesList?.size,
            object : OnGetItemCallback<Course> {
                override fun onSuccess(item: Course?) {
                    currentCourse = item
                    coursesList.add(currentCourse!!)
                    if (idList?.size == coursesList.size) {
                        courseAdapter = CoursesAdapter(this@UserCoursesActivity,parseCourseToCourseData(coursesList))
                        setAdapter(courseAdapter!!)
                    }
                }

                override fun onError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        )
    }

    private fun parseCourseToCourseData(courses: ArrayList<Course>) : ArrayList<CourseData> {
        val filteredList = ArrayList<CourseData>()
        val item = courses.iterator()
        while (item.hasNext()) {
            val course = item.next()
            val courseData = CourseData()
            courseData.title = course.name
            courseData.image = images[0]
            filteredList.add(courseData)
        }
        return filteredList
    }

    private fun setAdapter(adapter : CoursesAdapter) {
        recyclerView?.adapter = adapter
    }

}
