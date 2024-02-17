package com.ddingmate.ddingmate.mark.controller;

import com.ddingmate.ddingmate.mark.service.MarkService;
import com.ddingmate.ddingmate.util.response.ApiResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/mark")
@RequiredArgsConstructor
public class MarkController {

    private final MarkService markService;

    @PostMapping("postId")
    public ApiResponse<Void> createLike(@AuthenticationPrincipal Long memberId, @PathVariable Long postId) {
        markService.createMark(memberId, postId);

        return ApiResponse.ok();
    }

    @DeleteMapping
    public ApiResponse<Void> deleteLike(@AuthenticationPrincipal Long memberId, @PathVariable Long postId) {
        markService.deleteMark(memberId, postId);

        return ApiResponse.ok();
    }
}
