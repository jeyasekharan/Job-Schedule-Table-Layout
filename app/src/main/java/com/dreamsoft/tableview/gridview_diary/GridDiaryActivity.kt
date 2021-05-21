package com.dreamsoft.tableview.gridview_diary

import android.content.res.Configuration
import android.os.Bundle
import android.util.Log
import android.view.OrientationEventListener
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dreamsoft.tableview.R
import com.dreamsoft.tableview.gridview_diary.models.DiaryData
import com.dreamsoft.tableview.gridview_diary.models.EventData
import com.dreamsoft.tableview.gridview_diary.models.Events
import com.dreamsoft.tableview.gridview_diary.models.UserDetails
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_grid_view_diary.*
import org.json.JSONArray
import java.lang.reflect.Type


/* This is diary view with gridview  */

class GridDiaryActivity : AppCompatActivity() {

    var jsonArray: JSONArray? = null
    var usersListIndex = 0
    lateinit var arrFiveGroupsArrayKeys: List<List<String>>
    lateinit var keysEngineer: List<String>

    var myOrientationEventListener: OrientationEventListener? = null

    /* Grouped Events based on Engineer ids*/
    var eventGroupedOnEngineerIDs: Map<String, List<Events>>? = null

    private lateinit var gridDiaryAdapter: DiaryGridAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view_diary)

        getData()
        setClickListeners()
        //getInitialOrientation()
    }

    private fun getInitialOrientation() {
        if (resources.configuration.orientation == Configuration.ORIENTATION_LANDSCAPE) {

            if (this::gridDiaryAdapter.isInitialized) {
                gridDiaryAdapter.setOrientation(Configuration.ORIENTATION_LANDSCAPE)
            }

        } else {
            if (this::gridDiaryAdapter.isInitialized) {
                gridDiaryAdapter.setOrientation(Configuration.ORIENTATION_PORTRAIT)
            }
        }
    }


    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)
        val newOrientation: Int = newConfig.orientation

        if (newOrientation == Configuration.ORIENTATION_LANDSCAPE) {

        } else {

        }
    }

    private fun setClickListeners() {
        iv_left_arrow.setOnClickListener {


        }

        iv_right_arrow.setOnClickListener {

        }

        iv_left_arrow_users.setOnClickListener {

            if (usersListIndex > 0) {
                usersListIndex -= 1

                keysEngineer = DiaryData.getKeyIndex(arrFiveGroupsArrayKeys, usersListIndex)

                setEngineerColumnNames(keysEngineer)

                eventGroupedOnEngineerIDs?.let {
                    val eventsData = getFiveEvents(it, keysEngineer)
                    gridDiaryAdapter.setFiveEventData(eventsData)
                }
            }

        }

        iv_right_arrow_users.setOnClickListener {
            if (usersListIndex < arrFiveGroupsArrayKeys.size -1) {
                usersListIndex += 1

                keysEngineer = DiaryData.getKeyIndex(arrFiveGroupsArrayKeys, usersListIndex)

                setEngineerColumnNames(keysEngineer)

                eventGroupedOnEngineerIDs?.let {
                    val eventsData = getFiveEvents(it, keysEngineer)
                    gridDiaryAdapter.setFiveEventData(eventsData)
                }
            }

        }
    }

    private fun getData() {

        /* Convert string data to Class  */
        val listType: Type = object : TypeToken<List<Events?>?>() {}.type
        val arList = Gson().fromJson<List<Events>>(EventData.data, listType)

        val totalEvents = arList.size

        /* Grouping events by engineers id  */
        eventGroupedOnEngineerIDs = arList.groupBy { it.engineer_id }

        Log.e("tagg", "getData: "+ eventGroupedOnEngineerIDs )

        /* Set adapter */
         eventGroupedOnEngineerIDs?.let {
             setGridAdapter(it)
         }

        /* Select date from event and set date in top heading */
        arList.let {
            val date = changeDateFormat(it[0].startDate)
            tv_date.text = date
        }
    }

    private fun setGridAdapter(arList: Map<String, List<Events>>) {

        var arList2: Map.Entry<String, List<Events>>
        var nameIndex = 0

        /* This code will fetch 3 engineers name and place it in heading */
        arList.forEach lit@{
            Log.e("name Index ", "setGridAdapter: " + it)

            if(nameIndex == 3) {
                return@lit
            }
            arList2 = it

            Log.e("name Index ", "setGridAdapter: " + it.component2()[0].username)

            when(nameIndex) {
                1 -> {
                    tv_name_1.text = it.value[0].username
                }

                2 -> {
                    tv_name_1.text = it.value[0].username
                }

                3 -> {
                    tv_name_1.text = it.value[0].username
                }
            }
            nameIndex++
        }

        /* Take list of 5 engineers id from data */

        arrFiveGroupsArrayKeys = DiaryData.splitInto5Keys(arList)
        Log.e("group of keys  ", "setGridAdapter: "+ arrFiveGroupsArrayKeys )

        /* Engineer keys are grouped into five each*/
        keysEngineer = DiaryData.getKeyIndex(arrFiveGroupsArrayKeys, usersListIndex)

        setEngineerColumnNames(keysEngineer)

        Log.e("taggg   ", "setGridAdapter: " + arList + "   "+ keysEngineer)
        val eventsData = getFiveEvents(arList, keysEngineer)

        gridDiaryAdapter = DiaryGridAdapter(eventsData)
        gridDiaryAdapter.setFiveEventData(eventsData)

        gridview_diary.layoutManager = GridLayoutManager(this, 1)
        gridview_diary.adapter = gridDiaryAdapter
    }


    private fun setEngineerColumnNames(keysEngineer: List<String>) {
        val users = UserDetails.getModelFromJson()

        val tvArrays = arrayOf(tv_name_1, tv_name_2, tv_name_3, tv_name_4, tv_name_5)

        keysEngineer.forEachIndexed { index, key ->
            val user = users?.first { it.user_id.toString() == key }
            tvArrays[index].text = user?.firstname + user?.surname
        }

    }

    /* To get no of events depending upon keys */
    private fun getFiveEvents(arList: Map<String, List<Events>>, keysEngineer: List<String>) :ArrayList<List<Events>> {
        var eventsBasedOnEngineers : ArrayList<List<Events>> = ArrayList()

        Log.e("atggg", "getFiveEvents: $keysEngineer   ${keysEngineer.indices}, ")
        for (i in keysEngineer.indices) {
            eventsBasedOnEngineers.add(arList[keysEngineer[i]]!!)
        }

        return eventsBasedOnEngineers

    }


    fun changeDateFormat(date: String): String {
        val strs = date.split(" ").toTypedArray()
        return strs[0]
        /* val originalFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)
         val targetFormat: DateFormat = SimpleDateFormat("yyyy-MM-dd")
         val date: Date = originalFormat.parse(strs[0])
         val formattedDate: String = targetFormat.format(date) // 2012082*/
    }
}

