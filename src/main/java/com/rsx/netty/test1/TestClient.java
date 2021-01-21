package com.rsx.netty.test1;

import io.netty.buffer.ByteBuf;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;

public class TestClient {
    public static void main(String[] args) throws IOException, InterruptedException {
        //修改器.rar
        //10150216.rar
        FileInputStream in = new FileInputStream("E:\\BaiduNetdiskDownload\\10150216.rar");
        FileChannel fileChannel = in.getChannel();


        long length = fileChannel.size();
        System.out.println("total length:"+length);
        SocketChannel socketChannel = SocketChannel.open();
        socketChannel.connect(new InetSocketAddress("localhost",8889));
        socketChannel.configureBlocking(true);

        long beforeMillis = System.currentTimeMillis();
        fileChannel.transferTo(0,length,socketChannel);
        long afterMillis = System.currentTimeMillis();
        System.out.println(afterMillis-beforeMillis);

    }
}
