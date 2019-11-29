package com.example.scoreregisterapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.domain.entities.Course
import com.example.scoreregisterapp.domain.model.CourseData

class CoursesAdapter(private val context: Context, private val optionList: List<CourseData>) :
    RecyclerView.Adapter<CoursesAdapter.CourseViewHolder>() {

    var mOnItemClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CourseViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_item_cardview, parent, false)
        return CourseViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        mOnItemClickListener = itemClickListener
    }

    override fun onBindViewHolder(holder: CourseViewHolder, position: Int) {
        val item : CourseData = optionList[position]
        holder.bind(item)
    }

    inner class CourseViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var image: ImageView = view.findViewById(R.id.item_image) as ImageView
        private var title: TextView = view.findViewById(R.id.item_title) as TextView
        fun bind(item: CourseData) {
            image.setImageResource(item.image!!)
            title.text = item.title
        }

        init {
            view.tag = this
        }
    }

    fun parseCourseToCourseData(){

    }
}