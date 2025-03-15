import http.LiveStartParam
import http.LiveUrl
import http.httpClient
import io.ktor.client.request.*
import io.ktor.http.*
import key.Const
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object MainViewModel {
    private val scope = CoroutineScope(Dispatchers.IO)

    fun start() {
        scope.launch {
            httpClient.post{
                url(LiveUrl.LIVE_START)
                setBody(LiveStartParam(Const.CODE,Const.APPID))
                contentType(ContentType.Application.Json)

                headersOf()
            }
        }
    }
}