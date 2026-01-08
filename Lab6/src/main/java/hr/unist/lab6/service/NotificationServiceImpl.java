package hr.unist.lab6.service;

import hr.unist.lab6.model.Notification;
import hr.unist.lab6.repository.NotificationRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository repo;

    public NotificationServiceImpl(NotificationRepository repo) {
        this.repo = repo;
    }

    @Override
    public List<Notification> getByMemberId(Long memberId) {
        return repo.findByMemberIdOrderByCreatedAtDesc(memberId);
    }
}
