package com.dreamsoft.tableview.gridview_diary

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamsoft.tableview.R
import com.dreamsoft.tableview.gridview_diary.models.Events
import kotlinx.android.synthetic.main.activity_main.*


class DiaryGridAdapter(var arList: Map<String, List<Events>>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null

    private val arrTimes = arrayListOf<String>(
        "9.00",
        "10.00",
        "11.00",
        "12.00",
        "13.00",
        "14.00",
        "15.00",
        "16.00",
        "17.00",
        "18.00",
        "19.00",
        "20.00"
    )


    init {


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return when (viewType) {
            1 -> ViewHolder1(
                LayoutInflater.from(parent.context).inflate(R.layout.item_grid_blank, parent, false)
            )
            2 -> ViewHolder2(
                LayoutInflater.from(parent.context).inflate(R.layout.item_table_row, parent, false)
            )
            else -> {
                ViewHolder2(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_table_row, parent, false)
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

                val inflater: LayoutInflater = LayoutInflater.from(context);
                val jobView: View = inflater.inflate(R.layout.item_job_1, null);

            }
            else -> {
                val viewHolder2: ViewHolder2 = holder as ViewHolder2

                viewHolder2.tv_time?.text = arrTimes[position]


        /*        val inflater: LayoutInflater = LayoutInflater.from(context);
                val jobView: View = inflater.inflate(R.layout.item_job_1, null);

                viewHolder2.ll_9_00_col_3?.addView(jobView)

                viewHolder2.tv_time?.text = arrTimes[position]*/
            }
        }
    }

    override fun getItemCount(): Int {
        return 12
    }

    class ViewHolder1(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var v = itemView

        init {

        }
    }


    class ViewHolder2(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private var v = itemView

        var ll_col_1: LinearLayout? = null
        var ll_col_2: LinearLayout? = null
        var ll_col_3: LinearLayout? = null

        var tv_time: TextView? = null

        init {
            tv_time = v.findViewById(R.id.tv_time)
            ll_col_1 = v.findViewById(R.id.ll_col_1)
            ll_col_2 = v.findViewById(R.id.ll_col_2)
            ll_col_3 = v.findViewById(R.id.ll_col_3)
        }
    }

}
