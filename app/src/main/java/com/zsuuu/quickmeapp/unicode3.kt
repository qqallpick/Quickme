import android.net.wifi.p2p.WifiP2pManager
import java.math.BigInteger

fun main(){
    var text = "90004800049000500005125105292332032000097000980009900044000460003365292122906528100049000500005100048"
    inttochar(text)

}

fun inttochar(string: String){
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
}

fun inttochar2(int: Int): Char {
    var bbb=int.toChar()
    return bbb
}
