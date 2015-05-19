package Testing;

import org.json.simple.parser.ParseException;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FCI.SWE.Controller.UserController;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class Controllertest {
	private final LocalServiceTestHelper helper1 =
			new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig());

			@BeforeClass
			public void setUp() {
			helper1.setUp();
			}

			@AfterClass
			public void tearDown() {
			helper1.tearDown();
			}
		
	
  @Test
  public void responsetest() {
	  UserController c = new UserController();
	  Assert.assertEquals("Registered Successfully", c.response("a", "a@yahoo.com", "1"));
  }
  
  @Test
  public void sendrequesttest() {
	  UserController c = new UserController();
	  Assert.assertEquals("Request sent Successfully", c.sendrequest("a@yahoo.com","b@yahoo.com"));
  }
  
  @Test
  public void searchusertest() throws ParseException {
	  UserController c = new UserController();
	  String x =null;
	  Assert.assertEquals(x, c.searchUser("b@yahoo.com"));
  }
  
  public void accepttest() {
	  UserController c = new UserController();
	  Assert.assertEquals("Accept is done succefully", c.accept("b@yahoo.com"));
  }
  
  @Test
  public void creatpagetest() throws ParseException {
	  UserController c = new UserController();
	  String x =null;
	  Assert.assertEquals(x, c.newpage("a", "programing", "coding"));
  }
  
  @Test
  public void creatposttest() throws ParseException {
	  UserController c = new UserController();
	  String x =null;
	  Assert.assertEquals(x, c.newpost("Ahmed", "1", "m5noooo2", "kfaya b2a kda 3awzen nzakr", "public"));
  }
  
  @Test
  public void notifytest() throws ParseException {
	  UserController c = new UserController();
	  String x =null;
	  Assert.assertEquals(x, c.notify("2"));
  }
  
  public void messagetest() {
	  UserController c = new UserController();
	  Assert.assertEquals("Massege sent Successfully", c.massege("a@yahoo.com","b@yahoo.com","a ya zoft :D"));
  }

  
}
