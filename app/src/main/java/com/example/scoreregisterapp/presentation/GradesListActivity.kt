package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.data.repository.CourseRepository
import com.example.scoreregisterapp.data.repository.EnrollmentRepository
import com.example.scoreregisterapp.data.repository.GradeRepository
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Enrollment
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.presentation.adapter.CoursesAdapter
import com.example.scoreregisterapp.presentation.adapter.GradesAdapter
import java.util.ArrayList

class GradesListActivity : AppCompatActivity() {

    private var enrollmentRepository = EnrollmentRepository.getInstance()
    private var courseRepository = CourseRepository.getInstance()
    private var gradeRepository = GradeRepository.getInstance()

    private var currentUser: User? = null
    private var userId: String? = null

    private var recyclerView: RecyclerView? = null
    private var gradeAdapter: GradesAdapter? = null
    private var courseAdapter: CoursesAdapter? = null

    private var idList: List<Enrollment>? = null
    private var idCoursesList: List<String>? = null
    private var currentCourse: Course? = null
    private var coursesList = ArrayList<Course>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grades_list)
    }

    private fun retrieveData() {
        currentUser = intent.extras?.getSerializable("data") as User
        userId = currentUser?.objectId
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
                        courseAdapter = CoursesAdapter(this@GradesListActivity,parseCourseToCourseData(coursesList))
                        setAdapter(courseAdapter!!)
                    }
                }

                override fun onError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        )
    }

    private fun getGrades(userId: String?) {
        gradeRepository
    }
}
