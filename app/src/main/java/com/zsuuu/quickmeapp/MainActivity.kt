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
//        把存好的数据返回回来
//        val inputText = load()
//        if (inputText.isNotEmpty()){
//            zhishup.setText(inputText)
//            zhishup.setSelection(inputText.length)
//            Toast.makeText(this, "有存好的默认数据", Toast.LENGTH_SHORT).show()
//        }

//        生成密钥的按钮
        button_shengchengmiyao.setOnClickListener{
//            接收edittext的数据
//            try{
//                var inputp = BigInteger(zhishup.editableText.toString())
//                var inputq = BigInteger(zhishuq.editableText.toString())
//                Toast.makeText(this, "生成密钥中P:"+inputp+"Q:"+inputq+"e:65537", Toast.LENGTH_SHORT).show()
//                xianshi_p.setText("大质数P:"+inputp)
//                xianshi_q.setText("大质数Q:"+inputq)
//                xianshi_e.setText("公钥e:65537")
//                var d = mainshengcheng(inputp,inputq)
//                xianshi_d.setText("私钥d:"+d)
//                var n = qium(inputp,inputq)
//                xianshi_n.setText("公模n:"+n)
//            }catch (e:IOException){
//                e.printStackTrace()
//            }
//            非空edittext的判断
//            if(!(zhishup.getText().toString().equals("")) && !(zhishuq.getText().toString().equals(""))){
//            进度条开始
//            progressBaryuan.visibility = View.VISIBLE
            Toast.makeText(this, "密钥生成中，请等候1-3秒", Toast.LENGTH_SHORT).show()
            var ran = Random()
            var inputp = BigInteger.probablePrime(1700, ran)
            var inputq = BigInteger.probablePrime(1700, ran)
//                var inputp = BigInteger(zhishup.editableText.toString())
//                var inputq = BigInteger(zhishuq.editableText.toString())
            Toast.makeText(this, "密钥生成完毕", Toast.LENGTH_SHORT).show()
//                xianshi_p.setText("大质数P:"+inputp)
//                xianshi_q.setText("大质数Q:"+inputq)
            xianshi_e.setText("公钥e:65537")
            var d = mainshengcheng(inputp,inputq)
            xianshi_d.setText("私钥d:"+d.toString(36))
            var n = qium(inputp,inputq)
            xianshi_n.setText("公模n:"+n.toString(36))
//            }else{
//                Toast.makeText(this, "防止空指针，请输入数据", Toast.LENGTH_SHORT).show()}
//            进度条消失
//            progressBaryuan.visibility = View.GONE
//            存储e\d\n保存
            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
            editor.putString("e", "65537")
            editor.putString("d", d.toString(36))
            editor.putString("n", n.toString(36))
            editor.apply()
        }

//保存密钥

//        button_save.setOnClickListener{
//            val editor = getSharedPreferences("data",Context.MODE_PRIVATE).edit()
//            editor.putString("name", "Tom")
//            editor.putInt("age", 28)
//            editor.putBoolean("married", false)
//            editor.apply()
//        }

//复制公钥和私钥的按钮
        button_fuzhie.setOnClickListener{
//            读取保存好的数据
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val namee =  prefs.getString("e","kongdata")
//            val named =  prefs.getString("d","kongdata")
            val namen =  prefs.getString("n","kongdata")
            val array: Array<String?> = arrayOf( namee , namen )
//            var gongyao: String? = (namee,namen)
//            复制到剪贴板
            var clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            把array[1]转为16进制
            var clipData = ClipData.newPlainText("Label", array[1])
            clipboardManager.setPrimaryClip(clipData)
            Toast.makeText(this, "公钥复制成功", Toast.LENGTH_SHORT).show()
        }

//        button_fuzhid.setOnClickListener{
//            //            读取保存好的数据
//            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
//            val namee =  prefs.getString("e","kong")
//            val named =  prefs.getString("d","kong")
//            val namen =  prefs.getString("n","kong")
//            val array: Array<String?> = arrayOf( named , namen)
//            var clipboardManager = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
//            var clipData = ClipData.newPlainText("Label", array[1])
//            clipboardManager.setPrimaryClip(clipData)
//            Toast.makeText(this, "私钥复制成功", Toast.LENGTH_SHORT).show()
//        }


//        页面跳转的按钮
        button_next.setOnClickListener{
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
        //        页面跳转的按钮
        button_nextjie.setOnClickListener{
            val intent = Intent(this,ThirdActivity::class.java)
            startActivity(intent)
        }
//        进度条按钮
//        button_jindutiao.setOnClickListener{
//            if(progressBaryuan.visibility == View.VISIBLE){progressBaryuan.visibility = View.GONE}
//            else{progressBaryuan.visibility = View.VISIBLE}
//        }
//        警告界面的按钮
        button_jinggao.setOnClickListener{
            AlertDialog.Builder(this).apply{
                setTitle("退出快密")
                setMessage("请问您确定要退出快密吗?")
                setCancelable(false)
                setPositiveButton("退出"){dialog,which ->finish()}
                setNegativeButton("不退出"){dialog,which ->}
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

    fun mainshengcheng(p: BigInteger, q: BigInteger): BigInteger {
        // 公钥私钥中用到的两个大质数p,q'''
//        "9062533795040332573828165676041420023923186629816064310889114191774242267509558224186412730341985526369762970541027092793448798602094649082837146502664467") ;
//        var q = BigInteger("8237979010845221321728170991711087677301781331097941496040295750873700896540126571402847199863674669421668134439033787351471915843830789790454964600714787") ;
        println("p:" + p)
        println("q:" + q)
//    求公模
        var n = qium(p, q)
        println("n:" + n)
        var d = genkey(p, q)
//        大数比较
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

    //欧几里得算法
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

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.set_item -> Toast.makeText(this, "add", Toast.LENGTH_SHORT).show()
            R.id.tell_item -> Toast.makeText(this, "remove", Toast.LENGTH_SHORT).show()
        }
        return true
    }


}