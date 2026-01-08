package hr.unist.lab6.controller;

import hr.unist.lab6.model.Notification;
import hr.unist.lab6.repository.NotificationRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationRepository repository;

    public NotificationController(NotificationRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Notification>> getForMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(repository.findByMemberId(memberId));
    }
}
