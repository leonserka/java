package hr.unist.lab4.service;

import hr.unist.lab4.model.Reservation;

public interface ReservationService {
    Reservation create(Long memberId, Long bookId);
    Reservation fulfill(Long reservationId);
}
