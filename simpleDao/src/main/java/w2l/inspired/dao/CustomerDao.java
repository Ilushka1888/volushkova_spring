package w2l.inspired.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import w2l.inspired.model.Customer;

import java.util.Optional;

@Repository
public interface CustomerDao extends ListCrudRepository<Customer, Integer> {
    Optional<Customer> findByName(String name);
}
