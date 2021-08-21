package com.zsuuu.quickmeapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_fifth.*

class FifthActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_fifth)
//        返回上一级按钮
        button_backfifth.setOnClickListener {
            finish()
        }
//        导入图片按钮
        button_backfifth.setOnClickListener {
            finish()
        }
 //       加密图片图片按钮
        button_backfifth.setOnClickListener {
            finish()
        }
 //       导出发送按钮
        button_backfifth.setOnClickListener {
            finish()
        }
    }


}