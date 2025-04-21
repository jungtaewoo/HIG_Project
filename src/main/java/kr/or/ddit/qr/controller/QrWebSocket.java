package kr.or.ddit.qr.controller;

import java.io.IOException;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class QrWebSocket extends TextWebSocketHandler {

	private final Map<String, WebSocketSession> wsSessions;

	public QrWebSocket(Map<String, WebSocketSession> wsSessions) { // ✅ 생성자 주입 방식으로 변경
		this.wsSessions = wsSessions;
	}

	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception {
		String username = session.getPrincipal().getName();
		wsSessions.put(username, session);
		System.out.println("웹소켓 연결됨: " + username);
	}

	@Override
	public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
		String username = session.getPrincipal().getName();
		wsSessions.remove(username);
		System.out.println("웹소켓 종료됨: " + username);
	}

	// 특정 사용자에게 메시지 전송하는 메서드
	public void sendMessageToUser(String empId, String message) {
		WebSocketSession session = wsSessions.get(empId);
		System.out.println("웹소켓 메소드에서 보는 session :" + session);
		if (session != null && session.isOpen()) {
			try {
				session.sendMessage(new TextMessage(message));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 모든 사용자에게 메시지 전송하는 메서드
	public void broadcastMessage(String message) {
		for (WebSocketSession session : wsSessions.values()) {
			if (session.isOpen()) {
				try {
					session.sendMessage(new TextMessage(message));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
