package com.dreamsoft.tableview.gridview_diary

import android.content.Context
import android.content.res.Configuration
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.dreamsoft.tableview.R
import com.dreamsoft.tableview.gridview_diary.models.DiaryData
import com.dreamsoft.tableview.gridview_diary.models.EventData
import com.dreamsoft.tableview.gridview_diary.models.Events


class DiaryGridAdapter(var alFiveEngineersEvents: ArrayList<List<Events>>) :

    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var context: Context? = null

    init {
        /* Events related to 5 or less Engineers*/

        for (i in alFiveEngineersEvents.indices) {

        }
    }

    /* Gets events of five engineers from activity when users changes to another activity */

    fun setFiveEventData(alFiveEngineersEvents: ArrayList<List<Events>>) {
        this.alFiveEngineersEvents = alFiveEngineersEvents
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        context = parent.context
        return ViewHolder2(
            LayoutInflater.from(parent.context).inflate(R.layout.item_table_row, parent, false),
            alFiveEngineersEvents.size
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        holder.setIsRecyclable(false)

        val viewHolder2: ViewHolder2 = holder as ViewHolder2

        /* Inflating blank view */
        inflateBlankViews()

        viewHolder2.tv_time?.text = EventData.arrTimes[position]

        bindEvents(position, viewHolder2)
    }

    private fun inflateBlankViews() {

    }

    /* Method to inflate the event in that particular column */

    private fun addEvent(linearLayout: LinearLayout, event: Events) {

        val inflater: LayoutInflater = LayoutInflater.from(context)
        var jobView: View? = null

        when (event.jobEventType) {
            1 -> {
                jobView = inflater.inflate(R.layout.item_job_1, null);
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
        tv_subtext?.text = "Engineer Id : " + event.engineer_id
        tv_address?.text = event.location

        linearLayout.addView(jobView!!)
    }

    override fun getItemCount(): Int {
        return EventData.arrTimes.size
    }

    class ViewHolder2(itemView: View, columnSize: Int) : RecyclerView.ViewHolder(itemView) {

        private var v = itemView

        var ll_col_1: LinearLayout? = null
        var ll_col_2: LinearLayout? = null
        var ll_col_3: LinearLayout? = null
        var ll_col_4: LinearLayout? = null
        var ll_col_5: LinearLayout? = null

        var tv_time: TextView? = null

        var ll_arrays: Array<LinearLayout?>

        init {
            tv_time = v.findViewById(R.id.tv_time)
            ll_col_1 = v.findViewById(R.id.ll_col_1)
            ll_col_2 = v.findViewById(R.id.ll_col_2)
            ll_col_3 = v.findViewById(R.id.ll_col_3)
            ll_col_4 = v.findViewById(R.id.ll_col_4)
            ll_col_5 = v.findViewById(R.id.ll_col_5)

            ll_arrays = arrayOf(ll_col_1, ll_col_2, ll_col_3, ll_col_4, ll_col_5)

            ll_col_1?.visibility = View.GONE
            ll_col_2?.visibility = View.GONE
            ll_col_3?.visibility = View.GONE
            ll_col_4?.visibility = View.GONE
            ll_col_5?.visibility = View.GONE

            for (i in 0 until columnSize) {
                ll_arrays[i]?.visibility = View.VISIBLE
            }
        }
    }

    var endTimeExtArr = arrayOfNulls<EndTimeModel>(5)


    private fun bindEvents(position: Int, viewHolder2: ViewHolder2) {

        /* For a single row */

        /* The three events will be looped to check whether the engineer has any event for that particular time */

        for (i in alFiveEngineersEvents.indices) {

//          removeAllView(viewHolder2.ll_arrays[i]!!)

            viewHolder2.ll_arrays[i]?.visibility = View.VISIBLE

            /* Check for previous events*/
            loadEndTime(i, viewHolder2)

            /* Loop through all events and check if an event start time is equal to layout time */
            for (event in alFiveEngineersEvents[i]) {

                /* Split date and time into two components */

                val startTime = event.startDate.split(" ").toTypedArray()[1]
                val endTime = event.endDate.split(" ").toTypedArray()[1]

                /* Checks with corresponding time position for the linear layouts */

                if (startTime == DiaryData.arrDateTime[position]) {

                    // Log.e("event 1:", "onBindViewHolder: event 3 $startTime   array time DiaryData.arrDateTime[position]" )
                    addEvent(viewHolder2.ll_arrays[i]!!, event = event)

                    /* Check for end time events */
                    checkSaveEndTime(startTime, endTime, i)

                }
            }
        }

        Log.e(" starting herer ", " ***************")

        endTimeExtArr.forEachIndexed { index, endTimeModel ->
            Log.e(" full end time $index", " ${endTimeModel}")
        }

        Log.e(" ending here ", " ***************")
    }

    private fun loadEndTime(i: Int, viewHolder2: ViewHolder2) {

        /* Check for previous time events */
        if (endTimeExtArr[i] != null) {

            Log.e(" index  $i ", " ${endTimeExtArr[i]}")
            addPreviousBlankEvent(viewHolder2.ll_arrays[i]!!, "full")

            var endHour = endTimeExtArr[i]?.hour
            endHour?.let {
                endHour -= 1
                endTimeExtArr[i]?.hour = endHour
            }

            if (endTimeExtArr[i]?.hour == 0) {
                endTimeExtArr[i] = null
            }
        }
    }

    private fun checkSaveEndTime(startTime: String, endTime: String, i: Int) {

        val endTimeHour = endTime.substring(0, 2)
        val endTimeMinutes = endTime.substring(3, 5)

        val startTimeHour = startTime.substring(0, 2)
        val startTimeMinutes = startTime.substring(3, 5)


        if (endTimeHour.toInt() > startTimeHour.toInt() + 1) {
            val timeDiff = endTimeHour.toInt() - startTimeHour.toInt()

            endTimeExtArr[i] = EndTimeModel(hour = timeDiff, minutes = endTimeMinutes.toInt())
        }
    }

    private fun addPreviousBlankEvent(linearLayout: LinearLayout, fullOrHalf: String) {
        Log.e("add blank", " adding blank even")
        val inflater: LayoutInflater = LayoutInflater.from(context)
        var jobView: View? = null
        jobView = inflater.inflate(R.layout.item_table_blank_grey, null);

        linearLayout.addView(jobView!!)
    }

    private fun removeAllView(linearLayout: LinearLayout) {
        linearLayout.removeAllViews()
    }


    fun setOrientation(orientationLandscape: Int) {
        if (orientationLandscape == Configuration.ORIENTATION_LANDSCAPE) {
            notifyDataSetChanged()
        } else {
            notifyDataSetChanged()
        }
    }
}

data class EndTimeModel(var hour: Int, var minutes: Int)
