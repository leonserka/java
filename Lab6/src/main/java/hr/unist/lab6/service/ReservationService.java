package hr.unist.lab6.service;

import hr.unist.lab6.model.Reservation;

public interface ReservationService {

    Reservation createReservation(Long memberId, Long bookId);

    Reservation fulfillReservation(Long reservationId);
}
