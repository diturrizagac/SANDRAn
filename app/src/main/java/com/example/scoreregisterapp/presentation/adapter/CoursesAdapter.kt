package com.example.scoreregisterapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.domain.entities.Course

class CoursesAdapter(private val context: Context, private val optionList: List<Course>) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    var mOnItemClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_course, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        mOnItemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item : Course = optionList[position]
        holder.bind(item)
    }

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(item: Course) {

        }
    }
}