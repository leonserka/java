package hr.unist.lab6.service;

import hr.unist.lab6.exception.BookNotFoundException;
import hr.unist.lab6.model.*;
import hr.unist.lab6.repository.BookRepository;
import hr.unist.lab6.repository.MemberRepository;
import hr.unist.lab6.repository.NotificationRepository;
import hr.unist.lab6.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepo;
    private final MemberRepository memberRepo;
    private final BookRepository bookRepo;
    private final NotificationRepository notificationRepo;

    public ReservationServiceImpl(
            ReservationRepository reservationRepo,
            MemberRepository memberRepo,
            BookRepository bookRepo,
            NotificationRepository notificationRepo
    ) {
        this.reservationRepo = reservationRepo;
        this.memberRepo = memberRepo;
        this.bookRepo = bookRepo;
        this.notificationRepo = notificationRepo;
    }

    @Override
    public Reservation create(Long memberId, Long bookId) {
        Member member = memberRepo.findById(memberId)
                .orElseThrow(() -> new IllegalArgumentException("Member with id " + memberId + " not found"));

        Book book = bookRepo.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book with id " + bookId + " not found"));

        Reservation reservation = new Reservation(member, book);
        reservation.setStatus(ReservationStatus.PENDING);

        return reservationRepo.save(reservation);
    }

    @Override
    @Transactional
    public Reservation fulfill(Long reservationId) {
        Reservation reservation = reservationRepo.findById(reservationId)
                .orElseThrow(() -> new IllegalArgumentException("Reservation with id " + reservationId + " not found"));

        if (reservation.getStatus() == ReservationStatus.FULFILLED) {
            throw new IllegalArgumentException("Reservation already fulfilled");
        }

        reservation.setStatus(ReservationStatus.FULFILLED);
        reservation.setFulfilledAt(LocalDateTime.now());
        Reservation saved = reservationRepo.save(reservation);
        String title = reservation.getBook().getTitle();
        String msg = "Knjiga '" + title + "' je sada dostupna za posudbu.";
        notificationRepo.save(new Notification(reservation.getMember(), msg));

        return saved;
    }
}
