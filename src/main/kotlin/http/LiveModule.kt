package http

import kotlinx.serialization.Serializable

@Serializable
class LiveStartParam(val code:String,val app_id:Long)