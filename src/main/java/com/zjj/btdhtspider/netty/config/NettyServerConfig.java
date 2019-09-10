package com.zjj.btdhtspider.netty.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.web.embedded.NettyWebServerFactoryCustomizer;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import com.zjj.btdhtspider.netty.handler.DhtServerHandler;

import io.netty.bootstrap.Bootstrap;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 *ClassName:NettyConfig
 *@Description:
 *
 *@Author zjj
 *@Date 2019年9月5日
 *@Version 1.0
 */
@Component
public class NettyServerConfig {
	
	@Value("${netty.server.port}")
	private int port;
	
	@Value("${netty.server.boss-group.size}")
	private int boss_size;
	
	@Value("${netty.server.work-group.size}")
	private int work_size;
	
	@Autowired
	@ConditionalOnMissingBean
	@Bean("serverBootstrap")
	public ServerBootstrap serverBootstrap(){
		ServerBootstrap serverBootstrap=new ServerBootstrap();
		serverBootstrap.group(bossGroup(), workGroup()).childHandler(new DhtServerHandler());
		return serverBootstrap;
	}
	
	private NioEventLoopGroup bossGroup(){
		return new NioEventLoopGroup(boss_size);
	}
	private NioEventLoopGroup workGroup(){
		return new NioEventLoopGroup(work_size);
	}
	
}
