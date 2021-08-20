package com.zsuuu.quickmeapp

import android.graphics.PointF.length
import java.lang.StringBuilder
import java.math.BigInteger
import java.util.*


fun main() {
    var ran = Random()
    var p = BigInteger.probablePrime(2048, ran)
    var q = BigInteger.probablePrime(2048, ran)
//    println("p:" + length(p))
    // 公钥私钥中用到的两个大质数p,q'''
//    var p = BigInteger("9062533795040332573828165676041420023923186629816064310889114191774242267509558224186412730341985526369762970541027092793448798602094649082837146502664467") ;
//    var q = BigInteger("8237979010845221321728170991711087677301781331097941496040295750873700896540126571402847199863674669421668134439033787351471915843830789790454964600714787") ;
    println("p:" + p)
    println("q:" + q)
//    求公模
    var n = qium(p, q)
    println("n:" + n)
    var d = genkey(p, q)
    var dd = d+ qiufy(p, q)
    println("d:" + dd)
}



fun Int.toHexString(): String = Integer.toHexString(this)

//char ->unicode
fun encode(char: Char) = "\\u${char.toInt().toHexString()}"

//String ->unicode
fun encode(text: String) = text
        .toCharArray()
        .map { encode(it) }
        .joinToString(separator = "", truncated = "")

//unicode ->String
fun decode(encodeText: String): String {
    fun decode1(unicode: String) = unicode.toInt(16).toChar()
    val unicodes = encodeText.split("\\u")
            .map { if (it.isNotBlank()) decode1(it) else null }.filterNotNull()
    return String(unicodes.toCharArray())
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
    var fy = qiufy(p, q)
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
