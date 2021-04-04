package com.example.android.sample.worldclock

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class TimeZoneSelectActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // タイムゾーン選択画面のレイアウトを指定する
        setContentView(R.layout.activity_time_zone_select)

        // 最初に「キャンセルされた」結果を返すように設定しておくと、
        // 戻るボタンをタップした時などに対応できる
        setResult(Activity.RESULT_CANCELED)

        // タイムゾーンリストをレイアウトから探す
        val adapter = TimeZoomAdapter(this)
        // リストにアダプターをセットする
        clockList.adapter = adapter

        // リストタップ時の動作
        clockList.setOnItemClickListener { _, _, position, _ ->
            // タップした場所のタイムゾーンをリストから得る
            val timeZone = adapter.getItem(position)

            // 遷移元の画面に結果を返す結果
            val result = Intent()
            // ユーザーがタップしたタイムゾーンを設定する
            result.putExtra("timeZone", timeZone)
            // OKの結果を返す
            setResult(Activity.RESULT_OK, result)

            // この画面を閉じる
            finish()
        }
    }
}