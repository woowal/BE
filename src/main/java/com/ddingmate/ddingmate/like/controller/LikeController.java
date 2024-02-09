package com.ddingmate.ddingmate.like.controller;

import com.ddingmate.ddingmate.like.dto.request.LikeCreateRequest;
import com.ddingmate.ddingmate.like.dto.request.LikeDeleteRequest;
import com.ddingmate.ddingmate.like.service.LikeService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/like")
@RequiredArgsConstructor
public class LikeController {

    private final LikeService likeService;

    @PostMapping
    public ApiResponse<Void> createLike(@RequestBody LikeCreateRequest likeCreateRequest) {
        likeService.createLike(likeCreateRequest);

        return ApiResponse.ok();
    }

    @DeleteMapping
    public ApiResponse<Void> deleteLike(@RequestBody LikeDeleteRequest likeDeleteRequest) {
        likeService.deleteLike(likeDeleteRequest);

        return ApiResponse.ok();
    }
}
