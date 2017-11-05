package local;

import br.com.moip.API;
import br.com.moip.Client;
import br.com.moip.authentication.Authentication;
import br.com.moip.authentication.BasicAuth;
import br.com.moip.request.NotificationPreferenceRequest;
import br.com.moip.resource.NotificationPreference;
import br.com.moip.response.NotificationPreferenceListResponse;

public class WebhookCreation {

	public static void main(String[] args) {

		WebhookCreation w = new WebhookCreation();
		w.doIt();

	}
	
	public void doIt() {
		Authentication auth = new BasicAuth("XETYILHK2G7CWJDWGQK9Q9RLZQ292IYH", "NUWX7F4ZXZQSINWAQPDG3JRKCGGUPZOPZ723UUVC");
		Client client = new Client(Client.SANDBOX, auth);
		API api = new API(client);
		
		NotificationPreferenceListResponse notificationList = api.notification().list();
		System.out.println(notificationList);
		
//		api.notification().delete("NPR-FCQLSCHEF5UM");
//		
		
//		NotificationPreference notificationPreference = api.notification().create(
//				  new NotificationPreferenceRequest()
//				    .addEvent("PAYMENT.*")
//				    .target("http://f7304439.ngrok.io/webhook/moip")
//				);
		
		
	}

}
