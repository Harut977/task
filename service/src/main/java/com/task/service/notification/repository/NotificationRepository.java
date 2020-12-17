package com.task.service.notification.repository;

import com.task.service.notification.entity.NotificationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

/**
 * NotificationRepository is the repository class for the notifications, which is extended from the JpaRepository class,
 * including the NotificationEntity in it.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Repository
public interface NotificationRepository extends JpaRepository<NotificationEntity, Long> {

    /**
     * updates the flag of the notification
     *
     * @param id        of the notification
     * @param delivered the delivery of the notification
     */
    @Modifying
    @Transactional
    @Query("UPDATE NotificationEntity e SET e.delivered = ?2 where e.id = ?1 ")
    void updateDeliveredFlag(Long id, boolean delivered);

    /**
     * @return count notification when delivered true
     */
    long countByDeliveredTrue();


    /**
     * checks whether there is a notification with the comment id or not
     *
     * @param commentEntity_Id the id of the comment being passed to the method
     * @return boolean value showing its' existence
     */
    Boolean existsByCommentEntity_Id(Long commentEntity_Id);

}
