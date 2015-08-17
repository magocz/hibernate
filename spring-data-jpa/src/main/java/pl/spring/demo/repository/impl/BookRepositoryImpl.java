package pl.spring.demo.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;


import com.mysema.query.BooleanBuilder;
import com.mysema.query.jpa.impl.JPAQuery;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;
import pl.spring.demo.entity.QBookEntity;

public class BookRepositoryImpl implements SearchByCriteria{
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public List<BookEntity> findBooksByCriteria(BookSearchCriteria bookSearchCriteria) {
		QBookEntity bookEntity = QBookEntity.bookEntity;
		JPAQuery jpaQuery = new JPAQuery(entityManager).from(bookEntity);
		BooleanBuilder restrictions = new BooleanBuilder();

		if (bookSearchCriteria.getTitle() != null) {
			restrictions.and(bookEntity.title.startsWithIgnoreCase(bookSearchCriteria.getTitle()));
		}
		if (bookSearchCriteria.getLibraryName() != null) {
			restrictions.and(bookEntity.library.name.startsWithIgnoreCase(bookSearchCriteria.getLibraryName()));
		}
		if (bookSearchCriteria.getAuthor() != null) {
			restrictions.and(bookEntity.authors.any().firstName.startsWithIgnoreCase(bookSearchCriteria.getAuthor())
					.or(bookEntity.authors.any().lastName.startsWithIgnoreCase(bookSearchCriteria.getAuthor())));
		}		
		return jpaQuery.where(restrictions).list(bookEntity);
	}
}
