import http.LiveStartParam
import http.LiveUrl
import http.httpClient
import io.ktor.client.request.*
import io.ktor.http.*
import key.Const
import key.Const.ACCESS_KEY_ID_VALUE
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import utils.EncryptUtils

object MainViewModel {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun start() {
        scope.launch {
            httpClient.post {
                url(LiveUrl.LIVE_START)
                setBody(LiveStartParam(Const.CODE, Const.APPID))
//                contentType(ContentType.Application.Json)
//                {"code":"E7SO6UW23XTS5","app_id":1742223725093}

                val contentValue = "{\"code\":\"E7SO6UW23XTS5\",\"app_id\":1742223725093}"
                val md5Body = EncryptUtils.md5(contentValue)
                val now = System.currentTimeMillis().toString()
                val headerStr =
                            "x-bili-accesskeyid:$ACCESS_KEY_ID_VALUE\n" +
                            "x-bili-content-md5:$md5Body\n" +
                            "x-bili-signature-method:HMAC-SHA256\n" +
                            "x-bili-signature-nonce:$now\n" +
                            "x-bili-signature-version:1.0\n" +
                            "x-bili-timestamp:$now"
                val authStr = EncryptUtils.hmacSha256(Const.ACCESS_KEY_ID_SECRET, headerStr)
                headers.append("Content-Type", "application/Json")
                headers.append("Accept", "application/Json")
                headers.append("x-bili-content-md5", md5Body)
                headers.append("x-bili-timestamp", now)
                headers.append("x-bili-signature-method", "HMAC-SHA256")
                headers.append("x-bili-signature-nonce", now)
                headers.append("x-bili-accesskeyid", ACCESS_KEY_ID_VALUE)
                headers.append("x-bili-signature-version", "1.0")
                headers.append("Authorization", authStr)
                println("MyHeaders:  ${headers.entries()}")
            }
        }
    }
}