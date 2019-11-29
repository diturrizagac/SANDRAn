package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.data.repository.CourseRepository
import com.example.scoreregisterapp.data.repository.EnrollmentRepository
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Enrollment
import com.example.scoreregisterapp.domain.model.CourseData
import com.example.scoreregisterapp.domain.model.HomeData
import com.example.scoreregisterapp.presentation.adapter.CoursesAdapter
import java.util.ArrayList

class UserCoursesActivity : AppCompatActivity() {

    private val TAG = "UserCoursesActivity"
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

    private var enrollmentRepository = EnrollmentRepository.getInstance()
    private var courseRepository = CourseRepository.getInstance()




    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_courses)
        retrieveData()
        initializeUI()
        setUpRecyclerView()
        getUserEnrollment()
        //setData()

    }

    private fun initializeUI() {
        recyclerView = findViewById(R.id.courses_recycler_view)
    }

    private fun setUpRecyclerView() {
        val layoutManager = GridLayoutManager(this, 2)
        recyclerView!!.layoutManager = layoutManager
        recyclerView!!.itemAnimator = DefaultItemAnimator()
    }

    private fun showCourses() {

    }

    private fun retrieveData() {
        userId = intent.extras?.getString("userId")
    }

    private fun getUserEnrollment() {
        enrollmentRepository.getUserEnrollment(
            userId,
            object : OnGetItemsCallback<Enrollment> {
                override fun onSuccess(items: List<Enrollment>?) {
                    idList = items
                    getAllCourses(idList)

                    //idCoursesList = getIdFromCourses(coursesList)
                }

                override fun onError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        )
    }
    private fun getCourse(courseId: String) {
        courseRepository.getCourse(
            courseId,
            idCoursesList?.size,
            object : OnGetItemCallback<Course> {
                override fun onSuccess(item: Course) {
                    currentCourse = item
                    coursesList.add(currentCourse!!)
                }

                override fun onError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        )
    }

    fun getUserCourses(list: List<String>) {

    }


    fun setAdapter(adapter : CoursesAdapter) {
        recyclerView?.adapter = adapter
    }

    fun getIdFromCourses(items : List<Enrollment>?) : List<String>? {

        val filteredList = ArrayList<String>()
        val item = items!!.iterator()
        while(item.hasNext()){
            val course = item.next()
            getCourse(course.id_db!!)
            //ilteredList.add(course.id_course!!)

        }
        return filteredList
    }

    fun getAllCourses(items : List<Enrollment>?) {
        val item = items!!.iterator()
        while(item.hasNext()){
            val course = item.next()
            getCourse(course.id_db!!)
        }
    }


    private fun parseCourseToCourseData(courses: ArrayList<Course>) : ArrayList<CourseData> {
        val filteredList = ArrayList<CourseData>()
        val item = courses.iterator()
        while (item.hasNext()) {
            val course = item.next()
            val courseData = CourseData()
            courseData.title = course.name
            filteredList.add(courseData)
        }
        courseAdapter = CoursesAdapter(this@UserCoursesActivity,parseCourseToCourseData(coursesList))
        setAdapter(courseAdapter!!)
        return filteredList
    }

}
