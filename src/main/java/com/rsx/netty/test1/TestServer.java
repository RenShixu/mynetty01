package com.rsx.netty.test1;

import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

public class TestServer {
    public static void main(String[] args) {
        try{
            ServerSocketChannel socketChannel = ServerSocketChannel.open();
            ServerSocket serverSocket = socketChannel.socket();
            serverSocket.setReuseAddress(true);
            serverSocket.bind(new InetSocketAddress(8889));

            ByteBuffer byteBuffer = ByteBuffer.allocate(4096);

            while(true){
                SocketChannel sc = socketChannel.accept();
                socketChannel.configureBlocking(true);

                long total = 0;
                int readLength;
                try{
                    while((readLength = sc.read(byteBuffer)) > 0){
                        total += readLength;
                        byteBuffer.flip();
                    }
                } catch(Exception e) {
                    e.printStackTrace();
                }

                System.out.println("total length:"+total);
            }



        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
