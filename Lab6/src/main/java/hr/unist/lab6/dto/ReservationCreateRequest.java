package hr.unist.lab6.dto;

public class ReservationCreateRequest {
    private Long memberId;
    private Long bookId;

    public Long getMemberId() { return memberId; }
    public void setMemberId(Long memberId) { this.memberId = memberId; }

    public Long getBookId() { return bookId; }
    public void setBookId(Long bookId) { this.bookId = bookId; }
}
