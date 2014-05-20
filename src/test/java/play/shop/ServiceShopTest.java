package play.shop;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ServiceShopTest {

	ServiceShop serviceShop = new ServiceShop();

	@Before
	public void setUp() throws Exception {

	}

	@After
	public void tearDown() throws Exception {

	}

	@Test
	public void testAddOne() throws Exception {
	}



	@Test
	public void testGetOne() throws Exception {
		Assert.assertNotNull(serviceShop.getOne(1));
	}

	@Test
	public void testGetPage() throws Exception {
	}

	@Test
	public void testSearch() throws Exception {

	}

	@Test
	public void testLikeIt() throws Exception {

	}
}