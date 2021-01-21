package com.rsx.nio;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import sun.nio.ch.DirectBuffer;

import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ByteBufferTest {
    public static void main(String[] args) throws Exception{
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
        byteBuffer.flip();
        FileInputStream inputStream = new FileInputStream("");
        FileChannel channel = inputStream.getChannel();
        channel.read(byteBuffer);

//        DirectBuffer directBuffer;

        FileInputStream fileIn = new FileInputStream("");
        FileChannel fileChannel = fileIn.getChannel();
//        fileChannel.transferTo()
        //C:\Users\RenShixu\Desktop

        ByteBuf buf = Unpooled.buffer();
        buf.writeBytes("hell".getBytes());
    }
}
