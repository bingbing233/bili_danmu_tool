package http

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.cookies.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import key.Const.ACCESS_KEY_ID_VALUE
import kotlinx.serialization.json.Json

private const val BASE_URL = "live-open.biliapi.com"

//it.addHeader("Accept", "application/json")
//it.addHeader("Content-Type", "application/json")
//it.addHeader("x-bili-content-md5", contentMd5Value)
//it.addHeader("x-bili-timestamp",now)
//it.addHeader("x-bili-signature-method","HMAC-SHA256")
//it.addHeader("x-bili-signature-nonce",now)
//it.addHeader("x-bili-accesskeyid",accessKeyIdValue)
//it.addHeader("x-bili-signature-version","1.0")
//it.addHeader("Authorization",encryptStr)

val httpClient = HttpClient(CIO) {
    install(DefaultRequest) {
        url {
            protocol = URLProtocol.HTTPS
            host = BASE_URL
        }
        val now = System.currentTimeMillis().toString()
        headers {
            append("Content-Type", "application/Json")
            append("Accept", "application/Json")
            append("x-bili-content-md5", "contentMd5Value")
            append("x-bili-timestamp", now)
            append("x-bili-signature-method", "HMAC-SHA256")
            append("x-bili-signature-nonce", now)
            append("x-bili-accesskeyid", ACCESS_KEY_ID_VALUE)
            append("x-bili-signature-version", "1.0")
            append("Authorization", "encryptStr")
        }
    }

    install(Logging) {
        level = LogLevel.ALL
    }

    install(HttpCookies) {
        storage = AcceptAllCookiesStorage()
    }

    install(ContentNegotiation) {
        json(Json {
            prettyPrint = true
            ignoreUnknownKeys = true
        })
    }

    install(HttpSend) {
    }


}