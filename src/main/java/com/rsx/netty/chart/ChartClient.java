package com.rsx.netty.chart;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

import java.util.Scanner;

public class ChartClient {
    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new ChartClientInitializer());

            Channel channel = bootstrap.connect("localhost",8903).sync().channel();


            Scanner sc = new Scanner(System.in);
            for(;;){
                channel.writeAndFlush(sc.nextLine());
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            workGroup.shutdownGracefully();
        }
    }
}
