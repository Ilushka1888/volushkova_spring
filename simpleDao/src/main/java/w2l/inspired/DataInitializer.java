package w2l.inspired;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.model.Customer;

@Component
public class DataInitializer implements ApplicationRunner {

    private final CustomerDao customerDao;

    public DataInitializer(CustomerDao customerDao) {
        this.customerDao = customerDao;
    }

    @Override
    public void run(ApplicationArguments args) {
        if (customerDao.count() == 0) {
            customerDao.save(new Customer("Ivanov"));
            customerDao.save(new Customer("Petrov"));
            customerDao.save(new Customer("Sidorov"));
            customerDao.save(new Customer("Pushkin"));
        }
    }
}