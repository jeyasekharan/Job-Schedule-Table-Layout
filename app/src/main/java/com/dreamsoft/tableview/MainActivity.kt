package com.dreamsoft.tableview

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TableLayout
import android.widget.TableRow
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    val ROWS = 10
    val times = arrayOf("9.00", "10.00", "10.00", "10.00", "10.00", "10.00", "10.00", "10.00", "10.00")
    val timeTextDash = "|\n|\n|\n|\n|\n|\n|"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initViews()
        //createTable(rows=ROWS)
        createTimeColumn(no = 10)
        addRow()
    }

    private fun addRow() {
        val tableRow = LayoutInflater.from(applicationContext).inflate(R.layout.item_table_row, null) as TableRow

        tableRow.layoutParams = TableLayout.LayoutParams(
            TableLayout.LayoutParams.MATCH_PARENT,
            TableLayout.LayoutParams.WRAP_CONTENT,1.0f
        )
        table_diary.addView(tableRow)
    }

    private fun createTable(rows: Int) {
        for (i in 4 until rows) {

        }
    }

    private fun createTimeColumn(no: Int) {
        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun initViews() {
        //ll_10_00_col_2
        val inflater: LayoutInflater = LayoutInflater.from(this);
        val view: View = inflater.inflate(R.layout.item_job, null);
        val view6_job: View = inflater.inflate(R.layout.item_job, null);
      //  ll_10_00_col_2.addView(view)

        val view2: View = inflater.inflate(R.layout.item_job, null);
        ll_11_00_col_1.addView(view2)


        Glide.with(this).load(R.drawable.ic_profile_1).circleCrop().into(iv_profile_1)
        Glide.with(this).load(R.drawable.ic_profile_2).circleCrop().into(iv_profile_2)
        Glide.with(this).load(R.drawable.ic_profile_3).circleCrop().into(iv_profile_3)


        val view3: View = inflater.inflate(R.layout.item_job, null);
        ll_11_00_col_1.addView(view3)

        val view4: View = inflater.inflate(R.layout.item_job, null);

        val row = TableRow(this)
        row.layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT)

        val txt = TextView(this)
        txt.apply {
            text = "12.00"+timeTextDash[2]
        }

   /*     val row0:TableRow = table_diary.getChildAt(4) as TableRow
        val view0 = row2.getChildAt(3) as LinearLayout
        view5.addView(view)


        val row2:TableRow = table_diary.getChildAt(3) as TableRow
        val view5 = row2.getChildAt(3) as LinearLayout
        view5.addView(view)*/


        val row6:TableRow = table_diary.getChildAt(5) as TableRow
        val view6 = row6.getChildAt(2) as LinearLayout
        view6.addView(view6_job)
        // row.addView(inflater.inflate(R.layout.item_job, null))
      //  row.addView(inflater.inflate(R.layout.item_job, null))
      //  row.addView(inflater.inflate(R.layout.item_job, null))

    }
}