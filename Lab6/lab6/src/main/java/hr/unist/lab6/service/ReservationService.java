package hr.unist.lab6.service;

import hr.unist.lab6.model.Reservation;

public interface ReservationService {
    Reservation create(Long memberId, Long bookId);
    Reservation fulfill(Long reservationId);
}
