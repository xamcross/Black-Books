package xam.cross.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import xam.cross.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Integer>{

}
