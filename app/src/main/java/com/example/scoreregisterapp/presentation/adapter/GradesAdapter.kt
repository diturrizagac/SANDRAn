package com.example.scoreregisterapp.presentation.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.domain.model.GradeData

class GradesAdapter(private val context: Context, private val gradesList: List<GradeData>) :
    RecyclerView.Adapter<GradesAdapter.GradeViewHolder>() {

    var mOnItemClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GradeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_list, parent, false)
        return GradeViewHolder(itemView)
    }

    override fun getItemCount(): Int {
        return gradesList.size
    }

    override fun onBindViewHolder(holder: GradeViewHolder, position: Int) {
        val item : GradeData = gradesList[position]
        holder.bind(item)
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        mOnItemClickListener = itemClickListener
    }

    inner class GradeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var courseNameTv: TextView = view.findViewById(R.id.item_list_title)
        private var finalGrade: TextView = view.findViewById(R.id.item_list_subtitle)
        private var partialExam: TextView = view.findViewById(R.id.item_list_subtitle_2)
        private var finalExam: TextView = view.findViewById(R.id.item_list_subtitle_3)
        private var auxiliarlExam: TextView = view.findViewById(R.id.item_list_subtitle_4)

        fun bind(item: GradeData) {
            courseNameTv.text = item.course_name
            finalGrade.text = item.final_grade
            partialExam.text = item.partial_exam
            finalExam.text = item.final_exam
            auxiliarlExam.text = item.auxiliar_exam
        }

        init {
            view.tag = this
        }
    }

}