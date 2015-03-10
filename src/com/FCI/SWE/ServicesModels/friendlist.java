package com.FCI.SWE.ServicesModels;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class friendlist {
	private String email1;
	private String email2;
	
	public friendlist(String email1 , String email2) {
		this.email1 = email1;
		this.email2 = email2;
	}
	private void setEmail1(String email){
		this.email1 = email;
	}
	private void setEmail2(String email){
		this.email2 = email;
	}
	private String getEmail1(){
		return email1;
	}
	private String getEmail2(){
		return email2;
	}
	
	public static friendlist sendrequest(String email, String email2) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("users");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("email").toString().equals(email)|| entity.getProperty("email2").toString().equals(email2)) {
				entity.setProperty("status", "active");
				datastore.put(entity);
				//return true;
			}
		}

		return null;
	}

}
