package hr.unist.lab6.service;

import hr.unist.lab6.model.Notification;
import java.util.List;

public interface NotificationService {
    List<Notification> getByMemberId(Long memberId);
}
