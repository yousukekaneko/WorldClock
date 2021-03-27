package com.example.android.sample.worldclock

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import java.util.*

class TimeZoomAdapter(private val context: Context,
                      private val timeZones: Array<String> = TimeZone.getAvailableIDs()
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: inflater.inflate(R.layout.list_time_zone_row, parent, false)

        // positionから表示すべきタイムゾーンのIDを得る
        val timeZoneId = getItem(position)

        // timeZoneIdからタイムゾーンを得る
        val timeZone = TimeZone.getTimeZone(timeZoneId)



        return view
    }

    // positionで指定されたインデックスにあるデータを返す
    override fun getItem(position: Int) = timeZones[position]

    // 行の一意性を保証するためのIDをLong値で返す
    override fun getItemId(position: Int) = position.toLong()

    // リスト表示するデータの件数をIntで返す
    override fun getCount() = timeZones.size
}