package pl.spring.demo.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import pl.spring.demo.entity.LibraryEntity;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "CommonRepositoryTest-context.xml")
public class LibraryRepositoryTest {
    @Autowired
    private LibraryRepository libraryRepository;
    @Autowired
    private BookRepository bookRepository;
    
    @Test
    public void testShouldFindLibraryById() {
        // given
        final long bookId = 1;
        // when
        LibraryEntity libraryEntity = libraryRepository.findOne(bookId);
        // then
        assertNotNull(libraryEntity);
        assertEquals("Pierwsza Biblioteka", libraryEntity.getName());
    }

    @Test
    public void testShouldFindLibrarysByName() {
        // given
        final String libraryName = "t";
        // when
        List<LibraryEntity> libraryEntity = libraryRepository.findLibraryByName(libraryName);
        // then
        assertNotNull(libraryEntity);
        assertFalse(libraryEntity.isEmpty());
        assertEquals("Trzecia Biblioteka", libraryEntity.get(0).getName());
    }  
    
    @Test
    public void testShouldRemoveLibraryWhithBooks() {
        // when
        libraryRepository.delete(2L);
        // then
        assertEquals(2, bookRepository.count());
    }  
}
