package Testing;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.FCI.SWE.ServicesModels.UserEntity;
import com.FCI.SWE.ServicesModels.friendlist;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class friendlisttest {
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
  public void savefriendlisttest() {
	  friendlist f=new friendlist("a@yahoo.com", "b@yahoo.com", "active");;
	  boolean c=f.savefriendlist();
	 Assert.assertEquals(c,true);    
  }
  
  @Test
  public void sendrequesttest() {
	  friendlist f=new friendlist("a@yahoo.com", "b@yahoo.com", "binding");
	  f.setEmail1("a@yahoo.com");
	  f.setEmail2("b@yahoo.com");
	  boolean c=f.sendrequest("a@yahoo.com","b@yahoo.com");
	 Assert.assertEquals(c,true);    
  }
  @Test
  public void getusertest() {
	  friendlist c = new friendlist("a", "a@yahoo.com", "1");
	  c.setEmail1("a@yahoo.com");
	 String X=c.getEmail1();
	  Assert.assertEquals(c.getrequest("a@yahoo.com"),null);
  }
}
