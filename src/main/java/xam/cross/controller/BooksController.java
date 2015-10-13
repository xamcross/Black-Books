package xam.cross.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import xam.cross.entity.Book;
import xam.cross.service.BookService;

@Controller
public class BooksController {

	@Autowired
	private BookService bookService;
	
	@ModelAttribute("book")
	@Transactional
	public Book construct(){
		Book book = new Book();
		return book;
	}
	
	@RequestMapping(value="/books", method=RequestMethod.GET)
	public String getAllBooks(Model model){
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "books";
	}
	
	@RequestMapping(value="/books/{id}", method=RequestMethod.POST)
	public String getAllBooks(@PathVariable("id") int id, Model model, Principal principal){
		bookService.addToCart(id, principal.getName());
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "redirect:/books?bookAdded=true";
	}
	
	@RequestMapping(value="/addBook", method=RequestMethod.GET)
	public String showAddBookForm(){
		return "addBook";
	}
	
	@RequestMapping(value="/addBook", method=RequestMethod.POST)
	public String addNewBook(@ModelAttribute("book") Book book, Model model){
		bookService.save(book);
		List<Book> books = bookService.findAll();
		model.addAttribute("books", books);
		return "books";
	}
	
}
