package pl.spring.demo.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "LIBRARY")
public class LibraryEntity implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id = null;

	@Column(nullable = false, length = 50)
	private String name;

	@OneToMany(mappedBy = "library" , cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)	
	private Collection<BookEntity> books;

	// for hibernate
	protected LibraryEntity() {
	}

	public LibraryEntity(String name) {
		this.name = name;
	}

	public LibraryEntity(Long id, String name) {
		this.name = name;
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Collection<BookEntity> getBooks() {
		return books;
	}

	public void setBooks(Collection<BookEntity> books) {
		this.books = books;
	}

	public void addBook(BookEntity bookEntity) {
		this.books.add(bookEntity);
	}

}
