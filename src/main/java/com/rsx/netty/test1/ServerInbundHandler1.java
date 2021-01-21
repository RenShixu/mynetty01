package com.rsx.netty.test1;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.FileRegion;
import io.netty.channel.SimpleChannelInboundHandler;

public class ServerInbundHandler1 extends SimpleChannelInboundHandler<FileRegion> {
    private Long length = 0L;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("channelActive invoke");
        super.channelActive(ctx);
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FileRegion msg) throws Exception {
        length += msg.count();
        System.out.println("read FileRegion count:" + msg.count());

    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.out.println("total length:"+length);
        super.exceptionCaught(ctx, cause);
    }
}
