package xam.cross.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import xam.cross.entity.Book;
import xam.cross.entity.ShoppingCart;

public interface BookRepository extends JpaRepository<Book, Integer> {

	List<Book> findByCarts(ShoppingCart cart);

}
