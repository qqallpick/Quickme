package com.zsuuu.quickmeapp

import android.annotation.SuppressLint
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*
import java.lang.StringBuilder
import java.math.BigInteger
import java.util.*


class MainActivity : AppCompatActivity() {
    @SuppressLint("ServiceCast")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        quickme_version.setText("版本号：v1.2　")
//        生成密钥的按钮
        button_shengchengmiyao.setOnClickListener{
            Toast.makeText(this, "密钥生成中，请等候1-3秒", Toast.LENGTH_SHORT).show()
//            生成大质数P、Q
            var ran = Random()
            var inputp = BigInteger.probablePrime(1700, ran)
            var inputq = BigInteger.probablePrime(1700, ran)
//            默认公钥e：65537，为公认加密标准
            xianshi_e.setText("公钥e:65537")
//            生成私钥d
            var d = mainshengcheng(inputp,inputq)
            xianshi_d.setText("私钥d:"+d.toString(36))
//            生成模n
            var n = qium(inputp,inputq)
            xianshi_n.setText("公模n:"+n.toString(36))
            Toast.makeText(this, "密钥生成完毕", Toast.LENGTH_SHORT).show()
//            存储e、d、n保存
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("e", "65537")
//            为了减少密钥长度采用36进制保存数据
            editor.putString("d", d.toString(36))
            editor.putString("n", n.toString(36))
            editor.apply()
        }

//          复制公钥按钮
        button_fuzhie.setOnClickListener{
//            读取保存好的数据
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val namee =  prefs.getString("e","kongdata")
            val namen =  prefs.getString("n","kongdata")
            val array: Array<String?> = arrayOf( namee , namen )
//            复制到剪贴板
            var clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            array[1] = namen, 公钥e默认为65537，所以不用复制了
            var clipData = ClipData.newPlainText("Label", array[1])
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "公钥复制成功", Toast.LENGTH_SHORT).show()
        }

//        页面跳转的按钮，进入加密
        button_next.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }

//        页面跳转的按钮，进入解密
        button_nextjie.setOnClickListener{
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }
//      页面跳转的按钮，使用说明
        button_howtouse.setOnClickListener{
            val intent = Intent(this,FourthActivity::class.java)
            startActivity(intent)
        }
//      页面跳转的按钮，图片加密
        button_jiamitupian.setOnClickListener{
            val intent = Intent(this,FifthActivity::class.java)
            startActivity(intent)
        }
//      页面跳转的按钮，解密图片
        button_jiemitupian.setOnClickListener{
            val intent = Intent(this,SixthActivity::class.java)
            startActivity(intent)
        }

//      警告界面的按钮，退出按钮
        button_jinggao.setOnClickListener{
            AlertDialog.Builder(this).apply{
                setTitle("退出快密")
                setMessage("请问您确定要退出快密吗?")
                setCancelable(false)
                setPositiveButton("退出"){dialog,which ->finish()}
                setNegativeButton("继续使用"){dialog,which ->}
                show()
            }
        }
    }

//    欧几里得算法
    private fun gcd(a:Int ,b: Int): Int {
        if(b.equals(0)){
            return a
        }
        else{
            val bb = a%b
            return gcd(b,  bb)
        }
    }

//    扩展欧几里得算法
    private fun extGcd(a :Int, b:Int): IntArray {
        if(b.equals(0)){
            var x1 = 1
            var y1 = 0
            var x = x1
            var y = y1
            var r = a
            var just:IntArray = intArrayOf(r, x, y)
            return just
        }else{
            var bb=a%b
            var temp: IntArray = extGcd(b, bb)
            var r  = temp[0]
            var x1 = temp[1]
            var y1 = temp[2]
            var x = y1
            var y = x1 - a/b*y1
            var just:IntArray = intArrayOf(r, x, y)
            return just
        }
    }

//    求私钥
    fun mainshengcheng(p: BigInteger, q: BigInteger): BigInteger {
        println("p:" + p)
        println("q:" + q)
//    求公模
        var n = qium(p, q)
        println("n:" + n)
        var d = genkey(p, q)
//    大数比较，需要补一次数据
        if(d.compareTo(BigInteger.ZERO)==-1){
            var dd = d+ qiufy(p,q)
            println("d:" + dd)
            return dd
        }
        else{
            return  d
        }
    }

    //求公模
    fun qium(p: BigInteger, q: BigInteger): BigInteger {
        var n = p*q
        return n
    }

    fun qiufy(p: BigInteger, q: BigInteger): BigInteger {
        var fy = p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
        return fy
    }

    //求私钥
    fun genkey(p: BigInteger, q: BigInteger): BigInteger {
//    var n = qium(p, q)
//    val n = p.multiply(q)
//    var fy =p.subtract(BigInteger.ONE).multiply(q.subtract(BigInteger.ONE))
        var fy = qiufy(p,q)
        val e = BigInteger("65537")
        var a = e
        var b = fy
//    这里有个拓展欧几里得算法
        var rxy: Array<BigInteger> = extGcd(a, b)
        var r = rxy[0]
        var x = rxy[1]
        var y = rxy[2]
        var d = x
        return d
    }

    //拓展欧几里得算法
    fun extGcd(a: BigInteger, b: BigInteger): Array<BigInteger> {
        if(b.equals(BigInteger.ZERO)){
            var x1 = BigInteger.ONE
            var y1 = BigInteger.ZERO
            var x = x1
            var y = y1
            var r = a
//        if(x.compareTo(BigInteger.ZERO)==-1){
//            x = x.add (b)
//        }
            var result = arrayOf<BigInteger>(r, x, y)
            return result
        }else{
            var bb = a % b
            var temp = extGcd(b, bb)
            var r = temp[0]
            println("r:" + r)
            var x1 = temp[1]
            println("x1:" + x1)
            var y1 = temp[2]
            println("y1:" + y1)
//        r, x1, y1 = ext_gcd(b, a % b)
//        如果私钥小于0,d=d+fy
            var x = y1
//        if(x.compareTo(BigInteger.ZERO)==-1){
//            x = x.add (b)
//        }
            var y = x1.subtract(a.divide(b).multiply(y1))
            var result = arrayOf<BigInteger>(r, x, y)
            return result
        }
    }

//    菜单功能，先在先放在这里，以后再弄
//    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
//        menuInflater.inflate(R.menu.main, menu)
//        return true
//    }
//
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        when(item.itemId){
//            R.id.set_item -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
//            R.id.tell_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
//        }
//        return true
//    }


}