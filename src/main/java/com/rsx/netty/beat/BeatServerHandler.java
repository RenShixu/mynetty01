package com.rsx.netty.beat;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.timeout.IdleStateEvent;

public class BeatServerHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
        if(evt instanceof IdleStateEvent){
            IdleStateEvent idEvt = (IdleStateEvent)evt;

            String event = "";
            switch (idEvt.state()){
                case READER_IDLE:
                    event = "读空闲";
                    break;
                case WRITER_IDLE:
                    event = "写空闲";
                    break;
                case ALL_IDLE:
                    event = "读写空闲";
                    break;
            }

            System.out.println("["+ctx.channel().remoteAddress()+"]:"+event);
            //ctx.channel().close();
        }
    }
}
