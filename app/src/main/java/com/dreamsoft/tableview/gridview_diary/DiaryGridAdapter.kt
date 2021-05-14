package com.dreamsoft.tableview.gridview_diary

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dreamsoft.tableview.R


class DiaryGridAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (viewType) {
            1 -> ViewHolder1(
                LayoutInflater.from(parent.context).inflate(R.layout.item_grid_blank, parent, false)
            )
            2 -> ViewHolder2(
                LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
            )
            else -> {
                ViewHolder1(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_job, parent, false)
                )
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            1 -> {
                val viewHolder1: ViewHolder1 = holder as ViewHolder1

            }
            2 -> {
                val viewHolder2: ViewHolder2 = holder as ViewHolder2

            }
            else -> {

            }
        }
    }

    override fun getItemCount(): Int {
        return 10
    }

    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var v = itemView

        init {

        }
    }


    class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var v = itemView

        init {

        }


    }

}
