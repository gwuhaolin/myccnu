package study.book;

import org.junit.Assert;
import org.junit.Test;

import java.util.List;

public class SearchBookTest {

	@Test
	public void testGet() throws Exception {
		List<SearchBook.MyBook> re = SearchBook.get("java", "1");
		Assert.assertTrue(re.size() > 0);
	}
}