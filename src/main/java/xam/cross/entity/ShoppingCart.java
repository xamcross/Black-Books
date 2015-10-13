package xam.cross.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class ShoppingCart {

	
	@Id
	@GeneratedValue
	private Integer id;
	
	@ManyToMany(mappedBy="carts")
	private List<Book> orderedBooks;
	
	private Double totalPrice;
	
	@OneToOne
	private Customer customer;

	public Double getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public Integer getId() {
		return id;
	}

	public List<Book> getOrderedBooks() {
		return orderedBooks;
	}

	public void setOrderedBooks(List<Book> orderedBooks) {
		this.orderedBooks = orderedBooks;
		this.totalPrice = 0.0;
		for (Book book : this.orderedBooks){
			this.totalPrice += book.getPrice();
		}
	}

	public void setId(Integer id) {
		this.id = id;
	}
	
}
