package Testing;

import org.testng.Assert;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;


/*import org.junit.After;
import org.junit.Before;
import org.junit.Test;
*/
import static com.google.appengine.api.datastore.FetchOptions.Builder.withLimit;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
//import static org.junit.Assert.assertEquals
import org.testng.annotations.Test;

import com.FCI.SWE.ServicesModels.UserEntity;

public class NewTest {
  
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
  public void getusertest() {
	  UserEntity c = new UserEntity("a", "a@yahoo.com", "1");
	  c.setEmail("a@yahoo.com");
	 String X=c.getEmail();
	  Assert.assertEquals(c.getUser("a@yahoo.com"),X);
  }
  @Test
  public void saveusertest() {
	  UserEntity c = new UserEntity("a", "a@yahoo.com", "1");
	  boolean f=c.saveUser();
	 Assert.assertEquals(f,true);    
  }
  
  @Test
  public void searchusertest() {
	  UserEntity c = new UserEntity("a", "a@yahoo.com", "1");
	  String X=c.getEmail();
	 Assert.assertEquals(c.searchUser("a@yahoo.com"),X);    
  }
    
}
