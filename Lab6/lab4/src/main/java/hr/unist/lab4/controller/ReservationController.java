package hr.unist.lab4.controller;

import hr.unist.lab4.dto.ReservationCreateRequest;
import hr.unist.lab4.model.Reservation;
import hr.unist.lab4.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService service;

    public ReservationController(ReservationService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<Reservation> create(@RequestBody ReservationCreateRequest req) {
        Reservation created = service.create(req.getMemberId(), req.getBookId());
        return ResponseEntity.created(URI.create("/api/reservations/" + created.getId())).body(created);
    }

    @PutMapping("/{id}/fulfill")
    public ResponseEntity<Reservation> fulfill(@PathVariable Long id) {
        return ResponseEntity.ok(service.fulfill(id));
    }
}
