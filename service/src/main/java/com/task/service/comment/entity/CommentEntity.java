package com.task.service.comment.entity;

import com.task.service.base.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * The CommentEntity class is used for saving and getting the comments from the database. It is extended
 * from the main entity class BaseEntity.
 * It has a String of comment in it.
 *
 * @author Harut
 * @since 12.17.2020
 */
@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "comment")
@EqualsAndHashCode(callSuper = true)
public class CommentEntity extends BaseEntity {

    private String comment;

}

