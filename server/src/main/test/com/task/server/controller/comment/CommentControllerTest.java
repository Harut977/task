package com.task.server.controller.comment;

import com.task.api.base.response.Response;
import com.task.api.base.response.SuccessResponse;
import com.task.api.comment.request.CommentRequest;
import com.task.service.comment.repository.CommentRepository;
import com.task.service.notification.repository.NotificationRepository;
import net.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Objects;

/**
 * This class is used for testing the work of the comments and notifications, then solving custom problems
 *
 * @author Harut
 * @since 12.17.2020
 */
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ActiveProfiles("test")
class CommentControllerTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommentControllerTest.class);

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private NotificationRepository notificationRepository;

    @Test
    void createComment() {
        for (int i = 0; i < 1000; i++) {
            String comment = RandomString.make();
            ResponseEntity<Response> responseEntity = restTemplate
                    .postForEntity("/comment",
                            new CommentRequest(comment), Response.class);
            SuccessResponse successResponse = modelMapper.map(
                    Objects.requireNonNull(responseEntity.getBody()).getData(),
                    SuccessResponse.class);
            Long id = successResponse.getObjectId();
            if (successResponse.isSuccess()) {
                LOGGER.info("Checking if it is added to the database or not");
                Assertions.assertTrue(commentRepository.existsById(id));
                Assertions.assertTrue(notificationRepository.existsByCommentEntity_Id(id));
            } else {
                LOGGER.info("Checking if it is removed from the database or not");
                Assertions.assertFalse(commentRepository.existsById(id));
                Assertions.assertFalse(notificationRepository.existsByCommentEntity_Id(id));
            }
        }
        System.out.println(commentRepository.count());
        System.out.println("Процент удачно добавленных комментариев. " + (double) commentRepository.count() / 10 + "%");
        System.out.println("Процент удачно доставленных уведомлений. " + (double) notificationRepository.countByDeliveredTrue() / 10 + "%");
    }
}