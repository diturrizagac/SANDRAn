package com.example.scoreregisterapp.presentation.adapter

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

import com.example.scoreregisterapp.R
import com.example.scoreregisterapp.domain.model.Home


class HomeAdapter(private val context: Context, private val optionList: List<Home>) :
    RecyclerView.Adapter<HomeAdapter.HomeViewHolder>() {

    private var myPos = 0
    var mOnItemClickListener : View.OnClickListener? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.raw_home, parent, false)
        return HomeViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: HomeViewHolder, position: Int) {
        val item : Home = optionList[position]

        holder.bind(item)

        /*if (myPos == position) {
            holder.title.setTextColor(Color.parseColor("#000000"))
            holder.linear.setBackgroundResource(R.drawable.ic_selector_1)
        } else {
            holder.title.setTextColor(Color.parseColor("#484646"))
            holder.linear.setBackgroundResource(R.drawable.ic_selector_2)
        }*/

        /*holder.itemView.setOnClickListener {
            myPos = position
            notifyDataSetChanged()
        }*/

    }

    override fun getItemCount(): Int {
        return optionList.size
    }

    fun setOnItemClickListener(itemClickListener: View.OnClickListener) {
        mOnItemClickListener = itemClickListener
    }

    inner class HomeViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        private var image: ImageView = view.findViewById(R.id.image) as ImageView
        private var title: TextView = view.findViewById(R.id.title) as TextView
        private var linear: LinearLayout = view.findViewById(R.id.linear) as LinearLayout

        fun bind(item: Home) {
            image.setImageResource(item.image!!)
            title.text = item.title
        }

        init {
            view.tag = this
            view.setOnClickListener(mOnItemClickListener)
        }


    }
}


