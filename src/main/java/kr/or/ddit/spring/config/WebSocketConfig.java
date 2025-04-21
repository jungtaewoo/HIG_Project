package kr.or.ddit.spring.config;

import javax.annotation.PostConstruct;

import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {
   @PostConstruct
   public void init() {
	   log.info("웹소켓 초기화됨 로그로그로그!!~");
	   System.out.println("웹소켓 초기화됨 (빈등록확인)");
   }
	
   @Override
   public void registerStompEndpoints(StompEndpointRegistry registry) {
      registry.addEndpoint("/wss") // 클라이언트가 WebSocket 연결을 할 엔드포인트
            .setAllowedOriginPatterns("*")
            .withSockJS(); // SockJS 사용 (웹소켓을 지원하지 않는 브라우저를 위한 폴백 옵션)
   }

   @Override
   public void configureMessageBroker(MessageBrokerRegistry registry) {
      registry.enableSimpleBroker("/topic"); // 메시지를 브로드캐스트할 경로
      registry.setApplicationDestinationPrefixes("/app"); // 클라이언트가 메시지를 보낼 때 사용하는 prefix
   }
}
