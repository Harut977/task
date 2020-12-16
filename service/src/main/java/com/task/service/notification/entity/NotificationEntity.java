package com.task.service.notification.entity;

import com.task.service.base.BaseEntity;
import com.task.service.comment.entity.CommentEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "notification")
@EqualsAndHashCode(callSuper = true)
public class NotificationEntity extends BaseEntity {

    @OneToOne
    private CommentEntity commentEntity;

    private boolean delivered;

}
