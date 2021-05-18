package com.dreamsoft.tableview.gridview_diary.models

class DiaryData {

    companion object {
        var timeHash = HashMap<String, String>()

        val arrDateTime = arrayListOf<String>(
            "06:00:00.0",
            "07:00:00.0",
            "08:00:00.0",
            "09:00:00.0",
            "10:00:00.0",
            "11:00:00.0",
            "12:00:00.0",
            "13:00:00.0",
            "14:00:00.0",
            "15:00:00.0",
            "16:00:00.0",
            "17:00:00.0",
            "18:00:00.0",
            "19:00:00.0",
            "20:00:00.0"
        )

        fun setTimeData() {
            arrDateTime.forEachIndexed { index, s ->
                timeHash[s] = index.toString()
            }
        }

        fun getKeys(arList: Map<String, List<Events>>): List<String> {
            val arrayKeys = arList.keys.take(3)
            return arrayKeys
        }
    }
}