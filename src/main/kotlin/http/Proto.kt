package http

import java.nio.Buffer
import java.nio.ByteBuffer

class Proto {
    companion object {
        const val OP_HEARTBEAT = 2
        const val OP_HEARTBEAT_REPLY = 3
        const val OP_SEND_SMS_REPLY = 5
        const val OP_AUTH = 7
        const val OP_AUTH_REPLY = 8
    }
    private var packetLength = 0
    private val headerLength:Short = 16
    private val version:Short = 0
    private var operation = 7
    private var seq = 0

    fun pack(body:String,op:Int): ByteBuffer? {
        packetLength = headerLength + body.length
        operation = op
        val buffer = ByteBuffer.allocate(packetLength)
        buffer.putInt(packetLength)
        buffer.putShort(headerLength)
        buffer.putShort(version)
        buffer.putInt(operation)
        buffer.putInt(seq)
        buffer.put(body.toByteArray())
        return buffer
    }
}