package com.FCI.SWE.Services;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.glassfish.jersey.server.mvc.Viewable;
import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.ServicesModels.Notifications;
import com.FCI.SWE.ServicesModels.Post;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.FCI.SWE.ServicesModels.conversation;
import com.FCI.SWE.ServicesModels.friendlist;
import com.FCI.SWE.ServicesModels.page;

/**
 * This class contains REST services, also contains action function for web
 * application
 * 
 * @author Mohamed Samir
 * @version 1.0
 * @since 2014-02-12
 *
 */
@Path("/")
@Produces(MediaType.TEXT_PLAIN)
public class UserServices {
	
	
	/*@GET
	@Path("/index")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}*/


		/**
	 * Registration Rest service, this service will be called to make
	 * registration. This function will store user data in data store
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided password
	 * @return Status json
	 */
	@POST
	@Path("/RegistrationService")
	public String registrationService(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {
		UserEntity user = new UserEntity(uname, email, pass);
		user.saveUser();
		JSONObject object = new JSONObject();
		object.put("Status", "OK");
		return object.toString();
	}

	/**
	 * Login Rest Service, this service will be called to make login process
	 * also will check user data and returns new user from datastore
	 * @param uname provided user name
	 * @param pass provided user password
	 * @return user in json format
	 */
	@POST
	@Path("/LoginService")
	public String loginService(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		JSONObject object = new JSONObject();
		UserEntity user = UserEntity.getUser(uname, pass);
		if (user == null) {
			object.put("Status", "Failed");

		} else {
			object.put("Status", "OK");
			object.put("name", user.getName());
			object.put("email", user.getEmail());
			object.put("password", user.getPass());
			object.put("id", user.getId());
		}
		return object.toString();

		
	}
	/**
	 * send request service, this service will be called to make
	 * send request. This function will send request to another user
	 * 
	 * @param email1
	 *            provided user1 email
	 * @param email2
	 *            provided user2 email
	 * @return Status json
	 */
	@POST
	@Path("/sendrequest")
	public String SendRequest(@FormParam("email") String email1,@FormParam("email2") String email2 ){

		JSONObject object = new JSONObject();
                UserEntity user1 = UserEntity.getUser(email1);
                UserEntity user2 = UserEntity.getUser(email2);
                if (user1 == null && user2 == null ) {
		   object.put("Status", "Failed");
                    }
           else
                 {
                    object.put("Status", "OK");
                    friendlist list = new friendlist(email1, email2, "waiting");
        			list.savefriendlist();
	             }
         
                return object.toString();
	}
	/**
	 * accept request service, this service will be called to make
	 * an accept request. This function will accept request from another user
	 * 
	 * @param email1
	 *            provided user1 email
	 * @param email2
	 *            provided user2 email
	 * @return Status json
	 */
@POST
@Path("/accept")
public String accept(@FormParam("email") String email1,@FormParam("email2") String email2 ){

JSONObject object = new JSONObject();

 friendlist user= friendlist.getrequest(email2);

 if (user == null ) {
object.put("Status", "Failed");
     }
else
  {
     object.put("Status", "OK");
     friendlist list = new friendlist(email1 , email2 , "accept");
		list.savefriendlist();
  }

 return object.toString();
}
/**
 * notification service, this service will be called to make
 * notify. This function will notify to user
 * 
 * @param user id
 *            provided user id
 * @return Status json
 */
@POST
@Path("/NotificationsService")
public String NotificationsService(@FormParam("userid") String user_id)
		throws InstantiationException, IllegalAccessException,
		ClassNotFoundException {
	JSONObject object = new JSONObject();
	JSONArray array = new JSONArray();
	ArrayList<Notifications> not = Notifications.Notifiy(user_id);

	if (not.size() == 0) {
		System.out.println("null");
		object.put("Status", "Failed");
		array.add(object);
	} else {

		for (int i = 0; i < not.size(); i++) {
			// object.put("name", user.get(i));
			JSONObject not1 = new JSONObject();
			not1.put("Status", "OK");
			not1.put("user_name", not.get(i).getUser_name());
			not1.put("user_id", not.get(i).getUser_id());
			not1.put("friend_name", not.get(i).getFriend_name());
			not1.put("not_id", not.get(i).getNot_id());
			not1.put("type", not.get(i).getType());
			not1.put("note", not.get(i).getNote());
			array.add(not1);

		}

	}
	System.out.println("size " + array.size());
	return array.toJSONString();
}

/**
 * create new page service, this service will be called to create
 * new page. This function will create page to  user
 * 
 * @param owner
 *            provided user name
 * @param name
 *            provided page name
 * @param category
 *            provided page category
 * @return Status json
 */
@POST
@Path("/CreatenewpageService")
public String CreatenewPageService(@FormParam("owner") String owner,
		@FormParam("name") String name,
		@FormParam("cateagory") String cateagory)
		{
	JSONObject object = new JSONObject();

	page P = new page();
	if (P.newpage(owner,name, cateagory) != "done") {
		object.put("Status", "Failed");
	} else {
		object.put("Status", "OK");
	}
	return object.toString();

}
/**
 * create new post service, this service will be called to create
 * new post. This function will create post to  user
 * 
 * @param user
 *            provided user name
 * @param user_ID
 *            provided user id
 * @param feeling
 *            provided user feeling
 * @param content
 *            provided post content
 * @param type
 *            provided post type                      
 * @return Status json
 */

@POST
@Path("/CreatePostService")
public String CreatePostService(@FormParam("user") String user_name,
		@FormParam("UID") String user_ID,
		@FormParam("feeling") String feeling,
		@FormParam("content") String content, @FormParam("type") String type) {

	System.out.print(user_name);
	JSONObject object = new JSONObject();

	Post P = new Post();
	if (P.newpost(user_ID, user_name, feeling, content, type) != "post") {
		object.put("Status", "Failed");
	} else {
		object.put("Status", "OK");
	}
	return object.toString();

}

/**
 * create new message service, this service will be called to create
 * new message. This function will create message from  user
 * to another
 * @param email1
 *            provided user1 email
 * @param email2
 *            provided user2 email            
 * @param message
 *            provided message content                       
 * @return Status json
 */
@POST
@Path("/massege")
public String massege(@FormParam("email1") String email1,@FormParam("email2") String email2 ,@FormParam("massege") String massege ){

	JSONObject object = new JSONObject();
            UserEntity user1 = UserEntity.getUser(email1);
            UserEntity user2 = UserEntity.getUser(email2);
            if (user1 == null && user2 == null ) {
	   object.put("Status", "Failed");
                }
       else
             {
                object.put("Status", "OK");
               com.FCI.SWE.ServicesModels.massege list = new com.FCI.SWE.ServicesModels.massege(email1 , email2 , massege);
    		   list.savemassege();
             }
     
            return object.toString();
}


@POST
@Path("/Group")
public String Group(@FormParam("email") String email,@FormParam("nameconv") String nameconv ,@FormParam("massege") String massege ){

	JSONObject object = new JSONObject();
            conversation user = conversation.getconv(nameconv);
  
            if (user == null  ) {
	   object.put("Status", "Failed");
                }
       else
             {
                object.put("Status", "OK");
               com.FCI.SWE.ServicesModels.Group list = new com.FCI.SWE.ServicesModels.Group(email);
    		   list.saveGroup();
             }
     
            return object.toString();
}

@POST
@Path("/addpeople")
public String registrationService(@FormParam("nameconv") String nameconv,@FormParam("email") String email) {
	conversation user = new conversation( nameconv,email);
	user.saveconv();
	JSONObject object = new JSONObject();
	object.put("Status", "OK");
	return object.toString();

}



}



