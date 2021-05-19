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
import com.dreamsoft.tableview.gridview_diary.models.DiaryData
import com.dreamsoft.tableview.gridview_diary.models.Events


class DiaryGridAdapter(var arList: Map<String, List<Events>>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null

    /* Time data for first column */
    private val arrTimes = arrayListOf(
        "6.00",
        "7.00",
        "8.00",
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

    /* Engineer ids and events related to 3 engineers in list array */
    var keysEngineer: List<String>
    var event1: List<Events>
    var event2: List<Events>
    var event3: List<Events>

    init {
        DiaryData.setTimeData()
        keysEngineer = DiaryData.getKeys(arList)
        Log.e("insdieee", ": $keysEngineer")
        event1 = arList[keysEngineer[0]]!!
        event2 = arList[keysEngineer[1]]!!
        event3 = arList[keysEngineer[2]]!!


        /* Get the start time and end time for each events ---- This is just for checking */
        event1.forEach {
            val startTime = it.startDate.split(" ").toTypedArray()[1]
            val endTime = it.endDate.split(" ").toTypedArray()[1]

            Log.e("event 1 ", "startt  : "+startTime )
        }

        event2.forEach {
            val startTime = it.startDate.split(" ").toTypedArray()[1]
            val endTime = it.endDate.split(" ").toTypedArray()[1]

            Log.e("event 2 ", "startt  : " +startTime )
        }

        event3.forEach {
            val startTime = it.startDate.split(" ").toTypedArray()[1]
            val endTime = it.endDate.split(" ").toTypedArray()[1]

            Log.e("event 3 ", "startt  : "+startTime )
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context

        return ViewHolder2(
            LayoutInflater.from(parent.context).inflate(R.layout.item_table_row, parent, false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        holder.setIsRecyclable(false)

        val viewHolder2: ViewHolder2 = holder as ViewHolder2
        viewHolder2.tv_time?.text = arrTimes[position]

        /* The three events will be looped to check whether the engineer has any event for that particular time*/
        event1.forEach {

            val startTime = it.startDate.split(" ").toTypedArray()[1]
            val endTime = it.endDate.split(" ").toTypedArray()[1]

            if (startTime == DiaryData.arrDateTime[position]) {
               // Log.e("event 1:", "onBindViewHolder: event 3 $startTime   array time DiaryData.arrDateTime[position]" )

                //addEvent(viewHolder2, viewHolder2.ll_col_1!!)
                addEvent(viewHolder2, viewHolder2.ll_col_1!!, event = it)
            }
        }

        event2.forEach {

            val startTime = it.startDate.split(" ").toTypedArray()[1]
            val endTime = it.endDate.split(" ").toTypedArray()[1]

          //  viewHolder2.ll_col_2!!.removeAllViews()

            if (startTime == DiaryData.arrDateTime[position]) {
               // Log.e("event 2:", "onBindViewHolder: event 3 $startTime   array time DiaryData.arrDateTime[position]" )

                addEvent(viewHolder2, viewHolder2.ll_col_2!!, event = it)
            }
        }

        event3.forEach {
            val startTime = it.startDate.split(" ").toTypedArray()[1]
            val endTime = it.endDate.split(" ").toTypedArray()[1]

           // viewHolder2.ll_col_3!!.removeAllViews()

            if (startTime == DiaryData.arrDateTime[position]) {
                Log.e("event 3:", "onBindViewHolder: event 3 $startTime   array time ${DiaryData.arrDateTime[position]}" )

                addEvent(viewHolder2, viewHolder2.ll_col_3!!, event = it)
            }
        }
    }

    /* Method to inflate the event in that particular column */

    private fun addEvent(viewHolder2: ViewHolder2, linearLayout: LinearLayout, event: Events) {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        var jobView: View? = null

        when(event.jobEventType) {
            1 -> {
               jobView  = inflater.inflate(R.layout.item_job_1, null);
            }

            2 -> {
                jobView = inflater.inflate(R.layout.item_job_2, null);
            }

            3 -> {
                jobView = inflater.inflate(R.layout.item_job_3, null);
            }
        }

        val tv_text = jobView?.findViewById<TextView>(R.id.tv_text)
        val tv_subtext = jobView?.findViewById<TextView>(R.id.tv_subtext)
        val tv_address = jobView?.findViewById<TextView>(R.id.tv_address)

        tv_text?.text = event.title
        tv_subtext?.text = "Engineer Id : "+event.engineer_id
        tv_address?.text = event.location

        linearLayout.addView(jobView!!)
    }

    override fun getItemCount(): Int {
        return arrTimes.size
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
