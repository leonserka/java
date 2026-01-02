package hr.unist.lab4.model;

import jakarta.persistence.*;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.time.LocalDateTime;

@Entity
public class Notification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JsonIgnore
    @ManyToOne(optional = false, fetch = FetchType.LAZY)
    private Member member;

    @Column(length = 500, nullable = false)
    private String message;

    private LocalDateTime createdAt = LocalDateTime.now();

    public Notification() {}

    public Notification(Member member, String message) {
        this.member = member;
        this.message = message;
    }

    public Long getId() { return id; }

    public Member getMember() { return member; }
    public void setMember(Member member) { this.member = member; }

    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
