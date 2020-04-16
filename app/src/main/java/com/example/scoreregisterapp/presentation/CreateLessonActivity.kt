package com.example.scoreregisterapp.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import androidx.recyclerview.widget.RecyclerView
import com.diturrizaga.easypay.util.NavigationTo
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.RestService.getRestProvider
import com.example.scoreregisterapp.data.callback.OnGetItemCallback
import com.example.scoreregisterapp.data.callback.OnGetItemsCallback
import com.example.scoreregisterapp.data.callback.OnPostItemCallback
import com.example.scoreregisterapp.data.repository.CourseRepository
import com.example.scoreregisterapp.data.repository.EnrollmentRepository
import com.example.scoreregisterapp.data.repository.LessonRepository
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.entities.Enrollment
import com.example.scoreregisterapp.domain.entities.Lesson
import com.example.scoreregisterapp.domain.entities.User
import com.example.scoreregisterapp.domain.model.LessonData
import com.example.scoreregisterapp.presentation.adapter.CoursesAdapter
import com.example.scoreregisterapp.presentation.qr.QrGenerateActivity
import java.util.ArrayList

class CreateLessonActivity : AppCompatActivity() {

    private val TAG = "CreateLessonActivity"
    private val CLASS = "lesson"

    private val lessonRepository = LessonRepository(getRestProvider()).getInstance()
    private val enrollmentRepository = EnrollmentRepository(getRestProvider()).getInstance()
    private val courseRepository = CourseRepository(getRestProvider()).getInstance()

    private var isFinishedLoad: Boolean? = false

    private var recyclerView: RecyclerView? = null
    private var courseAdapter: CoursesAdapter? = null

    private var currentCourseName: String? = null
    private var lessonData: LessonData? = null

    private var idList: List<Enrollment>? = null
    private var idCoursesList: List<String>? = null
    private var currentCourse: Course? = null
    private var coursesList = ArrayList<Course>()

    private var coursesFromSpinner: AppCompatSpinner? = null
    private var lessonSubjectEditText: AppCompatEditText? = null
    private var createLessonButton: Button? = null
    private var userNameCoursesSpinner = ArrayList<String>()


    private var teacherId: String? = null
    private var currentTeacher: User? = null

    private var currentLesson: Lesson? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_lesson)
        retrieveData()
        initializeUI()
        setListener()
        getUserEnrollment()

    }

    private fun initializeUI() {
        coursesFromSpinner = findViewById(R.id.courses_spinner)
        lessonSubjectEditText = findViewById(R.id.lesson_subject_et)
        createLessonButton = findViewById(R.id.create_lesson_button)
    }

    private fun retrieveData() {
        currentTeacher = intent.extras?.getSerializable("data") as User
        teacherId = currentTeacher?.objectId
    }

    private fun setListener() {
        createLessonButton?.setOnClickListener {
            createNewLesson()
        }
    }

    /***
     * request to server for courses enrolled
     */

    private fun getUserEnrollment() {
        enrollmentRepository.getUserEnrollment(
            teacherId,
            object : OnGetItemsCallback<Enrollment> {
                override fun onSuccess(items: List<Enrollment>?) {
                    idList = items
                    getAllCourses(idList)
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL")
                }

            }
        )
    }

    fun getAllCourses(items: List<Enrollment>?) {
        val item = items!!.iterator()
        while (item.hasNext()) {
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
                    saveCourseName(currentCourse)
                    setCurrentCoursesToList(currentCourse, userNameCoursesSpinner)
                    if (idList?.size == coursesList.size) {
                        setSpinner(userNameCoursesSpinner, coursesFromSpinner)
                    }
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL")
                }

            }
        )
    }

    @SuppressLint("LongLogTag")
    private fun setCurrentCoursesToList(course: Course?, spinnerArray: ArrayList<String>?) {
        val courseName = course?.name
        spinnerArray?.add(courseName!!)
        Log.v(TAG, courseName!!)
    }


    @SuppressLint("ResourceType")
    private fun setSpinner(nameList: ArrayList<String>, spinner: AppCompatSpinner?) {
        val dataAdapter = ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, nameList)
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner?.adapter = dataAdapter
        setSpinnerListener()
    }

    private fun saveCourseName(course: Course?) {
        coursesList.add(course!!)
    }

    private fun setSpinnerListener() {
        coursesFromSpinner?.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                val name = parent!!.getItemAtPosition(0)
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                currentCourseName = parent!!.selectedItem as String
                currentCourse = getCurrentCourse(currentCourseName, coursesList)
            }

        }
    }


    private fun getCurrentCourse(name: String?, courses: ArrayList<Course>?) : Course {
        var course : Course? = null
        val iterator = courses?.iterator()
        while (iterator!!.hasNext()) {
            course = iterator.next()
            if (course.name == name) {
                return course
            }
        }
        return course ?: throw NullPointerException("Expression 'course' must not be null")
    }

    private fun createNewLesson() {
        populateCurrentLesson()
        lessonRepository.createLesson(
            currentLesson,
            object : OnPostItemCallback<Lesson> {
                override fun onSuccess(item: Lesson?) {
                    currentLesson = item
                    populateLessonData()
                    Log.v(TAG, "Data Payer posted")
                    NavigationTo.goTo(QrGenerateActivity::class.java, this@CreateLessonActivity, lessonData)
                }

                override fun onError() {
                    Log.v(TAG, "Couldn't bring data from URL")
                }

            }
        )
    }

    private fun populateLessonData() {
        lessonData = LessonData()
        lessonData?.id_course = currentCourse?.objectId
        lessonData?.id_lesson = currentLesson?.objectId
        lessonData?.id_teacher = currentTeacher?.objectId
    }


    private fun populateCurrentLesson() {
        currentLesson = Lesson()
        currentLesson?._class = CLASS
        currentLesson?.id_course = currentCourse?.objectId
        currentLesson?.id_teacher = currentTeacher?.objectId
        currentLesson?.subject = lessonSubjectEditText?.text.toString()
        currentLesson?.objectId = ""
        currentLesson?.created = null
        currentLesson?.updated = null
        currentLesson?.ownerId = null
        currentLesson?._class = CLASS
    }

    private fun showAlertDialog() {
        // Initialize a new instance of
        val builder = AlertDialog.Builder(this)
        // Set the alert dialog title
        builder.setTitle("Confirm your transaction")
        // Display a message on alert dialog
        builder.setMessage("Do you want to confirm this transaction?")
        // Set a positive button and its click listener on alert dialog
        builder.setPositiveButton("Yes"){_,_ ->
            // Do something when user press the positive button
            //setRelationOnBackendless(payerCurrentAccount!!, currentTransaction!! )
            //setRelationOnBackendless(creditorCurrentAccount!!,creditorCurrentTransaction!!)
            //updateCreditorAccount()
            Toast.makeText(applicationContext,"Ok, we're sending your transaction", Toast.LENGTH_SHORT).show()
            //goTo(SuccessfulOperationActivity::class.java, this,payerUserId!!, currentTransaction!!, payerCurrentAccount!!.balance!!)
        }
        // Display a negative button on alert dialog
        builder.setNegativeButton("No"){_,_ ->
            Toast.makeText(applicationContext,"You canceled this operation", Toast.LENGTH_SHORT).show()
        }
        // Display a neutral button on alert dialog
        builder.setNeutralButton("Cancel"){_,_ ->
            Toast.makeText(applicationContext,"You cancelled the dialog.", Toast.LENGTH_SHORT).show()
        }
        // Finally, make the alert dialog using builder
        val dialog: AlertDialog = builder.create()
        // Display the alert dialog on app interface
        dialog.show()
    }

}
