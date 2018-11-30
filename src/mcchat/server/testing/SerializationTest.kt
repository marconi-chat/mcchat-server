package mcchat.server.testing

import mcchat.server.packets.serialize
import mcchat.server.packets.InfoPacket
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test


internal class SerializationTest {
    @Test
    fun InfoPacketSerializationMatchingTest() {
        val serializedPacket: ByteArray = serialize(InfoPacket(0.toByte()))
        val manuallySerializedPacket = ByteArray(2)
        //Serialize Packet manually
        manuallySerializedPacket[0] = 0
        manuallySerializedPacket[1] = 0
        //See if the results match
        assertArrayEquals(manuallySerializedPacket, serializedPacket, "The packets must be equal in structure")
        //Deserialization test section
        val reserializedPacket = InfoPacket(manuallySerializedPacket[1])
        //Check if starter and newly reserialized packet match
        //TODO
    }
}