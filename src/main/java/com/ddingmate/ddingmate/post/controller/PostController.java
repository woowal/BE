package com.ddingmate.ddingmate.post.controller;

import com.ddingmate.ddingmate.post.dto.request.PostCreateRequest;
import com.ddingmate.ddingmate.post.dto.request.PostUpdateRequest;
import com.ddingmate.ddingmate.post.dto.response.PostResponse;
import com.ddingmate.ddingmate.post.service.PostService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/post")
@RequiredArgsConstructor
public class PostController {

    private final PostService postService;

    @PostMapping
    public ApiResponse<Void> createPost(@RequestBody PostCreateRequest postCreateRequest) {
        postService.createPost(postCreateRequest);

        return ApiResponse.ok();
    }

    @DeleteMapping("/postId={postId}")
    public ApiResponse<Void> deletePost(@PathVariable Long postId) {
        postService.deletePost(postId);

        return ApiResponse.ok();
    }

    @PatchMapping
    public ApiResponse<Void> updatePost(@RequestBody PostUpdateRequest postUpdateRequest) {
        postService.updatePost(postUpdateRequest);

        return ApiResponse.ok();
    }

    @GetMapping()
    public ApiResponse<List> retrieveAll() {
        return ApiResponse.ok(postService.retrieveAll());
    }

    @GetMapping("/{postId}")
    public ApiResponse<PostResponse> retrievePost(@PathVariable Long postId) {
        return ApiResponse.ok(postService.retrievePost(postId));
    }

    @GetMapping("/category={category}")
    public ApiResponse<List> retrievePostsByCategory(@PathVariable String category) {
        return ApiResponse.ok(postService.retrievePostsByCategory(category));
    }

    @GetMapping("/like/{memberId}")
    public ApiResponse<List> retrievePostsByLike(@PathVariable Long memberId) {
        return ApiResponse.ok(postService.retrievePostsByLike(memberId));
    }

    @GetMapping("/type={type}")
    public ApiResponse<List> retrievePostsByType(@PathVariable String type) {
        return ApiResponse.ok(postService.retrievePostsByType(type));
    }

}
