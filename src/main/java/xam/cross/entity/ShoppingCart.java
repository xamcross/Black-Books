package xam.cross.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

@Entity
public class ShoppingCart {

	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Set<Book> orderedBooks = new HashSet<Book>();
	
	private double totalPrice;

	public double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Integer getId() {
		return id;
	}

	public void addBook(Book book){
		if (orderedBooks.contains(book)){
			return;
		}
		orderedBooks.add(book);
		System.out.println("book added = " + book.getPrice());
		totalPrice += book.getPrice();
	}
	
	public void removeBook(Book book){
		if (book != null){
			removeBookById(book.getId());
			return;
		}
	}
	
	public Set<Book> getOrderedBooks() {
		return new HashSet<Book>(orderedBooks);
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void removeBookById(int bookId) {
		for (Book book : orderedBooks){
			if (book.getId() == bookId){
				orderedBooks.remove(book);
				totalPrice -= book.getPrice();
				return;
			}
		}
	}
	
}
