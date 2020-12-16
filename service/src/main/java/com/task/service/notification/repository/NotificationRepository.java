package com.task.service.notification.repository;

import com.task.service.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {


    @Modifying
    @Transactional
    @Query("UPDATE NotificationEntity e SET e.delivered = ?2 where e.id = ?1 ")
    void updateDeliveredFlag(Long id, boolean delivered);
}
