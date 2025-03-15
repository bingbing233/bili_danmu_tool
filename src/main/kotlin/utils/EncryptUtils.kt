package utils

import java.security.MessageDigest
import java.util.*
import javax.crypto.Mac
import javax.crypto.spec.SecretKeySpec
import kotlin.experimental.and

object EncryptUtils {

    fun hmacSha256(key: String, data: String): String {
        val algorithm = "HmacSHA256"
        val secretKeySpec = SecretKeySpec(key.toByteArray(), algorithm)
        val mac = Mac.getInstance(algorithm)
        mac.init(secretKeySpec)
        val hmacBytes = mac.doFinal(data.toByteArray())
        return Base64.getEncoder().encodeToString(hmacBytes)
    }


    fun md5(value: String): String {
        val md5s = MessageDigest.getInstance("MD5").digest(value.toByteArray())
        val sb = StringBuilder()
        for (b in md5s) {
            val i = b.toInt() and 0xff
            var hexStr = Integer.toHexString(i)
            if (hexStr.length < 2) {
                hexStr = "0$hexStr"
            }
            sb.append(hexStr)
        }
        return sb.toString()
    }
}