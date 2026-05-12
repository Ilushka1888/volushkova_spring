package w2l.inspired.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.dao.MotivationEventDao;
import w2l.inspired.model.Customer;
import w2l.inspired.model.MotivationEvent;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class MotivationEventService {
    private final CustomerDao customerDao;
    private final MotivationEventDao motivationEventDao;

    public MotivationEventService(CustomerDao customerDao, MotivationEventDao motivationEventDao) {
        this.customerDao = customerDao;
        this.motivationEventDao = motivationEventDao;
    }

    @Transactional
    public MotivationEvent addEvent(Integer customerId, String description, int points) {
        Customer customer = customerDao.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Customer with id " + customerId + " not found"));
        return motivationEventDao.save(new MotivationEvent(customer, description, points));
    }

    @Transactional(readOnly = true)
    public List<MotivationEvent> getEvents() {
        return motivationEventDao.findAll();
    }

    @Transactional(readOnly = true)
    public List<MotivationEvent> getEventsByCustomer(Integer customerId) {
        return motivationEventDao.findByCustomerIdOrderByCreatedAtDesc(customerId);
    }

    @Transactional(readOnly = true)
    public int getTotalPoints() {
        return getEvents().stream().mapToInt(MotivationEvent::getPoints).sum();
    }

    @Transactional(readOnly = true)
    public Map<Customer, Integer> getPointsByCustomer() {
        Map<Customer, Integer> result = new LinkedHashMap<>();
        customerDao.findAll().forEach(customer -> result.put(customer, 0));
        getEvents().forEach(event -> result.merge(event.getCustomer(), event.getPoints(), Integer::sum));
        return result;
    }
}
