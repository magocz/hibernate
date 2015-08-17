package pl.spring.demo.repository.impl;

import java.util.List;

import pl.spring.demo.criteria.BookSearchCriteria;
import pl.spring.demo.entity.BookEntity;

public interface SearchByCriteria {
	public List<BookEntity> findBooksByCriteria(BookSearchCriteria bookSearchCriteria);
}
