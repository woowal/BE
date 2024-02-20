package com.ddingmate.ddingmate.post.controller;

import com.ddingmate.ddingmate.member.state.UserAuthorize;
import com.ddingmate.ddingmate.post.dto.request.PostCreateRequest;
import com.ddingmate.ddingmate.post.dto.request.PostUpdateRequest;
import com.ddingmate.ddingmate.post.dto.response.PostResponse;
import com.ddingmate.ddingmate.post.service.PostService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping()
    @UserAuthorize
    public ApiResponse<Void> createPost(@AuthenticationPrincipal Long memberId, @RequestBody PostCreateRequest postCreateRequest) {
        postService.createPost(memberId, postCreateRequest);

        return ApiResponse.ok();
    }

    @DeleteMapping("/{postId}")
    @UserAuthorize
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ApiResponse.ok();
    }

    @PatchMapping("/{postId}")
    @UserAuthorize
    public ApiResponse<Boolean> updatePost(@PathVariable Long postId, @RequestBody PostUpdateRequest postUpdateRequest) {
        return ApiResponse.ok(postService.updatePost(postId, postUpdateRequest));
    }

    @GetMapping("/all")
    public ApiResponse<List> retrieveAll() {
        return ApiResponse.ok(postService.retrieveAll());
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> retrievePost(@AuthenticationPrincipal Long memberId, @PathVariable Long postId) {
        return ApiResponse.ok(postService.retrievePost(memberId, postId));
    }

    @GetMapping("/catecory/{category}")
    public ApiResponse<List> retrievePostsByCategory(@PathVariable String category) {
        return ApiResponse.ok(postService.retrievePostsByCategory(category));
    }

    @GetMapping("/mark")
    @UserAuthorize
    public ApiResponse<List> retrievePostsByMark(@AuthenticationPrincipal Long memberId) {
        return ApiResponse.ok(postService.retrievePostsByMark(memberId));
    }

    @GetMapping("/type/{type}")
    public ApiResponse<List> retrievePostsByType(@PathVariable String type) {
        return ApiResponse.ok(postService.retrievePostsByType(type));
    }

}
