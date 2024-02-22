package com.ddingmate.ddingmate.post.controller;

import com.ddingmate.ddingmate.post.domain.Post;
import com.ddingmate.ddingmate.post.dto.request.PostCategoryRequest;
import com.ddingmate.ddingmate.member.state.UserAuthorize;
import com.ddingmate.ddingmate.post.dto.request.PostCreateRequest;
import com.ddingmate.ddingmate.post.dto.request.PostUpdateRequest;
import com.ddingmate.ddingmate.post.dto.response.PostResponse;
import com.ddingmate.ddingmate.post.service.PostService;
import com.ddingmate.ddingmate.post.state.Category;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

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
        List<PostResponse> posts = postService.retrieveAll().stream()
                .map(post -> PostResponse.from(post, false))
                .collect(Collectors.toList());

        return ApiResponse.ok(posts);
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> retrievePost(@AuthenticationPrincipal Long memberId, @PathVariable Long postId) {
        Post post = postService.retrievePost(postId);

        boolean isMine = post.getMember().getId().equals(memberId);

        return ApiResponse.ok(PostResponse.from(post, isMine));
    }

    @GetMapping("/category")
    public ApiResponse<List> retrievePostsByCategory(@RequestBody PostCategoryRequest postCategoryRequest) {
        List<PostResponse> posts = postService.retrievePostsByCategory(postCategoryRequest).stream()
                .map(post -> PostResponse.from(post, false))
                .collect(Collectors.toList());

        return ApiResponse.ok(posts);
    }

    @GetMapping("/mark")
    @UserAuthorize
    public ApiResponse<List> retrievePostsByMark(@AuthenticationPrincipal Long memberId) {
        List<PostResponse> posts = postService.retrievePostsByMark(memberId).stream()
                .map(post -> PostResponse.from(post, false))
                .collect(Collectors.toList());

        return ApiResponse.ok(posts);
    }

    @GetMapping("/type/{type}")
    public ApiResponse<List> retrievePostsByType(@PathVariable String type) {
        List<PostResponse> posts = postService.retrievePostsByType(type).stream()
                .map(post -> PostResponse.from(post, false))
                .collect(Collectors.toList());

        return ApiResponse.ok(posts);
    }

    @GetMapping("/categories")
    public ApiResponse<List<Category>> retrieveCategory() {
        return ApiResponse.ok(postService.retrieveCategory());
    }
}
