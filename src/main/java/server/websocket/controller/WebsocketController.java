package server.websocket.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import server.websocket.model.AlertResponseTime;

@Controller
public class WebsocketController {
	@MessageMapping("/alertNotification")
	@SendTo("/topic/alert")
	public AlertResponseTime sendMessage(@Payload AlertResponseTime AlertResponseTime) {
		return AlertResponseTime;
	}
	
	@MessageMapping("/avgResponseTime")
	@SendTo("/topic/avgResponseTime")
	public AlertResponseTime sendAvgResponseTime(@Payload AlertResponseTime AlertResponseTime) {
		return AlertResponseTime;
	}
	
	@MessageMapping("/servityResponse")
	@SendTo("/topic/servityResponse")
	public AlertResponseTime sendServityResponse(@Payload AlertResponseTime AlertResponseTime) {
		return AlertResponseTime;
	}

}
