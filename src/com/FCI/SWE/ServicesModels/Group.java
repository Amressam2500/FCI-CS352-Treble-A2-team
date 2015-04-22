package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Group {
	private String email;
	private String nameconv;
	private String massege;
	
	public Group(String email) {
		this.email = email;
		this.nameconv = nameconv;
		this.massege=massege;
	}
	private void setemail(String email){
		this.email = email;
	}
	private void setnameconv(String nameconv){
		this.nameconv = nameconv;
	}
	private String getemail(){
		return email;
	}
	private String getnameconv(){
		return nameconv;
	}
	private void setmassege(String massege){
		this.massege = massege;
	}
	private String getmassege(){
		return massege;
	}

	
	public Boolean saveGroup() {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Group");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Group", list.size() + 1);

		employee.setProperty("email", this.email);
		employee.setProperty("nameconv", this.nameconv);
		employee.setProperty("massege", this.massege);
		datastore.put(employee);

		return true;
	}
	
	
	
	
}
