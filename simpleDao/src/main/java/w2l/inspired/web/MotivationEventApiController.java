package w2l.inspired.web;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import w2l.inspired.model.MotivationEvent;
import w2l.inspired.service.MotivationEventService;
import w2l.inspired.web.dto.MotivationEventRequest;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/events")
public class MotivationEventApiController {
    private final MotivationEventService motivationEventService;

    public MotivationEventApiController(MotivationEventService motivationEventService) {
        this.motivationEventService = motivationEventService;
    }

    @GetMapping
    public List<MotivationEvent> getEvents() {
        return motivationEventService.getEvents();
    }

    @GetMapping("/customer/{customerId}")
    public List<MotivationEvent> getEventsByCustomer(@PathVariable Integer customerId) {
        return motivationEventService.getEventsByCustomer(customerId);
    }

    @PostMapping
    public ResponseEntity<MotivationEvent> addEvent(@RequestBody MotivationEventRequest request) {
        MotivationEvent event = motivationEventService.addEvent(
                request.customerId(),
                request.description(),
                request.points()
        );
        return ResponseEntity.created(URI.create("/api/events/" + event.getId())).body(event);
    }
}
