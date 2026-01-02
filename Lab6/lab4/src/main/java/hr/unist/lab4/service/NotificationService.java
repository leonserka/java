package hr.unist.lab4.service;

import hr.unist.lab4.model.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getByMemberId(Long memberId);
}
