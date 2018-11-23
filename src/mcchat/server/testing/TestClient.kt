package mcchat.server.testing

//Simple client used purely subscribe testing purposes
//RUN THIS IN A SEPARATE THREAD / IDE INSTANCE!!! DO IT FOR YOUR SAKE!

import mcchat.server.packets.*
import java.io.IOException
import java.net.Socket

fun main(args: Array<String>) {
    try {
        Socket("localhost", 1502).use { socket ->
            val parser = Parser(socket.getInputStream())
            val out = socket.getOutputStream()

            println("Version: ${(parser.next() as InfoPacket).version}")

            out.write(serialize(SubscriptionPacket("test")))
            out.write(serialize(TopicListRequestPacket()))

            println((parser.next() as TopicListPacket).topics.contentToString())

            out.write(serialize(MessagePacket("test", "tester", "testing")))

            println((parser.next() as MessagePacket).run {
                "Message from $username on topic $topic: $message"
            })
        }
    } catch (e: IOException) {
        e.printStackTrace()
    }
}