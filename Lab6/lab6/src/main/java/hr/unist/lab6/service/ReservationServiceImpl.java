package hr.unist.lab6.service;

import hr.unist.lab6.exception.BookNotFoundException;
import hr.unist.lab6.exception.MemberNotFoundException;
import hr.unist.lab6.exception.ReservationNotFoundException;
import hr.unist.lab6.model.Book;
import hr.unist.lab6.model.Member;
import hr.unist.lab6.model.Notification;
import hr.unist.lab6.model.Reservation;
import hr.unist.lab6.repository.BookRepository;
import hr.unist.lab6.repository.MemberRepository;
import hr.unist.lab6.repository.NotificationRepository;
import hr.unist.lab6.repository.ReservationRepository;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final MemberRepository memberRepository;
    private final BookRepository bookRepository;
    private final NotificationRepository notificationRepository;

    public ReservationServiceImpl(
            ReservationRepository reservationRepository,
            MemberRepository memberRepository,
            BookRepository bookRepository,
            NotificationRepository notificationRepository
    ) {
        this.reservationRepository = reservationRepository;
        this.memberRepository = memberRepository;
        this.bookRepository = bookRepository;
        this.notificationRepository = notificationRepository;
    }

    @Override
    public Reservation createReservation(Long memberId, Long bookId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberNotFoundException("Member not found with id: " + memberId));

        Book book = bookRepository.findById(bookId)
                .orElseThrow(() -> new BookNotFoundException("Book not found with id: " + bookId));

        Reservation r = new Reservation();
        r.setMember(member);
        r.setBook(book);
        r.setFulfilled(false);

        return reservationRepository.save(r);
    }

    @Override
    public Reservation fulfillReservation(Long reservationId) {
        Reservation r = reservationRepository.findById(reservationId)
                .orElseThrow(() -> new ReservationNotFoundException("Reservation not found with id: " + reservationId));

        r.setFulfilled(true);
        Reservation saved = reservationRepository.save(r);

        String msg = "Knjiga '" + saved.getBook().getTitle() + "' je sada dostupna za posudbu.";
        Notification n = new Notification(msg, saved.getMember());
        notificationRepository.save(n);

        return saved;
    }
}
