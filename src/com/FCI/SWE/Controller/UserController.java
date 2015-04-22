package com.FCI.SWE.Controller;

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
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.FCI.SWE.Models.User;
import com.FCI.SWE.ServicesModels.UserEntity;
import com.FCI.SWE.ServicesModels.friendlist;

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
@Produces("text/html")
public class UserController {
	/**
	 * Action function to render Signup page, this function will be executed
	 * using url like this /rest/signup
	 * 
	 * @return sign up page
	 */
	@GET
	@Path("/signup")
	public Response signUp() {
		return Response.ok(new Viewable("/jsp/register")).build();
	}

	/**
	 * Action function to render home page of application, home page contains
	 * only signup and login buttons
	 * 
	 * @return enty point page (Home page of this application)
	 */
	@GET
	@Path("/")
	public Response index() {
		return Response.ok(new Viewable("/jsp/entryPoint")).build();
	}

	/**
	 * Action function to render login page this function will be executed using
	 * url like this /rest/login
	 * 
	 * @return login page
	 */
	@GET
	@Path("/login")
	public Response login() {
		return Response.ok(new Viewable("/jsp/login")).build();
	}




	/**
	 * Action function to response to signup request, This function will act as
	 * a controller part and it will calls RegistrationService to make
	 * registration
	 * 
	 * @param uname
	 *            provided user name
	 * @param email
	 *            provided user email
	 * @param pass
	 *            provided user password
	 * @return Status string
	 */
	@POST
	@Path("/response")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("uname") String uname,
			@FormParam("email") String email, @FormParam("password") String pass) {

		String serviceUrl = "http://socialnwfci2015.appspot.com/rest/RegistrationService";
		String urlParameters = "uname=" + uname + "&email=" + email
				+ "&password=" + pass;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}

	/**
	 * Action function to response to login request. This function will act as a
	 * controller part, it will calls login service to check user data and get
	 * user from datastore
	 * 
	 * @param uname
	 *            provided user name
	 * @param pass
	 *            provided user password
	 * @return Home page view
	 */
	@POST
	@Path("/home")
	@Produces("text/html")
	public Response home(@FormParam("uname") String uname,
			@FormParam("password") String pass) {
		String urlParameters = "uname=" + uname + "&password=" + pass;

		String retJson = Connection.connect(
				"http://socialnwfci2015.appspot.com/rest/LoginService", urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");

		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("Failed"))
				return null;
			Map<String, String> map = new HashMap<String, String>();
			User user = User.getUser(object.toJSONString());
			map.put("name", user.getName());
			map.put("email", user.getEmail());
			return Response.ok(new Viewable("/jsp/home", map)).build();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return null;

	}
	@POST
	@Path("/sendrequest")
	@Produces("text/html")
	public String sendrequest(@FormParam("email") String email1,@FormParam("email2") String email2)
	{
		String serviceUrl = "http://socialnwfci2015.appspot.com/rest/sendrequest";
		String urlParameters = "email=" + email1 + "&email2="+email2 ;
		String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
				return "Request sent Successfully";

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
	}
	@POST
	@Path("/searchUser")
	@Produces("text/html")
	public Response searchUser(@FormParam("email")String email1) throws ParseException
	{
		UserEntity user=UserEntity.searchUser(email1);
		if(user.searchUser(email1)==null)
		{
			return null;
		}
		return Response.ok(new Viewable("/jsp/search" , user)).build();
	}
	
	@POST
	@Path("/accept")
	@Produces("text/html")
	public String accept(@FormParam("email2") String email2)
	{
		String serviceUrl = "http://localhost:8888/rest/accept";
		String urlParameters = "email2="+email2 ;
		String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			
         if (object.get("Status").equals("OK")){
				return "Accept is done succefully";

			}
          else{
          object.get("Status").equals("Failed");
				return "no requests";


           }

		} 
                                      catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

		
	}
	@POST
	@Path("/newpage")
	@Produces("text/html")
	public Response newpage(@FormParam("owner") String owner,@FormParam("name") String name,
			@FormParam("cateagory") String cateagory)
	{
		  String serviceUrl = "http://socialnwfci2015.appspot.com/rest/CreatenewpageService";
		  String urlParameters = "owner=" + owner + "&name="+name + "cateagory="+cateagory ;
		  String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	         	JSONParser parser = new JSONParser();
	         	Object obj;
	    		try {
	    			obj = parser.parse(retJson);
	    			JSONObject object = (JSONObject) obj;
	    			if (object.get("Status").equals("OK"))
	    			return Response.ok(new Viewable("/jsp/page")).build();
	    			} catch (ParseException e)
	    			  {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			  }
				return null;
	    		
	    		}
	@POST
	@Path("/newpost")
	@Produces("text/html")
	public Response newpage(@FormParam("user") String user_name, @FormParam("UID") String user_ID, @FormParam("feeling") String feeling,
			@FormParam("content") String content, @FormParam("type") String type)
	{
		  String serviceUrl = "http://socialnwfci2015.appspot.com/rest/CreatePostService";
		  String urlParameters = "user=" + user_name + "UID="+user_ID + "feeling="+feeling +"content="+content +"type="+type ;
		  String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	         	JSONParser parser = new JSONParser();
	         	Object obj;
	    		try {
	    			obj = parser.parse(retJson);
	    			JSONObject object = (JSONObject) obj;
	    			if (object.get("Status").equals("OK"))
	    			return Response.ok(new Viewable("/jsp/post")).build();
	    			} catch (ParseException e)
	    			  {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			  }
				return null;
	}
	@POST
	@Path("/notify")
	@Produces("text/html")
	public Response newpage(@FormParam("UserID") String UserID)
	{
		  String serviceUrl = "http://socialnwfci2015.appspot.com/rest/NotificationsService";
		  String urlParameters = "UserIDr=" + UserID  ;
		  String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
	         	JSONParser parser = new JSONParser();
	         	Object obj;
	    		try {
	    			obj = parser.parse(retJson);
	    			JSONObject object = (JSONObject) obj;
	    			if (object.get("Status").equals("OK"))
	    			return Response.ok(new Viewable("/jsp/notify")).build();
	    			} catch (ParseException e)
	    			  {
	    				// TODO Auto-generated catch block
	    				e.printStackTrace();
	    			  }
				return null;
	    		
	    		}
	
	@POST
	@Path("/massege")
	@Produces("text/html")
	public String massege(@FormParam("email1") String email1,@FormParam("email2") String email2,@FormParam("massege") String massege)
	{
		String serviceUrl = "http://socialnwfci2015.appspot.com/rest/sendrequest";
		String urlParameters = "email1=" + email1 + "&email2="+email2 +"&massege="+massege;
		String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
				return "Massege sent Successfully";

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	@POST
	@Path("/Group")
	@Produces("text/html")
	public String Group(@FormParam("email") String email,@FormParam("nameconv") String nameconv,@FormParam("massege") String massege)
	{
		String serviceUrl = "http://socialnwfci2015.appspot.com/rest/sendrequest";
		String urlParameters = "email=" + email + "&nameconv="+nameconv +"&massege="+massege;
		String retJson = Connection.connect(
				serviceUrl, urlParameters,
				"POST", "application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK")){
				return "Massege sent Successfully";

			}
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}
	
	@POST
	@Path("/addpepole")
	@Produces(MediaType.TEXT_PLAIN)
	public String response(@FormParam("nameconv") String nameconv,@FormParam("email") String email) {

		String serviceUrl = "http://socialnwfci2015.appspot.com/rest/RegistrationService";
		String urlParameters = "nameconv=" + nameconv + "&email=" + email;
		String retJson = Connection.connect(serviceUrl, urlParameters, "POST",
				"application/x-www-form-urlencoded;charset=UTF-8");
		JSONParser parser = new JSONParser();
		Object obj;
		try {
			// System.out.println(retJson);
			obj = parser.parse(retJson);
			JSONObject object = (JSONObject) obj;
			if (object.get("Status").equals("OK"))
				return "Registered Successfully";

		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		/*
		 * UserEntity user = new UserEntity(uname, email, pass);
		 * user.saveUser(); return uname;
		 */
		return "Failed";
	}
	
	
}