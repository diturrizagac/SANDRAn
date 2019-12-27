package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.data.repository.CourseRepository
import com.example.scoreregisterapp.data.repository.EnrollmentRepository
import com.example.scoreregisterapp.data.repository.GradeRepository
import com.example.scoreregisterapp.data.repository.UserRepository
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Enrollment
import com.example.scoreregisterapp.domain.entities.Grades
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.GradeData
import com.example.scoreregisterapp.presentation.adapter.CoursesAdapter
import com.example.scoreregisterapp.presentation.adapter.GradesAdapter
import java.util.ArrayList

class GradesListActivity : AppCompatActivity() {

    private val TAG = "GradesListActivity"

    private var courseRepository = CourseRepository(getRestProvider()).getInstance()
    //private var userRepository = UserRepository.getInstance()
    private var userRepository = UserRepository(getRestProvider()).getInstance()

    private var isFinishedLoad: Boolean? = false

    private var currentUser: User? = null
    private var userId: String? = null

    private var recyclerView: RecyclerView? = null
    private var gradeAdapter: GradesAdapter? = null

    private var userGrades: List<Grades>? = null
    private var gradeDataList = ArrayList<GradeData>()

    private var idCoursesList: List<String>? = null
    private var currentCourse: Course? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades_list)
        retrieveData()
        initializeUI()
        getUserGrades()
    }

    private fun retrieveData() {
        currentUser = intent.extras?.getSerializable("data") as User
        userId = currentUser?.objectId
    }

    private fun initializeUI() {
        recyclerView = findViewById(R.id.grades_list)
        val layoutManager = LinearLayoutManager(this)
        recyclerView!!.layoutManager = layoutManager
    }

    private fun getUserGrades() {
        userRepository.getUserGrades(
            userId,
            object : OnGetItemsCallback<Grades> {
                override fun onSuccess(items: List<Grades>?) {
                    userGrades = items
                    gradeDataList = parseGradesToGradeData(userGrades)
                    getIdCourses(userGrades)
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL")
                }

            }
        )
    }

    fun getIdCourses(items: List<Grades>?) {
        val item = items?.iterator()
        while (item!!.hasNext()) {
            val grade = item.next()
            getCourse(grade.id_course)
        }
    }

    private fun getCourse(courseId: String?) {
        courseRepository.getCourse(
            courseId,
            idCoursesList?.size,
            object : OnGetItemCallback<Course> {
                override fun onSuccess(item: Course?) {
                    currentCourse = item
                    matchGradeWithCourses(currentCourse, gradeDataList)
                    if (isFinishedLoad!!) {
                        gradeAdapter = GradesAdapter(this@GradesListActivity,gradeDataList)
                        setAdapter(gradeAdapter)
                        isFinishedLoad = false
                    }
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL ")
                }

            }
        )
    }

    private fun matchGradeWithCourses(course: Course?, gradeData: List<GradeData>?) {
        val item = gradeData?.iterator()
        while (item!!.hasNext()) {
            val gradeData = item.next()
            if (gradeData.id_course == course?.objectId) {
                gradeData.course_name = course?.name
                if(!item.hasNext()){
                    isFinishedLoad = true
                }
            }
        }
    }

    private fun parseGradesToGradeData(grades: List<Grades>?): ArrayList<GradeData> {
        val filteredList = ArrayList<GradeData>()
        val item = grades?.iterator()
        while (item!!.hasNext()) {
            val grade = item.next()
            val gradeData = GradeData()
            gradeData.id_course = grade.id_course
            gradeData.final_exam = grade.final_exam
            gradeData.partial_exam = grade.partial_exam
            gradeData.auxiliar_exam = grade.auxiliar_exam
            gradeData.final_grade = grade.final_grade
            filteredList.add(gradeData)

        }
        return filteredList
    }

    private fun setAdapter(adapter : GradesAdapter?) {
        recyclerView?.adapter = adapter
    }
}
