import java.math.BigInteger

fun main(){
    var text = "0123我爱你abc,.!，。！1230"
    var textt:String= text


    var text1 = encode(textt)
    println(text1)


//    var text1 = "1000000"
    var bigint = text
    var text2 = decode(text1)
    println(text2)
}


fun Int.toHexString(): String = Integer.toHexString(this)

//char ->unicode
fun encode(char: Char) = "${char.toInt()}"

//String ->unicode
fun encode(text: String) = text
        .toCharArray()
        .map { encode(it) }
        .joinToString(separator = "", truncated = "")

//unicode ->String
fun decode(encodeText: String): String {
    fun decode1(unicode: String) = unicode.toInt(10).toChar()
    val unicodes = encodeText.split("")
            .map { if (it.isNotBlank()) decode1(it) else null }.filterNotNull()
    return String(unicodes.toCharArray())
}

