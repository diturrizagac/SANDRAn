package com.example.scoreregisterapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatSpinner
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.data.callback.OnPostItemCallback
import com.example.scoreregisterapp.data.repository.LessonRepository
import com.example.scoreregisterapp.domain.entities.Lesson

class CreateLessonActivity : AppCompatActivity() {

    private val TAG = "CreateLessonActivity"
    private val CLASS = "lesson"

    val lessonRepository = LessonRepository.getInstance()

    var coursesFromSpinner: AppCompatSpinner? = null
    var lessonTitleEditText: AppCompatEditText? = null
    var createLessonButton: Button? = null

    var currentLesson: Lesson? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_lesson)
        initializeUI()
        retrieveData()
    }

    fun initializeUI() {
        coursesFromSpinner = findViewById(R.id.courses_spinner)
        lessonTitleEditText = findViewById(R.id.lesson_title_et)
        createLessonButton = findViewById(R.id.create_lesson_button)
    }

    fun retrieveData() {

    }

    fun setListener() {
        createLessonButton?.setOnClickListener {

        }
    }

    fun createNewLesson() {
        lessonRepository.createLesson(
            currentLesson,
            object : OnPostItemCallback<Lesson> {
                override fun onSuccess(item: Lesson?) {
                    currentLesson = item
                }

                override fun onError() {
                    TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
                }

            }
        )
    }



    private fun populateCurrentLesson() {
        currentLesson = Lesson()
        currentLesson?._class = CLASS
        currentLesson?.id_course = ""
        currentLesson?.id_student
    }

}
