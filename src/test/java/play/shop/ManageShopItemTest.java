package play.shop;

import org.junit.Test;

public class ManageShopItemTest {

  @Test
  public void testSearch_page() throws Exception {
    ManageShopItem.search_page(0, "的,的,的");
  }
}
