package com.master.gestion_ticket.repository;

import com.master.gestion_ticket.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotificationRepository extends JpaRepository<Notification, Long> {
}

