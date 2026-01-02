package hr.unist.lab4.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Member member;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    private Book book;

    @Enumerated(EnumType.STRING)
    private ReservationStatus status = ReservationStatus.PENDING;

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime fulfilledAt;

    public Reservation() {}

    public Reservation(Member member, Book book) {
        this.member = member;
        this.book = book;
    }

    public Long getId() { return id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public Book getBook() { return book; }
    public void setBook(Book book) { this.book = book; }

    public ReservationStatus getStatus() { return status; }
    public void setStatus(ReservationStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }

    public LocalDateTime getFulfilledAt() { return fulfilledAt; }
    public void setFulfilledAt(LocalDateTime fulfilledAt) { this.fulfilledAt = fulfilledAt; }
}
