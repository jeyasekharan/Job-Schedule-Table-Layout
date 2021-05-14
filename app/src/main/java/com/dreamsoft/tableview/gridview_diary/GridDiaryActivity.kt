package com.dreamsoft.tableview.gridview_diary

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.dreamsoft.tableview.R
import kotlinx.android.synthetic.main.activity_grid_view_diary.*


class GridDiaryActivity: AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_grid_view_diary)

        setGridAdapter()
    }

    private fun setGridAdapter() {
        val gridDiaryAdapter = DiaryGridAdapter()

        gridview_diary.layoutManager = GridLayoutManager(this, 2)
        gridview_diary.adapter = gridDiaryAdapter
    }
}