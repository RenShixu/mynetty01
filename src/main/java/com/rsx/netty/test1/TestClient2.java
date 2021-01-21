package com.rsx.netty.test1;

import com.rsx.netty.socket.ClientSocketHandler;
import com.rsx.netty.socket.ClientSocketInitializer;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.codec.LengthFieldPrepender;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;

import java.io.FileInputStream;
import java.nio.channels.FileChannel;

public class TestClient2 {
    public static void main(String[] args) {
        EventLoopGroup workGroup = new NioEventLoopGroup();
        try{
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(workGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                protected void initChannel(SocketChannel ch) throws Exception {
                    ChannelPipeline pipeline = ch.pipeline();

                    //add default handler
                    //pipeline.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE,0,4,0,4));
                    //pipeline.addLast(new LengthFieldPrepender(4));
                    //pipeline.addLast(new StringDecoder(CharsetUtil.UTF_8));
                    //pipeline.addLast(new StringEncoder(CharsetUtil.UTF_8));
                    //pipeline.addLast(new ClientSocketHandler());
                }
            });

            ChannelFuture future = bootstrap.connect("127.0.0.1",8888).sync();

            //CODEX破解补丁.rar
            //10150216.rar
            FileInputStream in = new FileInputStream("E:\\BaiduNetdiskDownload\\CODEX破解补丁.rar");
            FileChannel fileChannel = in.getChannel();
            long length = fileChannel.size();
            System.out.println("total length:"+length);
            future.channel().writeAndFlush(new DefaultFileRegion(fileChannel,0,length));
            //future.channel().writeAndFlush("hello");

            future.channel().closeFuture().sync();
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            workGroup.shutdownGracefully();
        }
    }
}
