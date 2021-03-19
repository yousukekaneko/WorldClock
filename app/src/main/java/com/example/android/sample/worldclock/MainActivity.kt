package com.example.android.sample.worldclock

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
    }
}