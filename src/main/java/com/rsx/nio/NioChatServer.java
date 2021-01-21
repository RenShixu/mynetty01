package com.rsx.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class NioChatServer {
    public static void main(String[] args) {
        ServerSocketChannel serverSocketChannel = null;
        Selector selector = null;
        List<SocketChannel> socketChannels = new ArrayList<>();
        try{
            serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(9999));
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while(selector.select() > 0){
                Set<SelectionKey> setKeys = selector.selectedKeys();
                Iterator<SelectionKey> itKeys = setKeys.iterator();
                while (itKeys.hasNext()){
                    SelectionKey key = itKeys.next();
                    if(key.isAcceptable()){
                        ServerSocketChannel ssc1 = (ServerSocketChannel)key.channel();
                        SocketChannel socketChannel = ssc1.accept();
                        socketChannel.configureBlocking(false);
                        socketChannel.register(selector,SelectionKey.OP_READ);
                        if(!socketChannels.contains(socketChannel)){
                            socketChannels.add(socketChannel);
                            System.out.println(socketChannel.toString());
                            System.out.println(socketChannel.getRemoteAddress().toString()+" 上线了");
                        }

                    }else if(key.isReadable()){
                        SocketChannel sc1 = (SocketChannel) key.channel();
                        System.out.println(sc1.toString());
                        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);

                        for(SocketChannel sc2 : socketChannels){
                            byteBuffer.clear();
                            byteBuffer.put((sc1.getRemoteAddress().toString() + "->").getBytes());
                            byteBuffer.flip();
                            sc2.write(byteBuffer);
                            byteBuffer.clear();
                            while(sc1.read(byteBuffer) > 0){
                                byteBuffer.flip();
                                sc2.write(byteBuffer);
                                byteBuffer.flip();
                            }
                        }
                    }
                    setKeys.remove(key);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if(serverSocketChannel != null){
                    serverSocketChannel.close();
                }
                if(selector != null){
                    selector.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
