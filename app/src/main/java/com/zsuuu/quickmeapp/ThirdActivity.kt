package com.zsuuu.quickmeapp

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.activity_third.*
import java.lang.StringBuilder
import java.math.BigInteger

class ThirdActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_third)

//        退出按钮
        button_back3.setOnClickListener {
            finish()
        }
//解密按钮
        button_jiemi.setOnClickListener {
            Toast.makeText(this, "解密密文中", Toast.LENGTH_SHORT).show()
//            读取数据自己的私钥d，n
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val d =  BigInteger(prefs.getString("d","kongdata"),36)
            val n =  BigInteger(prefs.getString("n","kongdata"),36)
//            接收密文
//            var inputmi = BigInteger(miwen3.editableText.toString())
            var mimiwen = miwen3.editableText.toString()
            var inputmi = BigInteger(mimiwen,36)
            var rr = decrypt(inputmi,d,n)
//            显示在屏幕上
            var mingwenrr = rr.toString()
            var rrr = zifuchuanzhuanhuan(mingwenrr)
            xianshi_miwen.setText(rrr)
            Toast.makeText(this, "解密成功", Toast.LENGTH_SHORT).show()
//            保存了明文的内容
//            val editor = getSharedPreferences("data", Context.MODE_PRIVATE).edit()
//            editor.putString("fuzhimingwen", rr.toString())
//            editor.apply()
        }

    }
    //解密过程和加密过程是一样的
     fun decrypt(m:BigInteger, d:BigInteger, n:BigInteger): BigInteger {
//    var e:BigInteger = ("65537")
        var e = d
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

//字符串转换


//    字符串转换的

    fun zifuchuanzhuanhuan(string: String): String {
//        var text = "90004800049000500005125105292332032000097000980009900044000460003365292122906528100049000500005100048"
//        inttochar(string)
        var changshu = string.toCharArray()
        println(changshu[0])
        var jieguo:String=""

        for (i in 1..changshu.size-1 step 5){
            var danci1:String=""
            for(i in i..i+4){
                danci1 =  danci1 + changshu[i]
            }
            println("danci1"+danci1)
            var shuchu = inttochar2(danci1.toInt())
            println("shuchu"+shuchu)
            jieguo = jieguo +shuchu
        }
        println(jieguo)
        return jieguo
    }

    fun inttochar2(int: Int): Char {
        var bbb=int.toChar()
        return bbb
    }












}