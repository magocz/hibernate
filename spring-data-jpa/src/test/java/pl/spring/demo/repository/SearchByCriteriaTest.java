package pl.spring.demo.repository;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.repository.impl.BookRepositoryImpl;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class SearchByCriteriaTest {
	@Autowired
	BookRepositoryImpl bookRepositoryImpl;
	
	private BookSearchCriteria bookSearchCriteria;
	
	@Before
	public void createNewBookSearchCriteria(){
		this.bookSearchCriteria = new BookSearchCriteria();
	}
	
	@Test
	public void shouldFindBookByAuthor(){
		bookSearchCriteria.setAuthor("jan");
		Assert.assertEquals(2,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
		Assert.assertEquals("Pierwsza książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(0).getTitle());
	}
	
	@Test
	public void shouldFindBookByTitle(){
		bookSearchCriteria.setTitle("p");
		Assert.assertEquals(1,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
		Assert.assertEquals("Pierwsza książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(0).getTitle());
	}
	
	@Test
	public void shouldFindBookByLibraryName(){
		bookSearchCriteria.setLibraryName("pier");
		Assert.assertEquals(2,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
		Assert.assertEquals("Pierwsza książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(0).getTitle());
		Assert.assertEquals("Trzecia książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(1).getTitle());
	}
	
	@Test
	public void shouldFindBookByLibraryNameAndAuthor(){
		bookSearchCriteria.setLibraryName("pier");
		bookSearchCriteria.setAuthor("jank");
		Assert.assertEquals(1,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
		Assert.assertEquals("Trzecia książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(0).getTitle());
	}
	
	@Test
	public void shouldFindBookByLibraryNameAndTitle(){
		bookSearchCriteria.setLibraryName("pier");
		bookSearchCriteria.setTitle("t");
		Assert.assertEquals(1,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
		Assert.assertEquals("Trzecia książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(0).getTitle());
	}
	
	@Test
	public void shouldFindBookByAuthorAndTitle(){
		bookSearchCriteria.setAuthor("j");
		bookSearchCriteria.setTitle("t");
		Assert.assertEquals(1,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
		Assert.assertEquals("Trzecia książka",bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).get(0).getTitle());
	}
	
	@Test
	public void shouldFindAllBooks(){		
		Assert.assertEquals(3,bookRepositoryImpl.findBooksByCriteria(bookSearchCriteria).size());
	}

}
