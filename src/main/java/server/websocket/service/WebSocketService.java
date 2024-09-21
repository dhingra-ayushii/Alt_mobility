package server.websocket.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import server.alert.service.AlertService;

@Controller
public class WebSocketService {
    @Autowired
    private AlertService alertService;

	@Autowired
	private SimpMessagingTemplate messagingTemplate;
	
	@Scheduled(fixedRate = 5000)
	public void sendScheduledNotification() {
		String notificationMessage = "Backend push notification at " + System.currentTimeMillis();
		// alert.setContent(notificationMessage);
		// alert.setSender("CHAT");
		// Send the notification to /topic/notifications
        ;

		messagingTemplate.convertAndSend("/topic/alert",alertService.getAllAlert() );

		System.out.println("Sent notification: ScheduledNotification" + notificationMessage);
	}
	
	@Scheduled(fixedRate = 5000)
	public void sendAvgResponseTime() {
		String notificationMessage = "Backend push notification at 1" + System.currentTimeMillis();
		// alert.setContent(notificationMessage);
		// alert.setSender("CHAT");
	   //Send the notification to /topic/notifications

		messagingTemplate.convertAndSend("/topic/avgResponseTime",alertService.getCriticalAlertPercentageByAlertType() );

		System.out.println("Sent notification:AvgResponseTime " + notificationMessage);
	}
	
	@Scheduled(fixedRate = 5000)
	public void sendServityResponse() {
		String notificationMessage = "Backend push notification at " + System.currentTimeMillis();
		// alert.setContent(notificationMessage);
		// alert.setSender("CHAT");
		// Send the notification to /topic/notifications

		messagingTemplate.convertAndSend("/topic/servityResponse",notificationMessage );

		System.out.println("Sent notification: ServityResponse" + notificationMessage);
	}


}
