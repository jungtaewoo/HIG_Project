package kr.or.ddit.qr.controller;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;
@Configuration
@EnableWebSocket
public class QrWebSocketConfig implements WebSocketConfigurer {
    
    private final Map<String, WebSocketSession> wsSessions = new ConcurrentHashMap<>();

    @Bean
    public Map<String, WebSocketSession> wsSessions() {
        return wsSessions; 
    }

    @Bean
    public QrWebSocket pushHandler() {  // ✅ 직접 빈 등록
        return new QrWebSocket(wsSessions);  // Map을 생성자 주입
    }

    @Override
    public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
        registry.addHandler(pushHandler(), "/noti").withSockJS();
    }
}
