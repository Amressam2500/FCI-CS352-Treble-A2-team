package com.FCI.SWE.Notification;

public class Notifiy_Request extends UserNotification  {

	
	public Notifiy_Request() {
		
	}

	@Override
	public String extract(String name,String note) {
		if(note.equals("accept")) name+=" accept your request";
		if(note.equals("request")) name+=" send you request";
		
			return name;
	}

}
