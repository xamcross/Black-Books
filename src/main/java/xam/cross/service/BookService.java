package xam.cross.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import xam.cross.entity.Book;
import xam.cross.entity.Customer;
import xam.cross.entity.ShoppingCart;
import xam.cross.repository.BookRepository;
import xam.cross.repository.CustomerRepository;

@Service
@Transactional
public class BookService {

	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private CustomerRepository customerRepository;
	
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
		Customer customer = customerRepository.findByEmail(email);
		ShoppingCart cart = customer.getCart();
		cart.addBook(book);
		cart = cartService.save(cart);
	}

}
