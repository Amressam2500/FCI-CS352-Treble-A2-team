package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class friendlist {
	private String email1;
	private String email2;
	private String status;
	
	public friendlist(String email1 , String email2,String status) {
		this.email1 = email1;
		this.email2 = email2;
		this.status=status;
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
	private void setstatus(String status){
		this.status = status;
	}
	private String getstatus(){
		return status;
	}
	
	public Boolean savefriendlist() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("users", list.size() + 1);

		employee.setProperty("email", this.email1);
		employee.setProperty("email2", this.email2);
		employee.setProperty("status", this.status);
		datastore.put(employee);

		return true;
	}
	
	public Boolean  sendrequest(String email, String email2) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("friends");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("email").toString().equals(email)|| entity.getProperty("email2").toString().equals(email2)) {
				entity.setProperty("status", "active");
				datastore.put(entity);
				return true;
			}
		}

		return null;
	}

public static friendlist getrequest( String email2 ) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();

		Query gaeQuery = new Query("request");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity list : pq.asIterable()) {
			if (list.getProperty("email2").toString().equals(email2)) {
friendlist returnedrequest = new friendlist(list.getProperty("name").toString(), list.getProperty("email").
			toString(), list.getProperty("password").toString());
				//returnedrequest.setId(list.getKey().getId());
				return returnedrequest;
			}
		}

		return null;
	}

}

