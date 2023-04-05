package com.mealkit.repository;

import com.mealkit.domain.post.user.UserPostComment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPostCommentRepository extends JpaRepository<UserPostComment, Long> {

List<UserPostComment> findByUserPost_UserPostId(Long userPostId);

    void deleteByUserPostCommentIdAndUserAccount_UserId(Long userPostCommendId, Long userId);
}
