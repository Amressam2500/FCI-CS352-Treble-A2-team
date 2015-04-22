package com.FCI.SWE.ServicesModels;



import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entities;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;

public class Post {
	public String ID;
	public String user_ID;
	public String user_name;
	public String post_ID;
	public String feelings;
	public String content;
	public String type;
	public String security;
	public String notes;
	public JSONArray Likes = new JSONArray();

	public String getID() {
		return ID;
	}

	public void setID(String iD) {
		ID = iD;
	}

	public String getUser_ID() {
		return user_ID;
	}

	public void setUser_ID(String user_ID) {
		this.user_ID = user_ID;
	}

	public String getUser_name() {
		return user_name;
	}

	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}

	public String getPost_ID() {
		return post_ID;
	}

	public void setPost_ID(String post_ID) {
		this.post_ID = post_ID;
	}

	public String getFeelings() {
		return feelings;
	}

	public void setFeelings(String feelings) {
		this.feelings = feelings;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSecurity() {
		return security;
	}

	public void setSecurity(String security) {
		this.security = security;
	}

	public String getNotes() {
		return notes;
	}

	public void setNotes(String notes) {
		this.notes = notes;
	}

	public String newpost(String user_id, String user_name, String feeling,
			String content, String type) {

		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query gaeQuery = new Query("post");
		PreparedQuery pq = datastore.prepare(gaeQuery);
		List<Entity> list = pq.asList(FetchOptions.Builder.withDefaults());

		Entity employee = new Entity("post", list.size() + 2);
		employee.setProperty("feeling", feeling);
		employee.setProperty("user_name", user_name);
		employee.setProperty("user_id", user_id);
		employee.setProperty("content", content);
		employee.setProperty("type", type);
		employee.setProperty("Likes", Likes.toJSONString());
		datastore.put(employee);

		Hashtag H = new Hashtag();
		List hash = gethashes(content);

		for (int i = 0; i < hash.size(); i++) {
			H.AddHashtag(hash.get(i).toString(),
			String.valueOf(list.size() + 1));
		}

		return "post";

	}

	public List gethashes(String token) {

		List<String> hashtags = new ArrayList<String>();
		StringTokenizer tokenizer = new StringTokenizer(token);

		while (tokenizer.hasMoreTokens()) {
			token = tokenizer.nextToken();
			if (token.startsWith("@")) {
				if (!hashtags.contains(token))
					hashtags.add(token);

			}
		}

		for (int i = 0; i < hashtags.size(); i++) {
			System.out.println(hashtags.get(i));

		}

		return hashtags;

	}

	
	public Boolean LikePost(String postid, String userid) throws ParseException {
		DatastoreService datastore = DatastoreServiceFactory
				.getDatastoreService();
		Query q = new Query("Post");
		PreparedQuery pq = datastore.prepare(q);

		for (Entity entity : pq.asIterable()) {

			if (Long.toString(entity.getKey().getId()).equals(postid)) {

				JSONParser parser = new JSONParser();
				Object obj = parser.parse(entity.getProperty("Likes")
						.toString());
				JSONArray array = (JSONArray) obj;
				if (array.contains(userid)) {
					return true;
				} else {
					array.add(userid);
					entity.setProperty("Likes", array.toJSONString());
					datastore.put(entity);
					return true;
				}

			}
		}

		return false;

	}
}
