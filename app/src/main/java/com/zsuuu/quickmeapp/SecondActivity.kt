package com.zsuuu.quickmeapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import java.lang.StringBuilder
import java.math.BigInteger

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
//        记住上次公钥的功能
        val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
        val inputn =  prefs.getString("gongyao","kongdata")
        gongyaon.setText(inputn)
//        加密明文
        button_jiami.setOnClickListener {
            Toast.makeText(this, "加密明文中", Toast.LENGTH_SHORT).show()
//            把36进制转换为了bigintger
            var inputn = BigInteger(gongyaon.editableText.toString(),36)
//            进制转换来完成字符的传递
            var mingwentext:String = mingwen.editableText.toString()
            var utfmingwen = chaijiejiami(mingwentext)
            var inputm = BigInteger(utfmingwen)
            var rr = encrypt(inputm,inputn)
//            显示在屏幕上
            xianshi_mingwen.setText(rr.toString(36))
            Toast.makeText(this, "加密成功，请点击“复制密文”", Toast.LENGTH_SHORT).show()
//            保存了加密的内容
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("mingwen", rr.toString(36))
            editor.putString("gongyao", inputn.toString(36))
            editor.apply()
        }

//        复制密文
        button_fuzhimiwen.setOnClickListener {
            //            读取保存好的数据
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val mingwen =  prefs.getString("mingwen","kongdata")
//            var gongyao: String? = (namee,namen)
//            复制到剪贴板
            var clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            var clipData = ClipData.newPlainText("Label", mingwen)
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "密文复制成功", Toast.LENGTH_SHORT).show()
        }



//        返回上一级按钮
        button_back.setOnClickListener {
            finish()
        }
    }



//    字符串转换的


    fun chaijiejiami(string: String): String {
//        var text = "0123我爱你abc,.!，。！1230"
//    var textt: Char = '我'
//    println(chartoint(textt))
//    var bbb=chartoint(textt)
//    println(inttochar(bbb))
        var chrCharArray =string.toCharArray()
        var zonghe:String= ""
//    var danzi =chrCharArray[10]
//    println("加密的字符"+danzi)
//    var endanzi =chartoint(danzi)
//    println("得到的10进制码"+endanzi)
//    var dcdanzi =inttochar(endanzi.toInt())
//    println("解码后的字符"+dcdanzi)
        for(i in 0..(chrCharArray.size-1)){
//        println(chrCharArray[i])
            zonghe = zonghe + chartoint(chrCharArray[i])
            println(i)
        }
        println(zonghe)
        var zonghechu = "9" + zonghe
        println(zonghechu)
        return zonghechu
    }

    fun chartoint(char: Char): String {
        var aaa = char.toInt()
        println("aaa"+aaa)
        var ddd:String = aaa.toString()
        if((aaa.toString()).length <5){
            println(aaa.toString())
            ddd="0${aaa.toInt()}"
            println("ddd"+ddd)
        }
        if(ddd.length <5){
            println(ddd)
            ddd="00${ddd.toInt()}"
            println("ddd"+ddd)
        }
        if(ddd.length <5){
            println(ddd)
            ddd="000${ddd.toInt()}"
            println("ddd"+ddd)
        }
        if(ddd.length <5){
            println(ddd)
            ddd="0000${ddd.toInt()}"
            println("ddd"+ddd)
        }
        if(ddd.length <5){
            println(ddd)
            ddd="00000${ddd.toInt()}"
            println("ddd"+ddd)
        }
        return ddd
    }

    fun encrypt(m:BigInteger, n:BigInteger): BigInteger {
//    var e:BigInteger = ("65537")
        var e = BigInteger("65537")
        //    var r:BigInteger = 1
        var r = BigInteger("1")
        val shuzi2 = BigInteger("2")
        var biao = BigInteger("55555")
        var mm = m
//        mm = mm.mod(n)
        while (e.compareTo(BigInteger.ZERO)==1){
            biao = e.mod(shuzi2)
            if(biao.compareTo(BigInteger.ONE)==0){
                r = (r * mm) % n
            }
            mm =(mm * mm) % n
            e = e / shuzi2
        }
        return r
    }

}