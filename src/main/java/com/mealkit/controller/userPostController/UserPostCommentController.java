package com.mealkit.controller.userPostController;


import com.mealkit.domain.post.user.Dto.request.UserPostCommentRequest;
import com.mealkit.jwt.domainTO.UserDetailsImplement;
import com.mealkit.service.UserPostCommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/comments")
@RequiredArgsConstructor
@Controller
public class UserPostCommentController {

    private final UserPostCommentService userPostCommentService;



    @PostMapping("/news")
    public String postNewUserPostComment(
            @AuthenticationPrincipal UserDetailsImplement userDetailsImplement,
            @RequestBody UserPostCommentRequest userPostCommentRequest
            ){
        System.out.println("postNewUserPostComment");
        userPostCommentService.saveUserPostComment(userPostCommentRequest.toDto(userDetailsImplement.toDto()));
        return "redirect:/community/posts/"+userPostCommentRequest.userPostId();
    }

    @PostMapping("/{commentId}/deletes")
    public String deleteUserPostComment(
            @PathVariable Long commentId,
            @AuthenticationPrincipal UserDetailsImplement userDetailsImplement,
            Long userPostId
    ){
        userPostCommentService.deleteUserPostComment(commentId, userDetailsImplement.getUserAccount().getUserId());
        return "redirect:/community/posts/" + userPostId;
    }

}
