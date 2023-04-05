package com.mealkit.service;


import com.mealkit.domain.UserAccount;
import com.mealkit.domain.post.user.Dto.UserPostCommentDto;
import com.mealkit.domain.post.user.UserPost;
import com.mealkit.domain.post.user.UserPostComment;
import com.mealkit.repository.UserPostCommentRepository;
import com.mealkit.repository.UserPostRepository;
import com.mealkit.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;

@Slf4j
@Service
@Transactional
@RequiredArgsConstructor
public class UserPostCommentService {

    private final UserPostCommentRepository userPostCommentRepository;
    private final UserRepository userRepository;
    private final UserPostRepository userPostRepository;


    @Transactional(readOnly = true)
    public List<UserPostCommentDto> searchUserPostComment(Long userPostId) {
        return userPostCommentRepository.findById(userPostId).stream().map(UserPostCommentDto::from).toList();
    }


    @Transactional
    public void saveUserPostComment(UserPostCommentDto dto) {

        try {
            System.out.println("check 1 : " + dto);
            UserPost userPost = userPostRepository.getReferenceById(dto.userPostId());
            System.out.println("check2");
            UserAccount userAccount = userRepository.getReferenceById(dto.userAccountDto().userId());
            UserPostComment userPostComment = dto.toEntity(userPost, userAccount);
            if (dto.parentCommentId() != null) {
                UserPostComment parentComment = userPostCommentRepository.getReferenceById(dto.parentCommentId());
                parentComment.addChildComment(userPostComment);
            } else {
                userPostCommentRepository.save(userPostComment);
            }

        } catch (EntityNotFoundException e) {
            log.warn("댓글 정보없음");
        }
    }


    public void deleteUserPostComment(Long userPostCommentId, Long userId){
        userPostCommentRepository.deleteByUserPostCommentIdAndUserAccount_UserId(userPostCommentId, userId);
    }
}
