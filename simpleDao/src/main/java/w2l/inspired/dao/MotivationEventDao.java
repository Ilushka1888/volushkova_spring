package w2l.inspired.dao;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.stereotype.Repository;
import w2l.inspired.model.MotivationEvent;

import java.util.List;

@Repository
public interface MotivationEventDao extends ListCrudRepository<MotivationEvent, Long> {
    List<MotivationEvent> findByCustomerIdOrderByCreatedAtDesc(Integer customerId);
}
