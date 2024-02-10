package com.ddingmate.ddingmate.comment.controller;

import com.ddingmate.ddingmate.comment.dto.request.CreateCommentRequest;
import com.ddingmate.ddingmate.comment.dto.response.CommentResponse;
import com.ddingmate.ddingmate.comment.service.CommentService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/comment")
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping()
    public ApiResponse<Void> createComment(@RequestBody CreateCommentRequest createCommentRequest) {
        commentService.createComment(createCommentRequest);
        return ApiResponse.ok();
    }

    @GetMapping("/byPost/{postId}")
    public ApiResponse<List<CommentResponse>> retrieveCommentByPost(@PathVariable(name = "postId") Long id) {
        return ApiResponse.ok(commentService.retrieveCommentByPost(id));
    }

    @GetMapping("/byMember/{memberId}")
    public ApiResponse<List<CommentResponse>> retrieveCommentByMember(@PathVariable(name = "memberId") Long id) {
        return ApiResponse.ok(commentService.retrieveCommentByMember(id));
    }

    @DeleteMapping("/{commentId}")
    public ApiResponse<Void> deleteCommentById(@PathVariable(name = "commentId") Long id) {
        commentService.deleteCommentById(id);
        return ApiResponse.ok();
    }

    @PatchMapping("/{commentId}")
    public ApiResponse<Void> updateComment(@PathVariable(name = "commentId") Long id, @RequestParam String content) {
        commentService.updateComment(id, content);
        return ApiResponse.ok();
    }

}
