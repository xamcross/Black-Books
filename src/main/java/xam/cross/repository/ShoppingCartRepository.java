package xam.cross.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xam.cross.entity.Customer;
import xam.cross.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{

	ShoppingCart findByCustomer(Customer customer);

}
