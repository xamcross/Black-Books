package xam.cross.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xam.cross.entity.Book;
import xam.cross.entity.ShoppingCart;
import xam.cross.repository.BookRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private ShoppingCartService cartService;
	
	public List<Book> findAll() {
		return bookRepository.findAll();
	}

	public void save(Book book) {
		bookRepository.save(book);
	}

	public Book findOne(int id) {
		return bookRepository.findOne(id);
	}

	public void addToCart(int id, String email) {
		Book book = bookRepository.findOne(id);
		ShoppingCart cart = cartService.findOne(email);
		List<Book> books = cart.getOrderedBooks();
		if (books == null) {
			books = new ArrayList<Book>();
		}
		books.add(book);
		cart.setOrderedBooks(books);
		List<ShoppingCart> carts = book.getCarts();
		if (carts == null){
			carts = new ArrayList<ShoppingCart>();
		}
		carts.add(cart);
		book.setCarts(carts);
		cartService.save(cart);
		bookRepository.save(book);
	}

}
