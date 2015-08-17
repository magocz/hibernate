package pl.spring.demo.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonDaoTest-context.xml")
public class LibraryDaoImplTest {
	@Autowired
	LibraryDao libraryDao;
	
	@Test
	public void shouldFindLibraryNyName(){
		 // given
        final String libraryName = "pier";
        // when
        List<LibraryEntity> librares = libraryDao.findLibraryByName(libraryName);
        // then
        assertNotNull(librares);
        assertEquals("Pierwsza Biblioteka", librares.get(0).getName());
	}
}
