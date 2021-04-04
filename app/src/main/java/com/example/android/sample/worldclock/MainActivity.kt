package com.example.android.sample.worldclock

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    val REQUEST_CODE = 1
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 標準時間を表示
        val defaultTimeZone = TimeZone.getDefault()
        timeZone.text = defaultTimeZone.displayName

        addButton.setOnClickListener {
            val intent = Intent(this, TimeZoneSelectActivity::class.java)
            // 遷移先画面から結果を受け取る画面遷移ができる
            startActivityForResult(intent, REQUEST_CODE)
        }

        // 世界時計のリストを表示する
        showWorldClock()
    }

    private fun showWorldClock() {
        // プリファレンスから保存しているタイムゾーンを得る
        val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
        val timeZones = pref.getStringSet("time_zone", setOf())

        if (timeZones != null) {
            clockList.adapter = TimeZoomAdapter(this, timeZones.toTypedArray())
        }
    }

    @SuppressLint("MissingSuperCall")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == 1 // リクエストコードが一致している
            && resultCode == Activity.RESULT_OK // 結果がRESULT_OKである
            && data != null) { // dataがnullではない
            // 受け取ったデータから、タイムゾーンを得る
            val timeZone = data.getStringExtra("timeZone")

            // プリファレンスから、保存しているタイムゾーンを得る
            val pref = getSharedPreferences("pref", Context.MODE_PRIVATE)
            val timeZones = pref.getStringSet("time_zone", mutableSetOf())

            // 保存していたタイムゾーン一覧に追加
            timeZones?.add(timeZone)

            // プリファレンスに保存する
            pref.edit().putStringSet("time_zone", timeZones).apply()

            // リストを再表示する
            showWorldClock()
        }
    }
}