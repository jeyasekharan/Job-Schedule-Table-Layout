package com.dreamsoft.tableview.gridview_diary

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.bumptech.glide.Glide
import com.dreamsoft.tableview.R
import com.dreamsoft.tableview.gridview_diary.models.EventData
import com.dreamsoft.tableview.gridview_diary.models.Events
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.android.synthetic.main.activity_grid_view_diary.*
import org.json.JSONArray
import java.lang.reflect.Type
import kotlin.collections.HashMap
import kotlin.collections.HashSet


class GridDiaryActivity : AppCompatActivity() {

    var jsonArray: JSONArray? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view_diary)

        getData()
        setClickListeners()
    }

    private fun setClickListeners() {
        iv_left_arrow.setOnClickListener {

        }

        iv_right_arrow.setOnClickListener {

        }

        iv_left_arrow_users.setOnClickListener {

        }

        iv_right_arrow_users.setOnClickListener {

        }
    }

    private fun getData() {

        var hashEvents: HashSet<Events>? = null
        var hashMapEvents: HashMap<String, Events>? = null

        val listType: Type = object : TypeToken<List<Events?>?>() {}.type
        val arList = Gson().fromJson<List<Events>>(EventData.data, listType)

        val totalEvents = arList.size

        val maps = arList.groupBy { it.engineer_id }

        Log.e("tagg", "getData: "+ maps )

        val engineerIds = maps.keys

        setGridAdapter(maps)

        arList.let {
            val date = changeDateFormat(it[0].startDate)
            tv_date.text = date
        }


     /*   val setOfThree = HashMap<String, List<Events>>()

        for (i in 0 until engineerIds.size step 3) {

            var setIndex = 0

            for (j in 0..i) {

                Log.e("data  type  ", "setGridAdapter: " + engineerIds.elementAt(setIndex))

                setOfThree.put(setIndex.toString(), maps.get(engineerIds.elementAt(j))!!)
                setIndex++
            }
        }

        Log.e("set of threee  ", "setGridAdapter: " + setOfThree)

        //Set Date for the index


        setGridAdapter(setOfThree["0"]!!)*/
        // val weatherList: List<Events> = Gson().fromJson(EventData.data , Array<Events>::class.java).toList()
    }

    private fun setGridAdapter(arList: Map<String, List<Events>>) {
        var arList2: Map.Entry<String, List<Events>>
        var nameIndex = 0

        arList.forEach lit@{
            Log.e("name Index ", "setGridAdapter: " + it.component2()[0].color_string)

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

        val gridDiaryAdapter = DiaryGridAdapter(arList)

        gridview_diary.layoutManager = GridLayoutManager(this, 1)
        gridview_diary.adapter = gridDiaryAdapter
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

