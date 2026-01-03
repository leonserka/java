package hr.unist.lab6.controller;

import hr.unist.lab6.model.Notification;
import hr.unist.lab6.service.NotificationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/notifications")
public class NotificationController {

    private final NotificationService service;

    public NotificationController(NotificationService service) {
        this.service = service;
    }

    @GetMapping("/member/{memberId}")
    public ResponseEntity<List<Notification>> byMember(@PathVariable Long memberId) {
        return ResponseEntity.ok(service.getByMemberId(memberId));
    }
}
