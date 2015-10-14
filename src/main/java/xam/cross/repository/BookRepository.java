package xam.cross.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xam.cross.entity.Book;

public interface BookRepository extends JpaRepository<Book, Integer> {

}
