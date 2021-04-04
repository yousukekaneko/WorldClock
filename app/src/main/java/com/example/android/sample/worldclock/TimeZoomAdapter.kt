package com.example.android.sample.worldclock

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextClock
import android.widget.TextView
import java.util.*
import kotlinx.android.synthetic.main.list_time_zone_row.view.*

class TimeZoomAdapter(
    context: Context,
    private val timeZones: Array<String> = TimeZone.getAvailableIDs()
) : BaseAdapter() {

    private val inflater = LayoutInflater.from(context)
    
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: createView(parent)

        // positionから表示すべきタイムゾーンのIDを得る
        val timeZoneId = getItem(position)

        // timeZoneIdからタイムゾーンを得る
        val getTimeZone = TimeZone.getTimeZone(timeZoneId)

        // ViewHolderとCreateViewを使う前
//        // タイムゾーン名のテキストビュー
//        val timeZoneLabel = view.findViewById<TextView>(R.id.timeZone)
//        timeZoneLabel.text = "${getTimeZone.displayName}(${getTimeZone.id})"
//
//        // 時刻を表示するテキストクロック
//        val clock = view.findViewById<TextClock>(R.id.textClock2)
//        clock.timeZone = getTimeZone.id

        // tagからViewHolderを取得
        val viewHolder = view.tag as ViewHolder

        // timeZone名をセット
        @SuppressLint("SetTextI18n")
        viewHolder.name.text = "${getTimeZone.displayName}(${getTimeZone.id})"

        // timeZoneをセット
        viewHolder.clock.text = getTimeZone.id
        return view
    }

    // positionで指定されたインデックスにあるデータを返す
    override fun getItem(position: Int) = timeZones[position]

    // 行の一意性を保証するためのIDをLong値で返す
    override fun getItemId(position: Int) = position.toLong()

    // リスト表示するデータの件数をIntで返す
    override fun getCount() = timeZones.size

    // 各行のViewへの参照を持っておくことで、毎回findViewByIdをすることを避ける
    private class ViewHolder(view: View) {
        val name = view.findViewById<TextView>(R.id.timeZone)
        val clock = view.findViewById<TextClock>(R.id.textClock2)
    }

    private fun createView(parent: ViewGroup?): View {
        val view = inflater.inflate(R.layout.list_time_zone_row, parent, false)
        view.tag = ViewHolder(view)
        return view
    }
}