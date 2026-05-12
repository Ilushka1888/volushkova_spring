package w2l.inspired.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import w2l.inspired.dao.CustomerDao;
import w2l.inspired.service.MotivationEventService;

import java.time.LocalDate;

@Controller
public class CustomerController {
    private final CustomerDao customerDao;
    private final MotivationEventService motivationEventService;

    public CustomerController(CustomerDao customerDao, MotivationEventService motivationEventService) {
        this.customerDao = customerDao;
        this.motivationEventService = motivationEventService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("serverTime", LocalDate.now());
        model.addAttribute("customers", customerDao.findAll());
        model.addAttribute("events", motivationEventService.getEvents());
        model.addAttribute("totalPoints", motivationEventService.getTotalPoints());
        model.addAttribute("pointsByCustomer", motivationEventService.getPointsByCustomer());
        return "index";
    }

    @PostMapping("/events")
    public String addEvent(@RequestParam Integer customerId,
                           @RequestParam String description,
                           @RequestParam int points,
                           RedirectAttributes redirectAttributes) {
        motivationEventService.addEvent(customerId, description, points);
        redirectAttributes.addFlashAttribute("message", "Событие сохранено");
        return "redirect:/";
    }
}
