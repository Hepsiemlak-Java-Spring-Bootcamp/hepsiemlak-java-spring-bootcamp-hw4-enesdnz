package emlakburada.queue;

import emlakburada.service.EmailMessage;
import emlakburada.service.UserData;

public interface QueueService {
	
	void sendMessage(EmailMessage email);
}
