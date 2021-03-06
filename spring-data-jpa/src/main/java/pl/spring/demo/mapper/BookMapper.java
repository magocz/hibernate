package pl.spring.demo.mapper;

import pl.spring.demo.entity.AuthorEntity;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.repository.LibraryRepository;
import pl.spring.demo.to.BookTo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

public class BookMapper {
	
	@Autowired
	private static LibraryRepository libraryRepository;
	
    public static BookTo map(BookEntity bookEntity) {
        if (bookEntity != null) {
            return new BookTo(bookEntity.getId(), bookEntity.getTitle(), mapAuthors(bookEntity.getAuthors()),bookEntity.getLibrary().getId());
        }
        return null;
    }

    public static BookEntity map(BookTo bookTo) {
        if (bookTo != null) {
            return new BookEntity(bookTo.getId(), bookTo.getTitle(), libraryRepository.getOne(bookTo.getLibraryId()));
        }
        return null;
    }

    public static List<BookTo> map2To(List<BookEntity> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    public static List<BookEntity> map2Entity(List<BookTo> bookEntities) {
        return bookEntities.stream().map(BookMapper::map).collect(Collectors.toList());
    }

    private static String mapAuthors(Collection<AuthorEntity> authors) {
        if (!authors.isEmpty()) {
            return authors.stream().map(authorEntity -> authorEntity.getFirstName() + " " + authorEntity.getLastName()).collect(Collectors.joining
                    (", "));
        }
        return null;
    }
}
