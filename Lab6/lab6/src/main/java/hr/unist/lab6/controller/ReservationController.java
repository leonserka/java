package hr.unist.lab6.controller;

import hr.unist.lab6.model.Reservation;
import hr.unist.lab6.service.ReservationService;
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
    public ResponseEntity<Reservation> create(
            @RequestParam Long memberId,
            @RequestParam Long bookId
    ) {
        Reservation created = service.createReservation(memberId, bookId);
        return ResponseEntity
                .created(URI.create("/api/reservations/" + created.getId()))
                .body(created);
    }

    @PutMapping("/{id}/fulfill")
    public ResponseEntity<Reservation> fulfill(@PathVariable Long id) {
        return ResponseEntity.ok(service.fulfillReservation(id));
    }
}
