package com.dreamsoft.tableview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_table_row.view.*

class TableProgActivity : AppCompatActivity() {

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

    val timeMap: HashMap<String, String> = HashMap()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activty_diary_custom)

        initializeTime()
        addHeading()

        addAllRows()

        println(add(5, 12))
    }

    private fun initializeTime() {

    }

    private fun addAllRows() {
        for (i in 0 until 10) {
            addSingleRow(index = i)
        }
        addJob(index = 4, columnIndex = 2)

        addBlankJob(index = 4, columnIndex = 3)
    }


    private fun addBlankJob(index: Int, columnIndex: Int) {
        println("the index $index")

        val inflater: LayoutInflater = LayoutInflater.from(this);
        val blankView: View = inflater.inflate(R.layout.item_table_blank_grey, null);

        val row: TableRow = table_diary.getChildAt(index) as TableRow
        val view = row.getChildAt(columnIndex) as LinearLayout
        println("the blank index $view")

        view.addView(blankView)
    }

    private fun add(a: Int, b: Int): Int = a + b


    private fun addHeading() {
        val tableRow = LayoutInflater.from(applicationContext)
            .inflate(R.layout.item_table_head, null) as TableRow

        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT, 1.0f
        )
        table_diary.addView(tableRow)

        Glide.with(this).load(R.drawable.ic_profile_1).circleCrop().into(iv_profile_1)
        Glide.with(this).load(R.drawable.ic_profile_2).circleCrop().into(iv_profile_2)
        Glide.with(this).load(R.drawable.ic_profile_3).circleCrop().into(iv_profile_3)
    }

    private fun addSingleRow(index: Int) {
        println("the index $index")
        val tableRow = LayoutInflater.from(applicationContext)
            .inflate(R.layout.item_table_row, null) as TableRow

        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT, 1.0f
        )
        table_diary.addView(tableRow)

        tableRow.tv_time.text = arrTimes[index]
    }


    private fun addJob(index: Int, columnIndex: Int) {
        val inflater: LayoutInflater = LayoutInflater.from(this);
        val jobView: View = inflater.inflate(R.layout.item_job_1, null);

        val row6: TableRow = table_diary.getChildAt(index) as TableRow
        val view6 = row6.getChildAt(2) as LinearLayout
        view6.addView(jobView)
    }
}