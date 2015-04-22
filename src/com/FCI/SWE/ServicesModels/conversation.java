package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class conversation {

	private String nameconv;
	private String email;
	
	public conversation(String nameconv,String email) {
		this.nameconv = nameconv;
		this.email = email;
	}
	
	private void setnameconv(String nameconv){
		this.nameconv = nameconv;
	}
	private void setemail(String email){
		this.email = email;
	}
	private String getnameconv(){
		return nameconv;
	}
	private String getemail(){
		return email;
	}
	public Boolean saveconv() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("conversation", list.size() + 1);

		employee.setProperty("nameconv", this.nameconv);
		employee.setProperty("email", this.email);
		datastore.put(employee);

		return true;
	}
	
	
	public static conversation getconv(String nameconv) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService(); 
		Query gaeQuery = new Query("conversation");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		for (Entity entity : pq.asIterable()) {
			if (entity.getProperty("nameconv").toString().equals(nameconv)) {
				conversation returnednameconv = new conversation(entity.getProperty("nameconv").toString(), entity.getProperty("email").toString());
				//returnednameconv.setId(entity.getKey().getId());	
				return returnednameconv;
			}
		}
		
        return null;
	}
	
}
