package com.FCI.SWE.ServicesModels;

import java.util.ArrayList;

import com.FCI.SWE.Notification.*;
import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Notifications {

	private String friend_name;
	private String user_id;
	private String friend_id;
	private String user_name;
	private String type;
	private String note;
	private String not_id;
	
	

	public String getFriend_id() {
		return friend_id;
	}

	public void setFriend_id(String friend_id) {
		this.friend_id = friend_id;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getNot_id() {
		return not_id;
	}

	public void setNot_id(String not_id) {
		this.not_id = not_id;
	}



	public Notifications(String id,String name,String u_id,String u_name,String not,String n_id){
		this.friend_id=id;
		this.friend_name=name;
		this.user_id=u_id;
		this.user_name = u_name;
		this.note = not;
		this.not_id=n_id;
	}
	
	public String getFriend_name() {
		return friend_name;
	}
	public void setFriend_name(String friend_name) {
		this.friend_name = friend_name;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	public static ArrayList<Notifications> Notifiy(String user_id) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		ArrayList<Notifications> not = new ArrayList<Notifications>();
		Query gaeQuery = new Query("Notifications");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			// System.out.println(entity.getProperty("name").toString());
			if (entity.getProperty("friend_id").toString().equals(user_id)) {
				Notifications N=new Notifications(entity.getProperty("user_id")
						.toString(), entity.getProperty("user_name").toString(),
						entity.getProperty("friend_id").toString(),entity.getProperty("friend_name").toString()
						,entity.getProperty("note").toString(), Long.toString(entity.getKey().getId()));
				String s="com.FCI.SWE.Notification."+ entity.getProperty("type").toString();
				UserNotification notfify = (UserNotification) Class.forName(s).newInstance();
				N.setType(notfify.extract(N.friend_name,N.note));
				not.add(N);
			}
		}

		return not;
	}

	public String getNote() {
		return note;
	}

	public void setNote(String note) {
		this.note = note;
	}

	
}