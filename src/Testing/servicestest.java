package Testing;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FCI.SWE.Services.UserServices;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class servicestest {
	
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
  public void registrationtest() {
	  UserServices s=new UserServices();
	  String x="Status"+"OK";
	  s.registrationService("a", "a@yahoo.com", "1");
	  Assert.assertEquals( x, s.registrationService("a", "a@yahoo.com", "1"));	 
  }
  
  @Test
  public void logintest() {
	  UserServices s=new UserServices();
	  String x="Status"+"OK";
	  s.registrationService("a", "a@yahoo.com", "1");
	  Assert.assertEquals( x, s.loginService("a", "1"));	 
  }
}
