package com.task.service.comment.repository;

import com.task.service.comment.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * CommentRepository is the repository class of the comments, which is extended from the JpaRepository class,
 * including the CommentEntity that are being saved in the database
 *
 * @author Harut
 * @since 12.17.2020
 */
@Repository
public interface CommentRepository extends JpaRepository<CommentEntity, Long> {
}
