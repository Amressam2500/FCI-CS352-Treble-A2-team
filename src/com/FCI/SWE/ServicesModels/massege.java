package com.FCI.SWE.ServicesModels;

import java.util.List;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class massege {
	private String email1;
	private String email2;
	private String massege;
	

public massege(String email1,String email2,String massege) {
	this.email1 = email1;
	this.email2 = email2;
	this.massege=massege;
}
private void setemail1(String email1){
	this.email1 = email1;
}
private void setemail2(String email2){
	this.email2 = email2;
}
private String getemail1(){
	return email1;
}
private String getemail2(){
	return email2;
}
private void setmassege(String massege){
	this.massege = massege;
}
private String getmassege(){
	return massege;
}

public Boolean savemassege() {
	DatastoreService datastore = DatastoreServiceFactory
			.getDatastoreService();
	Query gaeQuery = new Query("massege");
	PreparedQuery pq = datastore.prepare(gaeQuery);
	List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

	Entity employee = new Entity("massege", list.size() + 1);

	employee.setProperty("email1", this.email1);
	employee.setProperty("email2", this.email2);
	employee.setProperty("massege", this.massege);
	datastore.put(employee);

	return true;
}




}
