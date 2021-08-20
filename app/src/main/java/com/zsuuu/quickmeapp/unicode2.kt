import android.net.wifi.p2p.WifiP2pManager
import java.math.BigInteger

fun main(){
    var text = "0123我爱你abc,.!，。！1230"
//    var textt: Char = '我'
//    println(chartoint(textt))
//    var bbb=chartoint(textt)
//    println(inttochar(bbb))
    var chrCharArray =text.toCharArray()
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

//String strStringType="my string"; //创建一个字符串变量strStringType
//char[] chrCharArray; //创建一个字符数组chrCharArray
//chrCharArray = strStringType.toCharArray(); //将字符串变量转换为字符数组
//strStringType= String.valueOf(chrCharArray ); //将字符数组转换为字符串



fun inttochar(int: Int): Char {
    var bbb=int.toChar()
    return bbb
}

fun chaifen(string: String){
    var str = string.split("")
//    var strchaichar = chartoint(str[1])
}