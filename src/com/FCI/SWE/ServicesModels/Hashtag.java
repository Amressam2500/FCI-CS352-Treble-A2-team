package com.FCI.SWE.ServicesModels;



import java.util.List;

import org.json.simple.JSONArray;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Hashtag {
	public String ID;
	public String Name;
	public String Post_Id;

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getPost_Id() {
		return Post_Id;
	}

	public void setPost_Id(String post_Id) {
		Post_Id = post_Id;
	}

	public Boolean AddHashtag(String name,String id) {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("Hashtag");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("Hashtag", list.size() + 2);
		employee.setProperty("Name", name);
		employee.setProperty("Post_Id", id);

		datastore.put(employee);

		return true;
	}

}
