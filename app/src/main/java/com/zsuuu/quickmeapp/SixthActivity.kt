package com.zsuuu.quickmeapp

import android.app.Activity
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.FileProvider
import kotlinx.android.synthetic.main.activity_main.*
import java.io.File
import android.provider.MediaStore
import android.content.Intent
import android.graphics.BitmapFactory
import android.graphics.Bitmap
import android.graphics.Matrix
import android.media.ExifInterface
import kotlinx.android.synthetic.main.activity_fifth.*

class SixthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sixth)

        //        返回上一级按钮
        button_backfifth.setOnClickListener {
            finish()
        }

    }
}